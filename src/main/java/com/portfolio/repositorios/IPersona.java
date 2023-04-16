package com.portfolio.repositorios;


import com.portfolio.entidad.Persona;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import org.springframework.web.multipart.MultipartFile;


public interface IPersona {
    public Persona getPersonas(Integer id) throws SQLException, IOException;
    public String editPersona(Persona persona) throws SQLException;
    public String editFoto(MultipartFile foto) throws FileNotFoundException, SQLException, IOException;
}
