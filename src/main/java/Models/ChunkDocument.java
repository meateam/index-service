package Models;

import Config.Config;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Random;

public class ChunkDocument extends Document{
    private String chunkContent;

    public ChunkDocument(String fileId, FileMetadata metadata, Permission[] permissions , String chunkContent){
        super(fileId, metadata, permissions);
        this.chunkContent = chunkContent;
    }

    public ChunkDocument(Document document){
        super(document);
        this.chunkContent = null;
    }

    @Override
    public HashMap<String, Object> getHashMap() {
        HashMap<String,Object> map = super.getHashMap();
        map.put("chunkContent",chunkContent);
        return map;
    }

    public static ChunkDocument getRandom(FileMetadata metadata, Permission [] permissions){
        Faker faker = new Faker();
        ChunkDocument document = new ChunkDocument (Document.getRandom(metadata, permissions));
        document.chunkContent = faker.rickAndMorty().quote();
        return document;
    }
}
