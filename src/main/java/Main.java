import Enums.MessageEvent;
import Models.KafkaMessage;
import Models.FileMetadata;
import Services.*;

public class Main {

    /**
    public static String parseExample() throws Exception{
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler(-1);
        Metadata metadata = new Metadata();
        try{
            File initialFile = new File("/home/sraya/Desktop/sample_files/bbb.docx");
            InputStream targetStream = new FileInputStream(initialFile);
            parser.parse(targetStream, handler, metadata);
        }
        catch(Exception e){
            throw e;
        }
        return handler.toString();
    }
    */

    public static void processMessage(KafkaMessage message){

        MessageEvent messageEvent = message.getMessageEvent();
        String fileId = message.getFileId();
        // TODO get the metadata of the file from Drive
        FileMetadata fileMetadata = null;
        // TODO get the ES index
        String index = null, content = null, downloadPath = null;
        String [] slots = null;

        switch (messageEvent) {
            case CONTENT_CHANGE:
                System.out.println("Content change");
                downloadPath = null; //TODO fileId.type
                //TODO download the file
                DriveService.download(fileId , downloadPath);
                //TODO parse the file and get the content
                content = ParsingService.getContent(downloadPath);
                //TODO delete the file
                FileService.deleteFile(downloadPath);
                //TODO get the slots
                slots = SlotService.getSlots(content);
                //TODO index all the slots in elastic
                ElasticService.processContentChange(fileId,slots,fileMetadata,index);
                break;
            case METADATA_CHANGE:
                System.out.println("Metadata change");
                //TODO change the permissions in all corresponding elastic documents
                ElasticService.processMetadataChange(fileId , fileMetadata,index);
                break;
            case DELETE:
                System.out.println("Delete event");
                //TODO delete all documents of fileId from elastic
                ElasticService.delete(fileId, index);
                break;
        }
    }

    public static void main(String[]args){
        try{
            KafkaMessage message;
            // TODO always listen to messages from Kafka
            while(true){
                //TODO consume from Kafka
                message = KafkaService.consume();
                processMessage(message);
            }
            // String content = parseExample();
            // System.out.println(content);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
