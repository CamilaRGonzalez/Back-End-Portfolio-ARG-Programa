package com.portfolio.controller;

import com.portfolio.accesoDatos.educacionDB;
import com.portfolio.entidad.Educacion;
import com.portfolio.service.EducacionService;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(path="/educacion")
@CrossOrigin(origins = {"http://localhost:4200","https://arg-programa-portfolio.web.app"})
public class EducacionController {
    private final EducacionService datosDB = new EducacionService(new educacionDB());
    
    @GetMapping(path="")
    public @ResponseBody List<Educacion> getEducacion() throws SQLException, IOException{
        return datosDB.getEducacion();
    }
    
    @PutMapping(path="/editar")
    public @ResponseBody String editEducacion(@RequestBody Educacion edu) throws SQLException, ParseException{
        return datosDB.editEducacion(edu);
    }
    
    @PutMapping(path="/editarfoto")
    public @ResponseBody String editFoto(@RequestParam MultipartFile archivo,@RequestParam Integer id) throws SQLException, IOException{
        return datosDB.editFoto(archivo,id);
    }
    
    @PostMapping(path="nueva")
    public @ResponseBody String nuevaEducacion(@RequestBody Educacion educ) throws SQLException, ParseException{
        return datosDB.agregarEducacion(educ);
    }
    
    @DeleteMapping(path="eliminar/{id}")
    public @ResponseBody String eliminarEducacion(@PathVariable Integer id) throws SQLException{
        return datosDB.deleteEducacion(id);
    }
    
}
