package be.ucll.taskmanager.db;

import be.ucll.taskmanager.service.Subtask;
import be.ucll.taskmanager.service.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface DbInterface {
    void addTask(Task task);

    Task getTask(UUID id);

    List<Task> getAllTasks();

    void editTask(UUID uuid, String title, String description, LocalDateTime localDateTime);

    void addSubtask(UUID uuid, Subtask subtask);
}
