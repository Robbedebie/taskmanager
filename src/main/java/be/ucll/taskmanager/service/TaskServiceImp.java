package be.ucll.taskmanager.service;

import be.ucll.taskmanager.DTO.SubtaskDTO;
import be.ucll.taskmanager.DTO.TaskDTO;
import be.ucll.taskmanager.db.DbInterface;
import be.ucll.taskmanager.db.SubtaskRepository;
import be.ucll.taskmanager.db.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImp implements TaskService{
    private final TaskRepository repository;
    private final SubtaskRepository subtaskRepository;

    @Autowired
    public TaskServiceImp(TaskRepository repository, SubtaskRepository subtaskRepository) {
        this.repository = repository;
        this.subtaskRepository = subtaskRepository;
    }

    //TODO maak addtask(TaskDTO task)
    @Override
    public void addTask(TaskDTO taskDTO) {
        String title = taskDTO.getTitle();
        String description = taskDTO.getDescription();
        LocalDateTime date = taskDTO.getDate();
        Task task = new Task(description, date, title);
        repository.save(task);
    }
    @Override
    public Task getTask(UUID id){
        if(repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
        else {throw new ServiceException("Ding dong UUID is wrong. Or Arne can't find your task...");}
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
            return dto;
        }
        else {
            throw new IllegalArgumentException("ID not found");
        }
    }

    @Override
    public void editTask(TaskDTO dto) {
        if(repository.findById(dto.getUuid()).isPresent()) {
            Task task = repository.findById(dto.getUuid()).get();
            task.editTaskParametersUsingTaskDTO(dto);
            repository.save(task);
        }
        else {throw new IllegalArgumentException("Ding dong UUID is wrong");}
    }

    @Override
    public void addSubtask(UUID uuid, SubtaskDTO subtaskDTO) {
        Subtask subtask = new Subtask();
        subtask.setDescription(subtaskDTO.getDescription());
        subtask.setTitle(subtaskDTO.getTitle());
        subtask.setId(subtaskDTO.getId());

        subtaskRepository.save(subtask);
        Task task = getTask(uuid);
        task.addSubtask(subtask);
        repository.save(task);
    }
    public List<TaskDTO> getAllTasks(){
        return repository.findAll().stream().map( t -> {
            TaskDTO dto = new TaskDTO();
            dto.setDate(t.getdate());
            dto.setDescription(t.getDescription());
            dto.setTitle(t.getTitle());
            dto.setUuid(t.getUuid());
            return dto;
                }).collect(Collectors.toList());
    }
}
