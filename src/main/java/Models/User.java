package Models;

import java.util.HashMap;

public class User {
    private String userId;
    private String name;

    public User(String userId , String name){
        this.userId = userId;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getUserId (){
        return this.userId;
    }

    public HashMap<String,String> getHashMap(){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("userId",userId);
        map.put("name",name);
        return map;
    }
}
