package com.portfolio.repositorios;

import com.portfolio.entidad.habilidades;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;


public interface IHabilidades {
    public List<habilidades> getHabilidad() throws SQLException, IOException;
    public String editHabilidad(habilidades habilidad) throws SQLException, ParseException;
    public String agregarHabilidad(habilidades habilidad) throws SQLException, ParseException;
    public String eliminarHabilidad(Integer id) throws SQLException;
    public String editFoto(MultipartFile foto, Integer id) throws FileNotFoundException, SQLException, IOException;
}
