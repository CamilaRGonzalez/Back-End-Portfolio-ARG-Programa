package com.portfolio.service;

import com.portfolio.entidad.Proyectos;
import com.portfolio.repositorios.IProyectos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;


public class ProyectosService {
    IProyectos _proy;
    
    public ProyectosService(IProyectos proy){
        this._proy = proy;
    }
    
    public List<Proyectos> getProyectos() throws SQLException, IOException{
        return _proy.getProyectos();
    }
    public String agregarProyecto(Proyectos proyecto) throws SQLException{
        return _proy.agregarProyecto(proyecto);
    }
    public String editarProyecto(Proyectos proyecto) throws SQLException{
        return _proy.editarProyecto(proyecto);
    }
    public String eliminarProyecto(Integer id) throws SQLException{
        return _proy.eliminarProyecto(id);
    }
    public String editFoto(MultipartFile foto,Integer id) throws FileNotFoundException, SQLException, IOException{
        return _proy.editFoto(foto, id);
    }
}
