package com.portfolio.repositorios;


import com.portfolio.entidad.Proyectos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;


public interface IProyectos {
    public List<Proyectos> getProyectos() throws SQLException, IOException;
    public String agregarProyecto(Proyectos proyecto) throws SQLException;
    public String editarProyecto(Proyectos proyecto) throws SQLException;
    public String eliminarProyecto(Integer id) throws SQLException;
    public String editFoto(MultipartFile foto,Integer id) throws FileNotFoundException, SQLException, IOException;
}
