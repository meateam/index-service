package Enums;

public enum MessageEvent {
    DELETE,  // File was deleted
    CREATE,
    CONTENT_CHANGE,   // File was uploaded or updated
    FILENAME_CHANGE,
    PERMISSION_CHANGE,
    METADATA_CHANGE  // Change in Permissions, title, etc
}
