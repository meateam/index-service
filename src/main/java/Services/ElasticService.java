package Services;

import Models.FileMetadata;
import Models.Permission;
public class ElasticService {

    public static void delete(String fileId, String index){
        //TODO remove all documents with fileId from index
    }

    public static void indexMetadata(String fileId, FileMetadata fileMetadata, String index){
        // TODO update the metadata in all documents of fileId in Elastic
    }

    public static void indexChunk(String fileId, String chunkContent, String index){
        // TODO update the permissions in all documents of fileId in Elastic
    }

    public static void indexPermissions(String fileId, Permission[] permissions, String index){
        // TODO update the permissions in all documents of fileId in Elastic
    }



}
