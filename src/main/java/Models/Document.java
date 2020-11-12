package Models;


public class Document {
    private String fileId;
    private FileMetadata metadata;
    private Permission[] permissions;

    public Document(String fileId, FileMetadata metadata, Permission[] permissions){
        this.fileId = fileId;
        this.metadata = metadata;
        this.permissions = permissions;
    }
}
