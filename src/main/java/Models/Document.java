package Models;


public class Document {
    private String fileId;
    private String chunkContent;
    private FileMetadata metadata;
    private Permission[] permissions;

    public Document(String fileId, String chunkContent, FileMetadata metadata, Permission[] permissions){
        this.fileId = fileId;
        this.chunkContent = chunkContent;
        this.metadata = metadata;
        this.permissions = permissions;
    }
}
