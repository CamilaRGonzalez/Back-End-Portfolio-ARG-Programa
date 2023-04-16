package com.portfolio.controller;

import com.portfolio.accesoDatos.proyectosDB;
import com.portfolio.entidad.Proyectos;
import com.portfolio.service.ProyectosService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping(path="/proyectos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProyectosController {
    private ProyectosService datosDB = new ProyectosService(new proyectosDB());
    
    @GetMapping(path="")
    public @ResponseBody List<Proyectos> getProyecto() throws SQLException, IOException {
        return datosDB.getProyectos();
    }
    
    @PostMapping(path="nuevo")
    public @ResponseBody String nuevoProyecto(@RequestBody Proyectos proyecto) throws SQLException, IOException {
        return datosDB.agregarProyecto(proyecto);
    }
    
    @PutMapping(path="editar")
    public @ResponseBody String editarProyecto(@RequestBody Proyectos proyecto) throws SQLException, IOException {
        return datosDB.editarProyecto(proyecto);
    }
    
    @PutMapping(path="editarfoto")
    public @ResponseBody String editarProyecto(@RequestParam MultipartFile archivo, @RequestParam Integer id) throws SQLException, IOException {
        return datosDB.editFoto(archivo, id);
    }
    
    @DeleteMapping(path="eliminar/{id}")
    public @ResponseBody String eliminarProyecto(@PathVariable Integer id) throws SQLException{
        return datosDB.eliminarProyecto(id);
    }
    
}
