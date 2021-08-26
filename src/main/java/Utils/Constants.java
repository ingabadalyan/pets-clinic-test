package Utils;

public class Constants {

    public static final String BASE_URL = Configurations.getInstance().getProperty("base.url");
    public static final String BASE_PATH_PET = Configurations.getInstance().getProperty("base.path.pets");
    public static final String BASE_PATH_OWNERS = Configurations.getInstance().getProperty("base.path.owners");

    static final String CONFIG = "environment/local.properties";

}