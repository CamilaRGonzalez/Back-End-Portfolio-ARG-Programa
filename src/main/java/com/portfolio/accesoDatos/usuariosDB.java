package com.portfolio.accesoDatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import logicaNegocio.Seguridad;


public class usuariosDB {
    
    public boolean login(String user, String pass) throws SQLException{
        accesoDB datos = new accesoDB();
        Integer id = 0;
        
        String password = Seguridad.doHashing(pass);
        
        datos.sentenciaConParametros("select * from usuarios where usuario= ? and pass = ?");
        datos.setearParametros(1, user);
        datos.setearParametros(2, password);
        
        ResultSet resultado = datos.ejecutarAccionParametros();
        
         while(resultado.next()){
             id = resultado.getInt("id");
         }
        
        if(id != 0){
            return true;
        }
        
        return false;
    }
    
    public boolean NuevoUsuario(String usuario, String pass) throws SQLException{       
        String password = Seguridad.doHashing(pass);
        String query = "insert into usuarios (usuario,pass,idpersona) values (\""+usuario+"\",\""+password+"\",1)";
        
        accesoDB datos = new accesoDB();
        datos.consultaSinParametros(query);        
        return true;     
    }
}

