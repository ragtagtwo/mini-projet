package java_rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class TaskListClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
            TaskListService taskListService = (TaskListService) registry.lookup("TaskListService");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Add Task");
                System.out.println("2. Remove Task");
                System.out.println("3. Display Tasks");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        System.out.print("Enter task to add: ");
                        String newTask = scanner.nextLine();
                        taskListService.addTask(newTask);
                        System.out.println("Task added successfully.");
                        break;
                    case 2:
                        System.out.print("Enter task to remove: ");
                        String taskToRemove = scanner.nextLine();
                        taskListService.removeTask(taskToRemove);
                        System.out.println("Task removed successfully.");
                        break;
                    case 3:
                        System.out.println("Task List:");
                        List<String> tasks = taskListService.getTaskList();
                        for (String task : tasks) {
                            System.out.println(task);
                        }
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

