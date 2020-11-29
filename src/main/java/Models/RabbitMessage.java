package Models;

import Enums.MessageEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RabbitMessage {
    public final String fileId;
    public final MessageEvent event;

    public RabbitMessage(@JsonProperty("fileId") final String fileId,
                         @JsonProperty("event")final MessageEvent event) throws ParseException {
        System.out.println("CustomMessage");
        String s="app.log.2020-11-24";
        String sub=s.substring(8,18);
        System.out.println(sub);
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sub);
        System.out.println(sub+"\t"+date1);

        this.fileId = fileId;
        this.event = event;
    }

    public String getFileId() { return fileId;}

    public MessageEvent getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return "Message{" +
                "fileId='" + fileId + '\'' +
                ", event=" + event +
                '}';
    }
}



