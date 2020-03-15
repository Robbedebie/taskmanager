package be.ucll.taskmanager.service;

import be.ucll.taskmanager.DTO.TaskDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskDTO> getAllTasks();
    void addTask(Task task);
    Task getTask(UUID id);
    void editTask(UUID uuid, String title, String description, LocalDateTime localDateTime);
    void addSubtask(UUID uuid, Subtask subtask);
}
