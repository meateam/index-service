import Enums.MessageEvent;
import Models.*;
import Services.*;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
//import org.graalvm.compiler.lir.LIRInstruction;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;

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
                case PERMISSION_CHANGE:
                    System.out.print("FILE_PERMISSION_CHANGE");
                    EventService.processPermissionChange(fileId,ownerId);
                    break;
                case CONTENT_CHANGE:
                    System.out.print("CONTENT_CHANGE");
                    EventService.processContentChange(fileId,ownerId);
                    break;
                case METADATA_CHANGE:
                    System.out.print("METADATA_CHANGE");
                    EventService.processMetadataChange(fileId,ownerId);
                    break;
                case DELETE:
                    System.out.print("DELETE");
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

            Document.indexRandomDocuments(3,3);
//            DriveService.getAncestors("5fba241eac105400135301ad");
//            DriveService.getPermissions("5fba241eac105400135301ad");
//            Permission.getPermissionsArray("5fba241eac105400135301ad");
//            DriveService.getMetadata("5fb3cda3a865ab0012099d32");
           // Document.indexRandomDocuments(3,3);

//            ElasticService.delete("895-64-0360","test");
            //ElasticService.indexMetadata("056-34-7491", FileMetadata.getRandom("056-34-7491", User.getRandom()), "test");
           // System.out.println("hi");

//            KafkaMessage message = new KafkaMessage(MessageEvent.CREATE, "876", "146");
//            processMessage(message);
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
