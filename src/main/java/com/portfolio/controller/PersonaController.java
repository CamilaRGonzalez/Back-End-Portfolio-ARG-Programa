package com.portfolio.controller;

import com.portfolio.accesoDatos.personaDB;
import com.portfolio.entidad.Persona;
import com.portfolio.service.PersonaService;
import java.io.IOException;
import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping(path="/personas")
@CrossOrigin(origins = {"http://localhost:4200","https://arg-programa-portfolio.web.app"})
public class PersonaController {
    private PersonaService datosDB = new PersonaService(new personaDB());
       
    @GetMapping(path="/{id}")
    public @ResponseBody Persona getPersona(@PathVariable("id") Integer id) throws SQLException, IOException {
        return datosDB.getPersonas(id);
    }
    
    @PutMapping(path="/editar")
    public @ResponseBody String editPersona(@RequestBody Persona pers) throws SQLException{
        return datosDB.editPersona(pers);
    }
    
    @PutMapping(path="/editarfoto")
    public @ResponseBody String editFoto(@RequestParam MultipartFile archivo) throws SQLException, IOException{
        return datosDB.editFoto(archivo);
    }
    
}
