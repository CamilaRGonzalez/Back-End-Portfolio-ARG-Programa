package com.portfolio.repositorios;

import com.portfolio.entidad.Educacion;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;



public interface IEducacion {
    public List<Educacion> getEducacion()throws SQLException, IOException;
    public String editEducacion(Educacion educacion)throws SQLException;
    public String agregarEducacion(Educacion educacion)throws SQLException, ParseException;
    public String deleteEducacion(Integer id)throws SQLException;
    public String editFoto(MultipartFile foto, Integer id) throws FileNotFoundException, SQLException, IOException;
}
