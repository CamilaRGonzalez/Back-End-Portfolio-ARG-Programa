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
    
    /*
    Obtiene todos los datos de la tabla educación, incluyendo los blobs que contienen las
    fotos. Los blobs se convierten a base 64 y se guardan en el objeto entidad como string
    */
    @Override
    public List<Educacion> getEducacion() throws SQLException, IOException{
        accesoDB datos = new accesoDB();
        manejoImagenes manejoImg = new manejoImagenes();
        List<Educacion> lista = new ArrayList<Educacion>();
        
        ResultSet resultado = datos.consultaSinParametros("select * from educacion");
        
        while(resultado.next()){
            Educacion educacion = new Educacion();
            
            //se obtiene el campo foto de la tabla
            Blob blob = resultado.getBlob("foto");
            
            if (resultado.wasNull()) {
                //si no hay foto en la db el atributo del objeto educacion se setea como null
                educacion.setFoto(null);
            } 
            else {
                //si hay foto en db el blob se convierte a base 64 y se guarda el string
                //preparado para ser utilizado como imagen en el front
               byte[] bytes = manejoImg.obtenerBytes(blob);
               String base64 = manejoImg.codificarBase64(bytes);
               String listo = "data:image/jpg;base64," + base64;
               educacion.setFoto(listo);
            }
        
            //se obtienen todos los datos restantes y se guardan en el objeto educacion que será enviado al front-end
            educacion.setId(resultado.getInt("id"));
            educacion.setTitulo(resultado.getString("titulo"));
            educacion.setInstitucion(resultado.getString("institucion"));
            
            //formatear fecha (la fecha se envía como string con el formato dd/MM/YYYY)
            Date fecha = resultado.getDate("fechainicio");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            
            educacion.setFechainicio(formatter.format(fecha));           
            educacion.setEstado(resultado.getString("estado"));

                        
            lista.add(educacion);
        }
        
        datos.cerrarConexion();
        return lista;
    }
    
    /*
        edita todos los campos de la tabla menos el de la foto
    */
    @Override
    public String editEducacion(Educacion educacion) throws SQLException {
        accesoDB datos = new accesoDB();
        
        datos.sentenciaConParametros("update educacion set titulo=?,institucion=?,fechainicio=?,estado=? where id=?");
        datos.setearParametros(1, educacion.getTitulo());
        datos.setearParametros(2, educacion.getInstitucion());
        datos.setearParametros(3, educacion.getFechainicio());
        datos.setearParametros(4, educacion.getEstado());
        datos.setearParametros(5,educacion.getId().toString());
        
        datos.ejecutarUpdateParametros();
        datos.cerrarConexion();
        return "editado con exito";
        
    }
    
    
    
    /*
    agrega una nueva fila a la tabla insertando todos los campos menos el campo foto
    */
    @Override
    public String agregarEducacion(Educacion educacion) throws SQLException, ParseException{
        accesoDB datos = new accesoDB();
        
        datos.sentenciaConParametros("insert into educacion (titulo,institucion,fechainicio,estado) values (?,?,?,?)");
        datos.setearParametros(1, educacion.getTitulo());
        datos.setearParametros(2, educacion.getInstitucion());
        datos.setearParametros(3, educacion.getFechainicio());
        datos.setearParametros(4, educacion.getEstado());
      
        datos.ejecutarUpdateParametros();
        datos.cerrarConexion();
        return "Editado con exito";
        
    }
    
    /*
    Elimina la fila correspondiente de la db
    */
    @Override
    public String deleteEducacion(Integer id) throws SQLException{
        accesoDB datos = new accesoDB();
        
        datos.consultaUpdateSinParametros("delete from educacion where id= "+id.toString());
        datos.cerrarConexion();
        return "Eliminado con exito";
    }
    
    /*
    inserta un blob en el campo foto de la tabla
    */
    @Override
    public String editFoto(MultipartFile foto, Integer id) throws FileNotFoundException, SQLException, IOException{
        accesoDB datos = new accesoDB();
        FileInputStream stream = (FileInputStream) foto.getInputStream();
        
        //insertar blob en base de datos
        datos.sentenciaConParametros("update educacion set foto = ? where id=?");       
        datos.setearParametroBinaryStream(1, stream, (int)foto.getSize());
        datos.setearParametros(2, id.toString());
        
        datos.ejecutarUpdateParametros();
        datos.cerrarConexion();
        return "Editado con exito";
        
    }

}
