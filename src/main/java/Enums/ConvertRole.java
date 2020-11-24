package Enums;

import DriveStubs.grpc.PermissionOuterClass;
import Models.User;


public class Role1 {

    public Role get(DriveStubs.grpc.PermissionOuterClass.Role role) {
        if (role.equals("READ"))
            return Role.READ;
        if (role.equals("WRITE"))
            return Role.WRITE;
        return Role.
    }
}
