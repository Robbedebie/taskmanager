package be.ucll.taskmanager.service;

import be.ucll.taskmanager.DTO.SubtaskDTO;
import be.ucll.taskmanager.DTO.TaskDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskDTO> getAllTasks();
    TaskDTO addTask(TaskDTO taskDTO);
//    Task getTask(UUID id);
    TaskDTO getTaskDTO(UUID id);
    TaskDTO editTask(TaskDTO dto);
//    TaskDTO addSubtask(UUID uuid, SubtaskDTO subtaskDTO);
}
