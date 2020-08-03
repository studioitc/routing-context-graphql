package helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {

    public static Properties getTenantProperties(String fileName) {
        InputStream is = null;
        Properties prop = null;
        try {
            is = PropertiesHelper.class.getClassLoader().getResourceAsStream(fileName+".properties");
            prop = new Properties();
            prop.load(is);
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch(IOException ioe){
                    System.out.println(ioe.getMessage());
                }
            }
        }
        return prop;
    }
}
