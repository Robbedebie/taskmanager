package be.ucll.taskmanager.domain.service;

import be.ucll.taskmanager.domain.DTO.SubtaskDTO;
import be.ucll.taskmanager.domain.DTO.TaskDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Task {
    @Id
    private UUID uuid;
    @NotEmpty
    @Size(min=3, max = 50)
    private String description;
    @NotEmpty
    private String title;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;
    private String dateString;

    @OneToMany
    private List<Subtask> subtasks;


    public Task(){
        this.uuid = UUID.randomUUID();
        if(subtasks == null){
            this.subtasks = new ArrayList<>();
        }
    }
    public Task(UUID id, String description, LocalDateTime date,String title){
        setDate(date);
        setDescription(description);
        setTitle(title);
        this.uuid = id;
        if(subtasks == null){
            this.subtasks = new ArrayList<>();
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd yyyy 'at' HH:mm");
        this.dateString = dateTimeFormatter.format(date);
    }

    public Task(String description, LocalDateTime date, String title) {
        setDescription(description);
        setDate(date);
        setTitle(title);
        this.uuid = UUID.randomUUID();
        if(subtasks == null){
            this.subtasks = new ArrayList<>();
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd yyyy 'at' HH:mm");
        this.dateString = dateTimeFormatter.format(date);
    }

    public List<Subtask> getSubtasks(){
        return subtasks;
    }

    public void addSubtask(Subtask subtask){
        subtasks.add(subtask);
    }

    public String getTimeString(){
        return ""+ this.date.getHour() + ":" + this.date.getMinute();
    }

    public String getDescription() {
    return description;
    }

    public LocalDateTime getdate() {
        return date;
    }

    public void setDescription(String description) {
        if(description == null || description.trim().isEmpty()){
            throw new ServiceException("Description mag niet leeg zijn");
        }
        this.description = description;
    }

    public void setDate(LocalDateTime date) {
        if(date == null){
            throw new ServiceException("Date can't be null");
        }
        this.date = date;
    }

    public void setTitle(String title){
        if(title == null || title.trim().isEmpty()){
            throw new ServiceException("Description mag niet leeg zijn");
        }
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDateString(){
        return dateString;
    }

    public String getDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd yyyy 'at' HH:mm");
        return dateTimeFormatter.format(date);
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Task{" +
                "uuid=" + uuid +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", date=" + date +
                '}';
    }
    public void editTaskParametersUsingTaskDTO(TaskDTO taskDTO){
        setDate(taskDTO.getDate());
        setDescription(taskDTO.getDescription());
        setTitle(taskDTO.getTitle());
    }
    public List<SubtaskDTO> getsubtasksDTO(){
        List<SubtaskDTO> dtos= new ArrayList<>();
        for(Subtask s: subtasks){
            SubtaskDTO dto = new SubtaskDTO();
            dto.setId(s.getId());
            dto.setTitle(s.getTitle());
            dto.setDescription(s.getDescription());
            dtos.add(dto);
        }
        return dtos;
    }
}
