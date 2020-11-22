package Services;

import Config.Config;
import DriveStubs.grpc.FileOuterClass;
import DriveStubs.grpc.FileServiceGrpc;
import DriveStubs.grpc.UsersGrpc;
import DriveStubs.grpc.UsersOuterClass;
import Models.FileMetadata;
import Models.Permission;
import Models.User;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;

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
    }
    public static Permission[] getPermissions(String fileId){
        return null;
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
