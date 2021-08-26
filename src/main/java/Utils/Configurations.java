package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Configurations {

    private Properties properties = null;
    private static Configurations instance = null;

    /** Private constructor */
    private Configurations () throws IOException {
        this.properties = new Properties();
        InputStream propertiesStream = getInputStream();
        if (propertiesStream != null) {
            this.properties.load(propertiesStream);
            propertiesStream.close();

        } else {
            System.err.println("file not found");
        }
    }

    private InputStream getInputStream(){
        String environment = System.getProperty("Environment");
        if (environment != null){
            return Thread.currentThread().getContextClassLoader().getResourceAsStream("environment/"+environment + ".properties");
        }
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(Constants.CONFIG);
    }

    /** Creates the instance is synchronized to avoid multithreads problems */
    private synchronized static void createInstance () throws IOException {
        if (instance == null) {
            instance = new Configurations ();
        }
    }

    /** Get the properties instance. Uses singleton pattern */
    public static Configurations getInstance(){
        // Uses singleton pattern to guarantee the creation of only one instance
        if(instance == null) {
            try {
                createInstance();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /** Get a property of the property file */
    public String getProperty(String key){
        String result = null;
        if(key !=null && !key.trim().isEmpty()){
            result = this.properties.getProperty(key);
        }
        return result;
    }

    /** Override the clone method to ensure the "unique instance" requirement of this class */
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }}