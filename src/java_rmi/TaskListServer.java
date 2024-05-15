package java_rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TaskListServer {
    public static void main(String[] args) {
        try {
            TaskListService taskListService = new TaskListServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("TaskListService", taskListService);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

