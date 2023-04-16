package com.portfolio.service;

import com.portfolio.entidad.Educacion;
import com.portfolio.repositorios.IEducacion;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;


public class EducacionService {
    IEducacion _edu;
    
    public EducacionService(IEducacion edu){
        this._edu = edu;
    }
    
    public List<Educacion> getEducacion() throws SQLException, IOException{
        return _edu.getEducacion();
    }
    
    public String editEducacion(Educacion educacion)throws SQLException{
        return _edu.editEducacion(educacion);
    }
    
    public String agregarEducacion(Educacion educacion)throws SQLException, ParseException{
        return _edu.agregarEducacion(educacion);
    }
    
    public String deleteEducacion(Integer id)throws SQLException{
        return _edu.deleteEducacion(id);
    }
    
    public String editFoto(MultipartFile foto, Integer id) throws FileNotFoundException, SQLException, IOException{
        return _edu.editFoto(foto, id);
    }
}
