package Services;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ParsingService {

    public static String getContent(String path) throws Exception {
        //TODO return the file content using Tika
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler(-1);
        Metadata metadata = new Metadata();
        try{
            File initialFile = new File(path);
            InputStream targetStream = new FileInputStream(initialFile);
            parser.parse(targetStream, handler, metadata);
            return handler.toString();
        }
        catch(Exception e){
            throw e;
        }
    }

}
