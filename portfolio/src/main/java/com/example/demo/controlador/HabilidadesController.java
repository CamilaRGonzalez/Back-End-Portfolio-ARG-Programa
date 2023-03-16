package com.example.demo.controlador;

import com.portfolio.accesoDatos.habilidadesDB;
import com.portfolio.entidad.habilidades;
import com.portfolio.service.HabilidadesService;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping(path="/habilidades")
@CrossOrigin(origins = "http://localhost:4200")
public class HabilidadesController {
    private HabilidadesService datosDB = new HabilidadesService(new habilidadesDB());
    
    @GetMapping(path="")
    public @ResponseBody List<habilidades> getHabilidad() throws SQLException, IOException{
        return datosDB.getHabilidad();
    }
    
    @PostMapping(path="nueva")
    public @ResponseBody String crearHabilidad(@RequestBody habilidades habilidad) throws SQLException, ParseException{
        return datosDB.agregarHabilidad(habilidad);
    }
    
    @DeleteMapping(path="eliminar/{id}")
    public @ResponseBody String eliminarHabilidad(@PathVariable Integer id) throws SQLException{
        return datosDB.eliminarHabilidad(id);
    }
    
    @PutMapping(path="/editar")
    public @ResponseBody String editHabilidad(@RequestBody habilidades habilidad) throws SQLException, ParseException{
        return datosDB.editHabilidad(habilidad);
    }
    
    @PutMapping(path="/editarfoto")
    public @ResponseBody String editFoto(@RequestParam MultipartFile archivo,@RequestParam Integer id) throws SQLException, IOException{
        return datosDB.editFoto(archivo, id);
    }
}
