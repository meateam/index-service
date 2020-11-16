package Models;

import com.github.javafaker.Faker;
import com.github.javafaker.File;

import java.util.HashMap;
import java.util.Random;

public class FileMetadata {
    private String fileId;
    private String fileName;
    private String type;
    private long size;
    private User owner;
    private long createdAt;
    private long updatedAt;

    public FileMetadata(String fileId, String fileName, String type, long size, User owner, long createdAt, long updatedAt){
        this.fileId = fileId;
        this.fileName = fileName;
        this.type = type;
        this.size = size;
        this.owner = owner;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getFileId() {
        return fileId;
    }

    public long getSize() {
        return size;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public String getFileName() {
        return fileName;
    }

    public String getType() {
        return type;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
    public User getOwner(){
        return this.owner;
    }

    public HashMap<String,Object> getHashMap(){
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("fileId",fileId);
        map.put("fileName",fileName);
        map.put("type",type);
        map.put("size",size);
        map.put("owner",owner.getHashMap());
        map.put("createdAt",createdAt);
        map.put("updatedAt",updatedAt);
        return map;
    }

    public static FileMetadata getRandom(String fileId , User owner){
        Faker faker = new Faker();
        String [] types = {"docx", "pptx", "pdf", "xlsx"};
        String type = types[faker.random().nextInt(types.length)];
        String fileName = faker.animal().name()+"."+type;
        long size = faker.random().nextLong();
        long createdAt = Math.abs(faker.random().nextLong());
        long updatedAt = createdAt + faker.random().nextLong();
        FileMetadata metadata = new FileMetadata(fileId,fileName, type, size, owner, createdAt, updatedAt );
        return metadata;
    }
}
