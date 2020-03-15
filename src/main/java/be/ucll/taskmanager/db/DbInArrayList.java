package be.ucll.taskmanager.db;

import be.ucll.taskmanager.service.Subtask;
import be.ucll.taskmanager.service.Task;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class DbInArrayList implements DbInterface {
    List<Task> tasks;
    
    public DbInArrayList(){
        tasks = new ArrayList<>();
        tasks.add(new Task("Implement next 3 stories", LocalDateTime.of(2022,03,12,12,30), "InternetProgrammeren Minor"));
        tasks.add(new Task("Ween een beetje", LocalDateTime.of(2020,06,05,19,22), "Routing & Switching"));
    }
    @Override
    public void addTask(Task task){
        tasks.add(task);
    }
    @Override
    public Task getTask(UUID id) {
        for(Task t: tasks) {
            if (t.getUuid().equals(id)) {
                return t;
            }
        }
        return null;
    }
    @Override
    public List<Task> getAllTasks(){
        return tasks;
    }

    @Override
    public void editTask(UUID uuid, String title, String description, LocalDateTime localDateTime) {
        Task toBeEdited = getTask(uuid);
        toBeEdited.setTitle(title);
        toBeEdited.setDescription(description);
        toBeEdited.setDate(localDateTime);
        tasks.set(tasks.indexOf(toBeEdited),toBeEdited);
    }

    @Override
    public void addSubtask(UUID uuid, Subtask subtask) {
        Task mainTask = getTask(uuid);
        mainTask.addSubtask(subtask);
        tasks.set(tasks.indexOf(mainTask), mainTask);
    }
}
