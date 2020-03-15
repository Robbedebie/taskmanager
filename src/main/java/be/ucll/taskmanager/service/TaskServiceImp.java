package be.ucll.taskmanager.service;

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

    //TODO addtask(TAskDTO task)
    @Override
    public void addTask(Task task) {
        repository.save(task);
    }

    @Override
    public Task getTask(UUID id) {
        if(repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
        else {
            throw new IllegalArgumentException("ID not found");
        }
    }

    @Override
    public void editTask(UUID uuid, String title, String description, LocalDateTime localDateTime) {
        Task task = getTask(uuid);
        task.setTitle(title);
        task.setDescription(description);
        task.setDate(localDateTime);
        repository.save(task);
    }

    @Override
    public void addSubtask(UUID uuid, Subtask subtask) {
        subtaskRepository.save(subtask);
        Task task = getTask(uuid);
        task.addSubtask(subtask);
        repository.save(task);
    }


//    @Override
//    public List<Task> getAllTasks(){
//        return repository.findAll();
//    }
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
