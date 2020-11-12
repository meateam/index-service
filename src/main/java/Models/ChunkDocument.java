package Models;

public class ChunkDocument extends Document{
    private String chunkContent;

    public ChunkDocument(String fileId, FileMetadata metadata, Permission[] permissions , String chunkContent){
        super(fileId, metadata, permissions);
        this.chunkContent = chunkContent;
    }

}
