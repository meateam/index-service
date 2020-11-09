package Models;

import Enums.MessageEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RabbitMessage {
    public final String fileId;
    public final MessageEvent event;

    public RabbitMessage(@JsonProperty("fileId") final String fileId,
                         @JsonProperty("event")final MessageEvent event) {
        System.out.println("CustomMessage");
        this.fileId = fileId;
        this.event = event;
    }

    public String getFileId() {
        return fileId;
    }

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



