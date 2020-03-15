package be.ucll.taskmanager.service;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
      this.description = description;
      this.date = date;
      this.title= title;
      this.uuid = id;
        if(subtasks == null){
            this.subtasks = new ArrayList<>();
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd yyyy 'at' HH:mm");
        this.dateString = dateTimeFormatter.format(date);
    }

    public Task(String description, LocalDateTime date, String title) {
        if (description == null || description.trim().isEmpty()) {
            throw new ServiceException("Description mag niet leeg zijn");
        }
        if (date == null) {
            throw new ServiceException("date is null");
        }
        this.uuid = UUID.randomUUID();
        this.description = description;
        this.date = date;
        this.title = title;
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
        this.description = description;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setTitle(String title){
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
}