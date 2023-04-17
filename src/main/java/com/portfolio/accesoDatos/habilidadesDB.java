package com.portfolio.accesoDatos;

import com.portfolio.entidad.habilidades;
import com.portfolio.repositorios.IHabilidades;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import logicaNegocio.manejoImagenes;
import org.springframework.web.multipart.MultipartFile;


public class habilidadesDB implements IHabilidades{
    
    @Override
    public List<habilidades> getHabilidad() throws SQLException, IOException{
        accesoDB datos = new accesoDB();
        manejoImagenes manejoImg = new manejoImagenes();
        List<habilidades> lista = new ArrayList<habilidades>();
        
        ResultSet resultado = datos.consultaSinParametros("select * from habilidades");
        
        while(resultado.next()){
            habilidades habilidad = new habilidades();
            
            Blob blob = resultado.getBlob("foto");
            
            if (resultado.wasNull()) {
                habilidad.setFoto(null);
            } 
            else {
               byte[] bytes = manejoImg.obtenerBytes(blob);
               String base64 = manejoImg.codificarBase64(bytes);
               String listo = "data:image/jpg;base64," + base64;
               habilidad.setFoto(listo);
            }
            
            habilidad.setId(resultado.getInt("id"));
            habilidad.setNombre(resultado.getString("nombre"));
            habilidad.setPorcentaje(resultado.getInt("porcentaje"));
            habilidad.setTipo(resultado.getString("tipo"));
                          
            lista.add(habilidad);
        }
        datos.cerrarConexion();
        return lista;       
    }
    
    @Override
    public String editHabilidad(habilidades habilidad) throws SQLException, ParseException{
        accesoDB datos = new accesoDB();

        datos.sentenciaConParametros("update habilidades set nombre=?, tipo=?,porcentaje=? where id=?");
        datos.setearParametros(1, habilidad.getNombre());
        datos.setearParametros(2, habilidad.getTipo());
        datos.setearParametros(3, habilidad.getPorcentaje().toString());
        datos.setearParametros(4, habilidad.getId().toString());

        datos.ejecutarUpdateParametros();
        datos.cerrarConexion();
        return "editado con exito";
        
    }
    
    @Override
    public String agregarHabilidad(habilidades habilidad) throws SQLException, ParseException{
        accesoDB datos = new accesoDB();

        datos.sentenciaConParametros("insert into habilidades (nombre,tipo,porcentaje) values (?,?,?)");
        datos.setearParametros(1, habilidad.getNombre());
        datos.setearParametros(2, habilidad.getTipo());
        datos.setearParametros(3, habilidad.getPorcentaje().toString());

        datos.ejecutarUpdateParametros();
        datos.cerrarConexion();
        return "editado con exito";
        
    }
    
    @Override
    public String eliminarHabilidad(Integer id) throws SQLException{
        accesoDB datos = new accesoDB();
        
        datos.consultaUpdateSinParametros("delete from habilidades where id= " +id.toString());
        datos.cerrarConexion();
        return "Eliminado con exito";      
    }
    
    @Override
    public String editFoto(MultipartFile foto, Integer id) throws FileNotFoundException, SQLException, IOException{
        accesoDB datos = new accesoDB();
        FileInputStream stream = (FileInputStream) foto.getInputStream();
        
        //insertar blob en base de datos
        datos.sentenciaConParametros("update habilidades set foto = ? where id = ?");       
        datos.setearParametroBinaryStream(1, stream, (int)foto.getSize());
        datos.setearParametros(2, id.toString());
        
        datos.ejecutarUpdateParametros();
        datos.cerrarConexion();
        return "Editado con exito";
        
    }
}
