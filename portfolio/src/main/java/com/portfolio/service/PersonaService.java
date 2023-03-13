package com.portfolio.service;

import com.portfolio.entidad.Persona;
import com.portfolio.repositorios.IPersona;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import org.springframework.web.multipart.MultipartFile;


public class PersonaService {
    IPersona _pers;
    
    public PersonaService(IPersona pers){
        this._pers = pers;
    }
    
    public Persona getPersonas(Integer id) throws SQLException, IOException{
        return _pers.getPersonas(id);
    }
    
    public String editPersona(Persona persona) throws SQLException{
        return _pers.editPersona(persona);
    }
    
    public String editFoto(MultipartFile foto) throws FileNotFoundException, SQLException, IOException{
        return _pers.editFoto(foto);
    }
}
