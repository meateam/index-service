package Models;

import java.util.HashMap;

public class PrefSuffDocument extends Document {
    private String[] prefSuffParts;

    public PrefSuffDocument(String fileId, FileMetadata metadata, Permission[] permissions , String[] prefSuffParts){
        super(fileId, metadata, permissions);
        this.prefSuffParts = prefSuffParts;
    }

    @Override
    public HashMap<String, Object> getHashMap() {
        HashMap<String,Object> map = super.getHashMap();
        map.put("prefSuffParts",prefSuffParts);
        return map;
    }
}
