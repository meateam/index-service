package Models;

import Enums.Role;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Random;

public class Permission {
    private User user;
    private Role role;

    public Permission(User user, Role role){
        this.user = user;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public User getUser() {
        return user;
    }

    public void setRole(Role role) {
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

    public static Permission getRandom(){
        Random rand = new Random();
        User user = User.getRandom();
        Role role = rand.nextBoolean() ? Role.READ : Role.WRITE;
        return new Permission(user, role);
    }
}
