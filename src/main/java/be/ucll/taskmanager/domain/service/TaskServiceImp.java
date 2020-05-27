package be.ucll.taskmanager.domain.service;

import be.ucll.taskmanager.domain.DTO.SubtaskDTO;
import be.ucll.taskmanager.domain.DTO.TaskDTO;
import be.ucll.taskmanager.domain.db.SubtaskRepository;
import be.ucll.taskmanager.domain.db.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImp implements TaskService{
    private final TaskRepository repository;
    private final SubtaskRepository subtaskRepository;

    @Autowired
    public TaskServiceImp(TaskRepository repository, SubtaskRepository subtaskRepository) {
        this.repository = repository;
        this.subtaskRepository = subtaskRepository;
    }
    @Override
    public TaskDTO addTask(TaskDTO taskDTO) {
        String title = taskDTO.getTitle();
        String description = taskDTO.getDescription();
        LocalDateTime date = taskDTO.getDate();
        Task task = new Task(description, date, title);
        repository.save(task);
        return taskDTO;
    }
    private Task getTask(UUID id){
        if(repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
        else {throw new ServiceException("Task not found, or UUID is wrong");}
    }

    @Override
    public TaskDTO getTaskDTO(UUID id) {
        if(repository.findById(id).isPresent()){
            Task t = repository.findById(id).get();
            TaskDTO dto = new TaskDTO();
            dto.setDate(t.getdate());
            dto.setDescription(t.getDescription());
            dto.setTitle(t.getTitle());
            dto.setUuid(t.getUuid());
            dto.setSubtasks(t.getsubtasksDTO());
            return dto;
        }
        else {
            throw new IllegalArgumentException("ID not found");
        }
    }

    @Override
    public TaskDTO editTask(TaskDTO dto) {
        if(repository.findById(dto.getUuid()).isPresent()) {
            Task task = repository.findById(dto.getUuid()).get();
            task.editTaskParametersUsingTaskDTO(dto);
            repository.save(task);
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setDescription(task.getDescription());
            taskDTO.setTitle(task.getTitle());
            taskDTO.setDate(task.getdate());
            taskDTO.setUuid(task.getUuid());
            taskDTO.setSubtasks(task.getsubtasksDTO());
            return taskDTO;
        }
        else {throw new IllegalArgumentException("Ding dong UUID is wrong");}

    }
    public SubtaskDTO addSubtask(UUID uuid, SubtaskDTO subtaskDTO) {
        Subtask subtask = new Subtask();
        subtask.setDescription(subtaskDTO.getDescription());
        subtask.setTitle(subtaskDTO.getTitle());
        subtask.setId(subtaskDTO.getId());

        subtaskRepository.save(subtask);
        Task task = getTask(uuid);
        task.addSubtask(subtask);
        repository.save(task);
        return subtaskDTO;
    }
    public List<TaskDTO> getAllTasks(){
        List<TaskDTO> dtos = new ArrayList<>();
        for(Task t: repository.findAll()){
            TaskDTO dto = new TaskDTO();
            dto.setTitle(t.getTitle());
            dto.setDescription(t.getDescription());
            dto.setSubtasks(t.getsubtasksDTO());
            dto.setUuid(t.getUuid());
            dto.setDate(t.getdate());
            dtos.add(dto);
        }
        return dtos;
    }
}
