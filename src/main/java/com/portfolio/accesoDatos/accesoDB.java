package com.portfolio.accesoDatos;

import java.io.FileInputStream;
import java.sql.*;

public class accesoDB {
    private Connection conexion;
    private PreparedStatement sentenciaParametros;
    private Statement sentencia;
    private CallableStatement storedProcedure;
    private ResultSet resultado;
    
    public accesoDB() throws SQLException {
        //this.conexion = DriverManager.getConnection ("jdbc:mysql://localhost:3306/portfolio?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "root");
        this.conexion = DriverManager.getConnection ("jdbc:mysql://uaz1abkvvn6ejlub:Kj3Kb8TmfDBWOntS7Sxh@b313lgcvty7urgg9xcnj-mysql.services.clever-cloud.com:3306/b313lgcvty7urgg9xcnj","uaz1abkvvn6ejlub", "Kj3Kb8TmfDBWOntS7Sxh");
    }
    
    public void sentenciaConParametros(String consulta) throws SQLException{
        this.sentenciaParametros = conexion.prepareStatement(consulta);
    }
    
    public void setearParametros(int numero, String parametro) throws SQLException{
        this.sentenciaParametros.setString(numero, parametro);
    }
    
    public void setParamFecha(int numero, Date fecha) throws SQLException{
        this.sentenciaParametros.setDate(numero, fecha);
    }
    
    public void ejecutarUpdateParametros() throws SQLException{
        this.sentenciaParametros.executeUpdate();
    }
    
    public ResultSet ejecutarAccionParametros() throws SQLException{
        this.resultado = this.sentenciaParametros.executeQuery();
        return this.resultado;
    }
    
    public ResultSet consultaSinParametros(String consulta) throws SQLException{
        this.sentencia = conexion.createStatement();
        resultado = this.sentencia.executeQuery (consulta);       
        return resultado;
    }
    
    public void consultaUpdateSinParametros(String consulta) throws SQLException{
        this.sentencia = conexion.createStatement();
        this.sentencia.executeUpdate(consulta);       
    }
    
    public void cerrarConexion() throws SQLException{
        this.conexion.close();
    }
    
    public void sentenciaStoredProcedure(String storedProcedure) throws SQLException{
        this.storedProcedure= conexion.prepareCall("{call " +storedProcedure + "}");
    }
    
    public void setearParametroSP(int numero, String parametro) throws SQLException{
        this.storedProcedure.setString(numero, parametro);
    }
    
    public void setearParametroBinaryStream(Integer posicion,FileInputStream fileIS,Integer tamanioFile) throws SQLException{
        this.sentenciaParametros.setBinaryStream(posicion, fileIS, tamanioFile);
    }
    
    public ResultSet ejecutarSP() throws SQLException{
        resultado = this.storedProcedure.executeQuery();       
        return resultado;
    }
}
