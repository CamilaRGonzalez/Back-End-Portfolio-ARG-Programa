package logicaNegocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;


public class manejoImagenes {
    
     public File obtenerFile(String ruta){
        File file = new File(ruta);
        return file;
    }
    
    public FileInputStream obtenerFileInputStream(File file) throws FileNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(file);
        return fileInputStream;
    }
    
    public String codificarBase64(byte[] arr){
        String encoded = Base64.getEncoder().encodeToString(arr);
        return encoded;
    }
    
    public byte[] obtenerBytes(Blob imagen) throws IOException, SQLException{
        byte[] data;
        data = imagen.getBinaryStream().readAllBytes();
        return data;
    }
}
