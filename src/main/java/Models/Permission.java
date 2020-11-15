package Models;

import java.util.HashMap;

public class Permission {
    private User user;
    private String role;

    public Permission(User user, String role){
        this.user = user;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public User getUser() {
        return user;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HashMap<String,Object> getHashMap(){
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("user",user.getHashMap());
        map.put("role",role);
        return map;
    }
}
