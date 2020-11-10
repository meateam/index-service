package Models;

import Enums.MessageEvent;

public class KafkaMessage {
    private MessageEvent messageEvent;
    private String fileId;
    private String ownerId;

    public KafkaMessage(MessageEvent messageEvent, String fileId, String ownerId){
        this.messageEvent = messageEvent;
        this.fileId = fileId;
        this.ownerId = ownerId;
    }

    public MessageEvent getMessageEvent(){
        return this.messageEvent;
    }

    public String getFileId(){
        return this.fileId;
    }

    public String getOwnerId(){
        return this.ownerId;
    }

    public void setMessageEvent(MessageEvent messageEvent){
        this.messageEvent = messageEvent;
    }

    public void setFileId(String fileId){
        this.fileId = fileId;
    }
}



