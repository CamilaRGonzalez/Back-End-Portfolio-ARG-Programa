package com.portfolio.accesoDatos;

import com.portfolio.entidad.Persona;
import com.portfolio.repositorios.IPersona;
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

public class personaDB implements IPersona{
    
    public personaDB(){}
    
    @Override
    public Persona getPersonas(Integer id) throws SQLException, IOException{
        accesoDB datos = new accesoDB();
        manejoImagenes manejoImg = new manejoImagenes();
        List<Persona> lista = new ArrayList<Persona>();
        
        ResultSet resultado = datos.consultaSinParametros("select * from persona where id= " + id.toString());
               
        while(resultado.next()){
            Persona persona = new Persona();
            
            Blob blob = resultado.getBlob("foto");
            
            if (resultado.wasNull()) {
                persona.setFoto(null);
            } 
            else {
               byte[] bytes = manejoImg.obtenerBytes(blob);
               String base64 = manejoImg.codificarBase64(bytes);
               String listo = "data:image/jpg;base64," + base64;
               persona.setFoto(listo);
            }
        
            persona.setId(resultado.getInt("id"));
            persona.setNombreyapellido(resultado.getString("nombreyapellido"));
            persona.setTitulo(resultado.getString("titulo"));
            persona.setSobremi(resultado.getString("sobremi"));
                        
            lista.add(persona);
        }
        
        datos.cerrarConexion();
        return lista.get(0);
    }
    
    @Override
    public String editPersona(Persona persona) throws SQLException{
        accesoDB datos = new accesoDB();
        
        datos.sentenciaConParametros("update persona set nombreyapellido = ?, titulo = ?, sobremi = ? where id = ?");
       
        datos.setearParametros(1, persona.getNombreyapellido());
        datos.setearParametros(2, persona.getTitulo());
        datos.setearParametros(3, persona.getSobremi());
        datos.setearParametros(4, persona.getId().toString());
        
        datos.ejecutarUpdateParametros();
        datos.cerrarConexion();
        
        return "Editado con exito";
    }
    
    @Override
    public String editFoto(MultipartFile foto) throws FileNotFoundException, SQLException, IOException{
        accesoDB datos = new accesoDB();
        //manejoImagenes manejoImg = new manejoImagenes();
        FileInputStream stream = (FileInputStream) foto.getInputStream();
        
        //insertar blob en base de datos
        datos.sentenciaConParametros("update persona set foto = ?");       
        datos.setearParametroBinaryStream(1, stream, (int)foto.getSize());
        datos.ejecutarUpdateParametros();
        datos.cerrarConexion();
        return "Editado con exito";
        
    }
    
}

