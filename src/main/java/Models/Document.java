package Models;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Document {
    private String fileId;
    private FileMetadata metadata;
    private Permission[] permissions;

    public Document(String fileId, FileMetadata metadata, Permission[] permissions){
        this.fileId = fileId;
        this.metadata = metadata;
        this.permissions = permissions;
    }

    public HashMap<String,Object> getHashMap(){
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("fileId",fileId);
        map.put("metadata",metadata.getHashMap());

        ArrayList<HashMap<String,Object>> permissionList = new ArrayList<HashMap<String,Object>>();
        for (Permission permission: permissions) {
            permissionList.add(permission.getHashMap());
        }
        map.put("permissions",permissionList.toArray());
        return map;
    }
}
