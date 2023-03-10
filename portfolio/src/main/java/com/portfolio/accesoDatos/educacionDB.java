package com.portfolio.accesoDatos;

import com.portfolio.entidad.Educacion;
import com.portfolio.repositorios.IEducacion;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logicaNegocio.manejoImagenes;
import org.springframework.web.multipart.MultipartFile;

public class educacionDB implements IEducacion{

    @Override
    public List<Educacion> getEducacion() throws SQLException, IOException{
        accesoDB datos = new accesoDB();
        manejoImagenes manejoImg = new manejoImagenes();
        List<Educacion> lista = new ArrayList<Educacion>();
        
        ResultSet resultado = datos.consultaSinParametros("select * from educacion");
        
        while(resultado.next()){
            Educacion educacion = new Educacion();
            
            Blob blob = resultado.getBlob("foto");
            
            if (resultado.wasNull()) {
                educacion.setFoto(null);
            } 
            else {
               byte[] bytes = manejoImg.obtenerBytes(blob);
               String base64 = manejoImg.codificarBase64(bytes);
               String listo = "data:image/jpg;base64," + base64;
               educacion.setFoto(listo);
            }
        
            educacion.setId(resultado.getInt("id"));
            educacion.setTitulo(resultado.getString("titulo"));
            educacion.setInstitucion(resultado.getString("institucion"));
            //formatear fecha
            Date fecha = resultado.getDate("fechainicio");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            
            educacion.setFechainicio(formatter.format(fecha));           
            educacion.setEstado(resultado.getString("estado"));

                        
            lista.add(educacion);
        }
        
        datos.cerrarConexion();
        return lista;
    }
    
    @Override
    public String editEducacion(Educacion educacion) throws SQLException {
        accesoDB datos = new accesoDB();
        
        datos.sentenciaConParametros("update educacion set titulo=?,institucion=?,fechainicio=?,estado=? where id=?");
        datos.setearParametros(1, educacion.getTitulo());
        datos.setearParametros(2, educacion.getInstitucion());
        datos.setearParametros(3, educacion.getFechainicio());
        datos.setearParametros(4, educacion.getEstado());
        datos.setearParametros(5,educacion.getId().toString());
        
        datos.ejecutarAccionParametros();
        datos.cerrarConexion();
        return "editado con exito";
        
    }
    
    @Override
    public String agregarEducacion(Educacion educacion) throws SQLException, ParseException{
        accesoDB datos = new accesoDB();
        
        datos.sentenciaConParametros("insert into educacion (titulo,institucion,fechainicio,estado) values (?,?,?,?)");
        datos.setearParametros(1, educacion.getTitulo());
        datos.setearParametros(2, educacion.getInstitucion());
        datos.setearParametros(3, educacion.getFechainicio());
        datos.setearParametros(4, educacion.getEstado());
      
        datos.ejecutarAccionParametros();
        datos.cerrarConexion();
        return "Editado con exito";
        
    }
    
    @Override
    public String deleteEducacion(Integer id) throws SQLException{
        accesoDB datos = new accesoDB();
        
        datos.consultaSinParametros("delete from educacion where id= "+id.toString());
        datos.cerrarConexion();
        return "Eliminado con exito";
    }
    
    @Override
    public String editFoto(MultipartFile foto, Integer id) throws FileNotFoundException, SQLException, IOException{
        accesoDB datos = new accesoDB();
        FileInputStream stream = (FileInputStream) foto.getInputStream();
        
        //insertar blob en base de datos
        datos.sentenciaConParametros("update educacion set foto = ? where id=?");       
        datos.setearParametroBinaryStream(1, stream, (int)foto.getSize());
        datos.setearParametros(2, id.toString());
        
        datos.ejecutarAccionParametros();
        datos.cerrarConexion();
        return "Editado con exito";
        
    }

}
