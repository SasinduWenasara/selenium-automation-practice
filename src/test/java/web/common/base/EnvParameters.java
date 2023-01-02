package web.common.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class EnvParameters {
    public static final String EXECUTION_ENV;
    public static final String WEB_BROWSER;
    public static final String ENV;
    private static Properties properties = new Properties();

    public EnvParameters(){
    }

    static {

        FileInputStream in = null;

        try {
            in = new FileInputStream("src/main/resources/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (System.getProperty("execution.env") != null && !System.getProperty("execution.env").equalsIgnoreCase("")) {
            EXECUTION_ENV = System.getProperty("execution.env");
        }else {
            EXECUTION_ENV = properties.getProperty("execution.env");
        }

        if (System.getProperty("BROWSER")!= null && System.getProperty("BROWSER").equalsIgnoreCase("")) {
            WEB_BROWSER =  System.getProperty("BROWSER");
        }
        else {
            WEB_BROWSER = properties.getProperty("Browser");
        }

        if (System.getProperty("Env") != null && !(System.getProperty("Env").equalsIgnoreCase(""))) {
            ENV = System.getProperty("Env");
        }else {
            ENV = properties.getProperty("Env");
        }
    }
}
