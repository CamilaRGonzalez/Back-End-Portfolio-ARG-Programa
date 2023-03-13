package com.portfolio.service;

import com.portfolio.entidad.habilidades;
import com.portfolio.repositorios.IHabilidades;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;


public class HabilidadesService {
    IHabilidades _hab;
    
    public HabilidadesService(IHabilidades hab){
        this._hab = hab;
    }
    
    public List<habilidades> getHabilidad() throws SQLException, IOException{
        return _hab.getHabilidad();
    }
    public String editHabilidad(habilidades habilidad) throws SQLException, ParseException{
        return _hab.editHabilidad(habilidad);
    }
    public String agregarHabilidad(habilidades habilidad) throws SQLException, ParseException{
        return _hab.agregarHabilidad(habilidad);
    }
    public String eliminarHabilidad(Integer id) throws SQLException{
        return _hab.eliminarHabilidad(id);
    }
    public String editFoto(MultipartFile foto, Integer id) throws FileNotFoundException, SQLException, IOException{
        return _hab.editFoto(foto, id);
    }
}
