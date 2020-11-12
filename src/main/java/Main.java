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

//    public static String parseExample() throws Exception{
//        AutoDetectParser parser = new AutoDetectParser();
//        BodyContentHandler handler = new BodyContentHandler(-1);
//        Metadata metadata = new Metadata();
//        try{
//            File initialFile = new File("/home/sraya/Desktop/sample_files/demo.docx");
//            InputStream targetStream = new FileInputStream(initialFile);
//            parser.parse(targetStream, handler, metadata);
//            return handler.toString();
//        }
//        catch(Exception e){
//            throw e;
//        }
//    }

    public static void processMessage(KafkaMessage message) throws Exception {
        try {
            MessageEvent messageEvent = message.getMessageEvent();
            String fileId = message.getFileId();
            String ownerId = message.getOwnerId();
            switch (messageEvent) {
                case CREATE:
                    System.out.print("create");
                    EventService.processCreate(fileId,ownerId);
                    break;
//                case PERMISSION_CHANGE:
//                    System.out.print("PERMISSION_CHANGE");
//                    EventService.processPermissionChange(fileId,ownerId);
//                    break;
//                case CONTENT_CHANGE:
//                    System.out.print("CONTENT_CHANGE");
//                    EventService.processContentChange(fileId,ownerId);
//                    break;
//                case METADATA_CHANGE:
//                    System.out.print("METADATA_CHANGE");
//                    EventService.processMetadataChange(fileId,ownerId);
//                    break;
//                case DELETE:
//                    System.out.print("DELETE");
//                    EventService.processDelete(fileId,ownerId);
//                    break;
            }
        }
        catch(Exception exception){
            throw exception;
        }
    }

    public static void main(String[]args){
        try{
            KafkaMessage message = new KafkaMessage(MessageEvent.CREATE, "876", "146");
            processMessage(message);
//            String str = parseExample();
//            System.out.println(str);
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
