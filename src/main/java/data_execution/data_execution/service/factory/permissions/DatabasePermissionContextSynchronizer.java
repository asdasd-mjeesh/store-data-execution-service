package data_execution.data_execution.service.factory.permissions;

public interface DatabasePermissionContextSynchronizer {
    void loadFromDatabase();
    void uploadToDatabase();
}
