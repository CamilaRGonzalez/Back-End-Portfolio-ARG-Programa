package com.portfolio.accesoDatos;

import com.portfolio.entidad.Proyectos;
import com.portfolio.repositorios.IProyectos;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logicaNegocio.manejoImagenes;
import org.springframework.web.multipart.MultipartFile;



public class proyectosDB implements IProyectos{
    
    @Override
    public List<Proyectos> getProyectos() throws SQLException, IOException{
        accesoDB datos = new accesoDB();
        manejoImagenes manejoImg = new manejoImagenes();
        List<Proyectos> lista = new ArrayList<Proyectos>();
        
        ResultSet resultado = datos.consultaSinParametros("select * from proyectos");
        
        while(resultado.next()){
            Proyectos proyecto = new Proyectos();
            
            Blob blob = resultado.getBlob("foto");
            
            if (resultado.wasNull()) {
                proyecto.setFoto(null);
            } 
            else {
               byte[] bytes = manejoImg.obtenerBytes(blob);
               String base64 = manejoImg.codificarBase64(bytes);
               String listo = "data:image/jpg;base64," + base64;
               proyecto.setFoto(listo);
            }
        
            proyecto.setId(resultado.getInt("id"));
            proyecto.setNombre(resultado.getString("nombre"));
            proyecto.setTecnologias(resultado.getString("tecnologias"));
            proyecto.setDescripcion(resultado.getString("descripcion"));
            proyecto.setLink(resultado.getString("link"));
                    
            lista.add(proyecto);
        }
        
        datos.cerrarConexion();
        return lista;
    }
    
    @Override
    public String agregarProyecto(Proyectos proyecto) throws SQLException{   
        accesoDB datos = new accesoDB();
        
        datos.sentenciaConParametros("insert into proyectos (nombre,tecnologias,descripcion,link) values (?,?,?,?)");
        datos.setearParametros(1, proyecto.getNombre());
        datos.setearParametros(2, proyecto.getTecnologias());
        datos.setearParametros(3, proyecto.getDescripcion());
        datos.setearParametros(4, proyecto.getLink());
        datos.ejecutarUpdateParametros();
        datos.cerrarConexion();
        
        return "Editado con exito";
    }
    
    @Override
    public String editarProyecto(Proyectos proyecto) throws SQLException{
        accesoDB datos = new accesoDB();
        
        datos.sentenciaConParametros("update proyectos set nombre=?, tecnologias =?, descripcion =?, link = ? where id=?");
        datos.setearParametros(1, proyecto.getNombre());
        datos.setearParametros(2, proyecto.getTecnologias());
        datos.setearParametros(3, proyecto.getDescripcion());
        datos.setearParametros(4, proyecto.getLink());
        datos.setearParametros(5, proyecto.getId().toString());
        
        datos.ejecutarUpdateParametros();
        datos.cerrarConexion();
        
        return "Editado con exito";
    }
    
    @Override
    public String eliminarProyecto(Integer id) throws SQLException{
        accesoDB datos = new accesoDB();
        
        datos.consultaUpdateSinParametros("delete from proyectos where id= "+id.toString());
   
        datos.cerrarConexion();
        return "Eliminado con exito";
    }
    
    @Override
    public String editFoto(MultipartFile foto,Integer id) throws FileNotFoundException, SQLException, IOException{
        accesoDB datos = new accesoDB();
        
        //manejoImagenes manejoImg = new manejoImagenes();
        FileInputStream stream = (FileInputStream) foto.getInputStream();
        
        //insertar blob en base de datos
        datos.sentenciaConParametros("update proyectos set foto = ? where id = ?");       
        datos.setearParametroBinaryStream(1, stream, (int)foto.getSize());
        datos.setearParametros(2, id.toString());
        
        datos.ejecutarUpdateParametros();
        datos.cerrarConexion();
        return "Editado con exito";
        
    }
}

