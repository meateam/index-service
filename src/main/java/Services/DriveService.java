package Services;

import Config.Config;
import DriveStubs.grpc.*;
import Models.FileMetadata;
import Models.Permission;
import Models.User;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;

import java.util.Iterator;

public class DriveService {

    public static FileOuterClass.File getFileById(String fileId){
        ManagedChannel channel = ManagedChannelBuilder.forAddress(Config.DRIVE_URL, Config.FILE_SERVICE_PORT).usePlaintext().build();
        FileServiceGrpc.FileServiceBlockingStub fileStub = FileServiceGrpc.newBlockingStub(channel);
        FileOuterClass.GetByFileByIDRequest fileByIDRequest = FileOuterClass.GetByFileByIDRequest.newBuilder()
                .setId(fileId).build();
        FileOuterClass.File file = fileStub.getFileByID(fileByIDRequest);
        return file;
    }
    public static void download(String fileId, String path){
        // TODO download the file and save it in path

        ManagedChannel channel = ManagedChannelBuilder.forAddress(Config.DRIVE_URL, Config.DOWNLOAD_SERVICE_PORT).usePlaintext().build();
        DownloadGrpc.DownloadBlockingStub downloadStub = DownloadGrpc.newBlockingStub(channel);
        DownloadService.DownloadRequest downloadRequest = DownloadService.DownloadRequest.newBuilder()
                .setBucket(getFileById(fileId).getBucket()).setKey(getFileById(fileId).getKey()).build();
        Iterator<DownloadService.DownloadResponse> response = downloadStub.download(downloadRequest);
        while(response.hasNext()){
            response.next();
        }



        //        Object obj = downloadStub.download(downloadRequest);
//        downloadStub.download(downloadRequest).
//        by
//        for (DownloadService.DownloadResponse b: bytes){
//            System.out.println(b);
//        }

    }
    public static PermissionOuterClass.GetFilePermissionsResponse getPermissions(String fileId){
        ManagedChannel channel = ManagedChannelBuilder.forAddress(Config.DRIVE_URL, Config.PERMISSION_SERVICE_PORT).usePlaintext().build();
        PermissionGrpc.PermissionBlockingStub permissionStub = PermissionGrpc.newBlockingStub(channel);
        PermissionOuterClass.GetFilePermissionsRequest filePermissionsRequest = PermissionOuterClass.GetFilePermissionsRequest.newBuilder()
                .setFileID(fileId).build();
        PermissionOuterClass.GetFilePermissionsResponse permissions = permissionStub.getFilePermissions(filePermissionsRequest);
        return permissions;
    }

    public static UsersOuterClass.User getUser (String userId) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(Config.DRIVE_URL, Config.USER_SERVICE_PORT).usePlaintext().build();
        UsersGrpc.UsersBlockingStub userStub = UsersGrpc.newBlockingStub(channel);
        UsersOuterClass.GetByIDRequest userByIDRequest = UsersOuterClass.GetByIDRequest.newBuilder()
                .setId(userId).build();
        UsersOuterClass.GetUserResponse user = userStub.getUserByID(userByIDRequest);
        return user.getUser();
    }

    public static FileOuterClass.GetAncestorsResponse getAncestors (String fileId) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(Config.DRIVE_URL, Config.FILE_SERVICE_PORT).usePlaintext().build();
        FileServiceGrpc.FileServiceBlockingStub fileStub = FileServiceGrpc.newBlockingStub(channel);
        FileOuterClass.GetAncestorsRequest ancestorsRequest = FileOuterClass.GetAncestorsRequest.newBuilder()
                .setId(fileId).build();
        FileOuterClass.GetAncestorsResponse ancestors = fileStub.getAncestors(ancestorsRequest);
        return ancestors;
    }
}
