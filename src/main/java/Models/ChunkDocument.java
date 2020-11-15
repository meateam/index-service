package Models;

import java.util.HashMap;

public class ChunkDocument extends Document{
    private String chunkContent;

    public ChunkDocument(String fileId, FileMetadata metadata, Permission[] permissions , String chunkContent){
        super(fileId, metadata, permissions);
        this.chunkContent = chunkContent;
    }

    @Override
    public HashMap<String, Object> getHashMap() {
        HashMap<String,Object> map = super.getHashMap();
        map.put("chunkContent",chunkContent);
        return map;
    }
}
