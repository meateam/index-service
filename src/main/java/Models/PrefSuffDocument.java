package Models;

import Config.Config;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Random;

public class PrefSuffDocument extends Document {
    private String[] prefSuffParts;

    public PrefSuffDocument(String fileId, FileMetadata metadata, Permission[] permissions , String[] prefSuffParts){
        super(fileId, metadata, permissions);
        this.prefSuffParts = prefSuffParts;
    }

    public PrefSuffDocument(Document document){
        super(document);
        this.prefSuffParts = new String[0];
    }

    @Override
    public HashMap<String, Object> getHashMap() {
        HashMap<String,Object> map = super.getHashMap();
        map.put("prefSuffParts",prefSuffParts);
        return map;
    }

    public static PrefSuffDocument getRandom(FileMetadata metadata){
        Random rand = new Random();
        Faker faker = new Faker();
        PrefSuffDocument document = new PrefSuffDocument(Document.getRandom(metadata));
        String [] prefSuff = new String[rand.nextInt(Config.PRE_SUFF_PARTS_COUNT)+1];
        for (int i=0; i<prefSuff.length; i++) {
            prefSuff[i]= faker.shakespeare().hamletQuote();
        }
        document.prefSuffParts = prefSuff;
        return document;
    }
}
