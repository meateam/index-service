package Models;

import Enums.Role;
import Services.DriveService;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Folder {

    private String name;
    private String id;

    public Folder(String name, String id){
        this.name = name;
        this.id = id;
    }

    public static Folder getRandom(){
        Faker faker = new Faker();
        String name = faker.color().name();
        String id = faker.idNumber().valid();
        return new Folder(name, id);
    }

    public HashMap<String,String> getHashMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name",name);
        map.put("id",id);
        return map;
    }

    public static Object[] getHashMapArray(Folder[] folders){
        ArrayList<HashMap<String,String>> mapList = new ArrayList<HashMap<String,String>>();
        for(int i = 0 ; i < folders.length ; i++){
            mapList.add(folders[i].getHashMap());
        }
        return mapList.toArray();
    }

    public static Folder getFolder(String id){
        String name = FileMetadata.getFileNameById(id);
        return new Folder(name, id);
    }
}
