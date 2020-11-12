package Services;

import Config.Config;
import Models.Document;
import Models.FileMetadata;
import Models.Permission;
import ucar.nc2.iosp.Layout;

import java.io.File;


public class EventService {

    public static void processDelete(String fileId, String ownerId) {
        try{
            ElasticService.delete(fileId,ownerId);
        }
        catch(Exception exception){
            throw exception;
        }
    }

    public static void processMetadataChange(String fileId , String ownerId){
        try{
            FileMetadata meatadata = DriveService.getMetadata(fileId);
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

            //metadata = DriveService.getMetadata(fileId);
            //ownerId = metadata.getOwner().getUserId();

            //permissions = DriveService.getPermissions(fileId);

            //DriveService.download(fileId, localFilePath);
            content = ParsingService.getContent(localFilePath);
            content = ParsingService.cleanContent(content);
            chunks = ChunkService.getChunks(content);
            preSuffArrays = ChunkService.getPreSuffArrays(content);
            System.out.println("hi");
//            for (String chunkContent : chunks) {
//                Document document = new Document(fileId, chunkContent, metadata, permissions);
//                ElasticService.indexDocument(document, ownerId);
//            }
//            FileService.deleteFile(localFilePath);
        }
        catch(Exception exception){
            throw exception;
        }
    }


    public static void processPermissionChange(String fileId,String ownerId){
        try{
            Permission[] permissions = DriveService.getPermissions(fileId);
            ElasticService.indexPermissions(fileId,permissions,ownerId);
        }
        catch(Exception exception){
            throw exception;
        }
    }
}
