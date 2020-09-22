package Models;

import Enums.MessageEvent;

public class KafkaMessage {
    private MessageEvent messageEvent;
    private String fileId;

    public KafkaMessage(MessageEvent messageEvent, String fileId){
        this.messageEvent = messageEvent;
        this.fileId = fileId;
    }

    public MessageEvent getMessageEvent(){
        return this.messageEvent;
    }

    public String getFileId(){
        return this.fileId;
    }

    public void setMessageEvent(MessageEvent messageEvent){
        this.messageEvent = messageEvent;
    }

    public void setFileId(String fileId){
        this.fileId = fileId;
    }
}



