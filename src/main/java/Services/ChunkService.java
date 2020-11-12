package Services;
import Config.*;

import java.util.ArrayList;
import java.util.List;

public class ChunkService {

    public static String [] getChunks(String content){
        List<String> chunks = new ArrayList<String>();
        System.out.println(content.length());
        String contentWithoutSpace = content.replaceAll("\\s+", " ");
        System.out.println(contentWithoutSpace.length());
//        System.out.println(contentWithoutSpace);
        String contentWithoutSigns = contentWithoutSpace.replaceAll("[^A-Za-z0-9- ]","");
        System.out.println(contentWithoutSigns.length());
        System.out.println(contentWithoutSigns);
//        for (int i=0; i<contentWithoutSpace.length(); i+=Config.CHUNK_SIZE){
//            String chunk = contentWithoutSpace.substring(i, Math.min(i + Config.CHUNK_SIZE, contentWithoutSpace.length()));
////            System.out.println("***");
////            System.out.println(chunk);
//            chunks.add(chunk);
//        }
//        for (int i=Config.CHUNK_SIZE; i<contentWithoutSpace.length(); i+=Config.CHUNK_SIZE){
//            String chunk = contentWithoutSpace.substring(i - Config.PART_CHUNK_SIZE , Math.min(i + Config.PART_CHUNK_SIZE, contentWithoutSpace.length()));
////            System.out.println("***");
////            System.out.println(chunk);
//            chunks.add(chunk);
//        }
//
//        String [] arrayChunks = new String[chunks.size()];
//        chunks.toArray(arrayChunks);
//        return arrayChunks;
        return null;
    }

}
