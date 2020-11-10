import Enums.MessageEvent;
import Models.KafkaMessage;
import Models.FileMetadata;
import Services.*;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Main {

    public static String parseExample() throws Exception{
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler(-1);
        Metadata metadata = new Metadata();
        try{
            File initialFile = new File("/home/sraya/Desktop/sample_files/demo.docx");
            InputStream targetStream = new FileInputStream(initialFile);
            parser.parse(targetStream, handler, metadata);
            return handler.toString();
        }
        catch(Exception e){
            throw e;
        }
    }

    public static void processMessage(KafkaMessage message) throws Exception {
        try {
            MessageEvent messageEvent = message.getMessageEvent();
            String fileId = message.getFileId();
            String ownerId = message.getOwnerId();
            switch (messageEvent) {
                case CREATE:
                    EventService.processCreate(fileId,ownerId);
                case PERMISSION_CHANGE:
                    EventService.processPermissionChange(fileId,ownerId);
                case CONTENT_CHANGE:
                    EventService.processContentChange(fileId,ownerId);
                    break;
                case METADATA_CHANGE:
                    EventService.processMetadataChange(fileId,ownerId);
                    break;
                case DELETE:
                    EventService.processDelete(fileId,ownerId);
                    break;
            }
        }
        catch(Exception exception){
            throw exception;
        }
    }

    public static void main(String[]args){
        try{
            String str = parseExample();
            System.out.println(str);
            /**
            KafkaMessage message;
            // TODO always listen to messages from Kafka
            while(true){
                //TODO consume from Kafka
                message = KafkaService.consume();
                processMessage(message);
            }
            // String content = parseExample();
            // System.out.println(content);
             */

        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
