package Config;
public class Config {
    public static String DOWNLOAD_FOLDER_PATH = "/home/sraya/IdeaProjects/IndexService/src/main/java/downloadFiles";
    public static String ES_HOST = "localhost:9200";
    public static String KAFKA_HOST = "localhost";
    public static String DRIVE_URL = "localhost";
    public static int FILE_SERVICE_PORT = 8083;
    public static int USER_SERVICE_PORT = 8086;
    public static int CHUNK_SIZE = 1000;
    public static int PRE_SUFF_SIZE = 100;
    public static int PRE_SUFF_PARTS_COUNT= CHUNK_SIZE / PRE_SUFF_SIZE;

}
