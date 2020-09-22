package Services;

import Models.FileMetadata;

public class ElasticService {

    public static void delete(String fileId, String index){
        //TODO remove all documents with fileId from index
    }

    public static void processMetadataChange(String fileId, FileMetadata fileMetadata, String index){
        // TODO update the permissions in all documents of fileId in ELastic
    }
    public static void processContentChange(String fileId,String[] slots ,FileMetadata fileMetadata, String index){
        // TODO index all the slots under the same fileId
    }


}
