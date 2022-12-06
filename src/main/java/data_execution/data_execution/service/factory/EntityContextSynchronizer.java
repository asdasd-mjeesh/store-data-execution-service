package data_execution.data_execution.service.factory;

public interface EntityContextSynchronizer {
    void loadFromDatabase();
    void uploadToDatabase();
}
