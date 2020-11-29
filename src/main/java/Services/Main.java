package Services;

import Enums.MessageEvent;
import Models.RabbitMessage;
import Config.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class Main {

    private  static Logger log4jLoggeer ;

    public static void main(String[]args) throws IOException {
        SpringApplication.run(Main.class, args);
        BasicConfigurator.configure();
        PropertyConfigurator.configure("C:\\Users\\Liora Yacob\\index-service\\src\\main\\resources\\log4j.properties");
        log4jLoggeer = Logger.getLogger(Main.class);
    }

    @Async
    @Scheduled(fixedDelay = 60000)
    public void deleteOldLogFiles() //limit log files per folder every 24 hours
    {
        File errorDir = new File("C:\\Users\\Liora Yacob\\index-service\\src\\main\\java\\logs\\error");
        File infoDIr = new File("C:\\Users\\Liora Yacob\\index-service\\src\\main\\java\\logs\\info");
        deleteOldestLogFile(errorDir);
        deleteOldestLogFile(infoDIr);
    }

    public static void deleteOldestLogFile(File logDir){
        File[] logFiles = logDir.listFiles();
        long oldestDate = Long.MAX_VALUE;
        File oldestFile = null;
        int maxBackupLogFiles=1;

        if( logFiles != null && logFiles.length >maxBackupLogFiles){
            //delete oldest files after theres more than Max Backup Log files
            for(File f: logFiles){
                if(f.lastModified() < oldestDate){
                    oldestDate = f.lastModified();
                    oldestFile = f;
                }
            }
            if(oldestFile != null){
                boolean e=oldestFile.delete();
            }
        }
    }

    //Consumer
    @RabbitListener(queues = Config.QUEUE_NAME)
    public void receiveMessage(final RabbitMessage message) {
        try{
            log4jLoggeer.info("Received message: "+ message.toString());
            Main.processMessage(message);
            //just an example for logging errors
            if(message.event == MessageEvent.CREATE)
                throw new Exception("Test Error Log");
        }
        catch (Exception e){
            log4jLoggeer.error(e.toString());
        }
    }

    public static void processMessage(RabbitMessage message){
        MessageEvent event = message.getEvent();
        String fileId=message.getFileId();
        switch (event) {
            case DELETE:
                //TODO delete all documents of fileId from elastic
                //				ElasticService.delete(fileId, index);
                break;
            case CREATE:

                break;
            case CONTENT_CHANGE:
                //				downloadPath = null; //TODO fileId.type
                //TODO download the file
                //				DriveService.download(fileId , downloadPath);
                //TODO parse the file and get the content
                //				content = ParsingService.getContent(downloadPath);
                //TODO delete the file
                //				FileService.deleteFile(downloadPath);
                //TODO get the slots
                //				slots = SlotService.getSlots(content);
                //TODO index all the slots in elastic
                //				ElasticService.processContentChange(fileId,slots,fileMetadata,index);
                break;
            case PERMISSION_CHANGE:
                break;
            case METADATA_CHANGE:
                break;
        }
    }

    @Bean
    public TopicExchange tipsExchange() {
        return new TopicExchange(Config.EXCHANGE_NAME);
    }

    @Bean
    public Queue defaultParsingQueue() {
        return new Queue(Config.QUEUE_NAME);
    }

    @Bean
    public Binding queueToExchangeBinding() {
        return BindingBuilder.bind(defaultParsingQueue()).to(tipsExchange()).with(Config.ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper mapper=new ObjectMapper();
        //		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,true);
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

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
}