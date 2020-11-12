package Models;

public class PrefSuffDocument extends Document {
    private String[] prefSuffParts;

    public PrefSuffDocument(String fileId, FileMetadata metadata, Permission[] permissions , String[] prefSuffParts){
        super(fileId, metadata, permissions);
        this.prefSuffParts = prefSuffParts;
    }
}
