package Services;

import Config.Config;
import Models.*;
import ucar.nc2.iosp.Layout;

import java.io.File;
import java.io.IOException;


public class EventService {

    public static void processDelete(String fileId, String ownerId) throws IOException {
        try{
            ElasticService.delete(fileId,ownerId);
        }
        catch(Exception exception){
            throw exception;
        }
    }

    public static void processMetadataChange(String fileId , String ownerId) throws IOException {
        try{
            FileMetadata meatadata = FileMetadata.getMetadata(fileId);
            ElasticService.indexMetadata(fileId,meatadata,ownerId);
        }
        catch(Exception exception){
            throw exception;
        }
    }

    public static void processContentChange(String fileId,String ownerId) throws Exception {
        try{
            ElasticService.delete(fileId,ownerId);
            processCreate(fileId,ownerId);
        }
        catch(Exception exception){
            throw exception;
        }

    }

    public static void processCreate(String fileId,String ownerId) throws Exception {
        try {
            String localFilePath = Config.DOWNLOAD_FOLDER_PATH + "/example.docx";
            String content;
            String[] chunks;
            FileMetadata metadata;
            Permission[] permissions;
            String [][] preSuffArrays;

            metadata = FileMetadata.getMetadata(fileId);
            ownerId = metadata.getOwner().getUserId();

            permissions = Permission.getPermissionsArray(fileId);

            DriveService.download(fileId, localFilePath);
            content = ParsingService.getContent(localFilePath);
            content = ParsingService.cleanContent(content);
            chunks = ChunkService.getChunks(content);
            preSuffArrays = ChunkService.getPreSuffArrays(content);
            for (String chunkContent : chunks) {
                Document document = new ChunkDocument(fileId, metadata, permissions, chunkContent);
                ElasticService.indexDocument(document, ownerId);
            }

            for (String[] prefSuffParts : preSuffArrays){
                Document document = new PrefSuffDocument(fileId, metadata, permissions, prefSuffParts);
                ElasticService.indexDocument(document, ownerId);
            }
            FileService.deleteFile(localFilePath);
        }
        catch(Exception exception){
            throw exception;
        }
    }


    public static void processPermissionChange(String fileId,String ownerId) throws IOException {
        try{
            Permission[] permissions = Permission.getPermissions(fileId);
            ElasticService.indexPermissions(fileId,permissions,ownerId);
        }
        catch(Exception exception){
            throw exception;
        }
    }

}
