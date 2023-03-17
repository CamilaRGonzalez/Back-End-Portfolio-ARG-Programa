package com.portfolio.controller;

import com.portfolio.accesoDatos.usuariosDB;
import com.portfolio.entidad.Response;
import java.io.IOException;
import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/login")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuariosController {
    private usuariosDB datosDB= new usuariosDB();
    
    @PostMapping(path="")
    public @ResponseBody Response nuevoProyecto(@RequestParam String user,@RequestParam String pass) throws SQLException, IOException {
        if(datosDB.login(user, pass)){
            return new Response(user,1);
        }
        else{
            return new Response(null,0);
        }
    }   
}
