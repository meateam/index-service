import Config.Config;
import Enums.MessageEvent;
import Models.RabbitMessage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@SpringBootApplication
//@EnableScheduling
public class Main {

    private static final Logger log = LoggerFactory.getLogger(RabbitMessage.class);

    public static void main(String[]args){
        SpringApplication.run(Main.class, args);
    }

    //Consumer
    @RabbitListener(queues = Config.QUEUE_NAME)
    public void receiveMessage(final RabbitMessage message) {
        log.info("Received message , message is: {}", message.toString());
        Main.processMessage(message);
    }

    public static void processMessage(RabbitMessage message){
        log.info("processMessage");

        MessageEvent event = message.getEvent();
        String fileId=message.getFileId();

        switch (event) {
            case DELETE:
                log.info("DELETE case");
//				System.out.println("Delete event");
                //TODO delete all documents of fileId from elastic
//				ElasticService.delete(fileId, index);
                break;
            case CREATE:
                log.info("CREATE case");
                break;
            case CONTENT_CHANGE:
                log.info("CONTENT_CHANGE case");
//				System.out.println("Content change");
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
            case FILENAME_CHANGE:
                log.info("FILENAME_CHANGE case");
//				System.out.println("FileName change");
                //TODO change the permissions in all corresponding elastic documents
//				ElasticService.processMetadataChange(fileId , fileMetadata,index);
                break;
            case PERMISSION_CHANGE:
                log.info("PERMISSION_CHANGE case");
                break;
            case METADATA_CHANGE:
                log.info("METADATA_CHANGE case");
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

    // You can comment the two methods below to use the default serialization / deserialization (instead of JSON)
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return  rabbitTemplate;
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
