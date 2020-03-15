package be.ucll.taskmanager.service;

import be.ucll.taskmanager.DTO.SubtaskDTO;
import be.ucll.taskmanager.DTO.TaskDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskDTO> getAllTasks();
    void addTask(TaskDTO taskDTO);
    Task getTask(UUID id);
    TaskDTO getTaskDTO(UUID id);
    void editTask(TaskDTO dto);
    void addSubtask(UUID uuid, SubtaskDTO subtask);
}
