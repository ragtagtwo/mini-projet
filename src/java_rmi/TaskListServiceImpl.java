package java_rmi;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TaskListServiceImpl extends UnicastRemoteObject implements TaskListService {
    private List<String> tasks;

    protected TaskListServiceImpl() throws RemoteException {
        super();
        tasks = new ArrayList<>();
    }

    @Override
    public void addTask(String task) throws RemoteException {
        tasks.add(task);
    }

    @Override
    public void removeTask(String task) throws RemoteException {
        tasks.remove(task);
    }

    @Override
    public List<String> getTaskList() throws RemoteException {
        return tasks;
    }
}

