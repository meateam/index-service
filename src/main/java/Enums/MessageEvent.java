package Enums;

public enum MessageEvent {
    CREATE, // File was uploaded
    PERMISSION_CHANGE, // Permissions were changed
    CONTENT_CHANGE, // File was updated
    DELETE, // File was deleted
    METADATA_CHANGE, // Change in title, etc
}
