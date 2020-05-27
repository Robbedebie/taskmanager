package be.ucll.taskmanager.domain.DTO;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskDTO {
    private UUID uuid;
    @NotEmpty
    @Size(min=3, max = 50)
    private String description;
    @NotEmpty
    private String title;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;
    private List<SubtaskDTO> subtasks;

    public TaskDTO() {
        this.subtasks = new ArrayList<>();
    }

    public void setSubtasks(List<SubtaskDTO> subtasks) {
        this.subtasks = subtasks;
    }
    public List<SubtaskDTO> getSubtasks() {
        return subtasks;
    }

    public String getDateString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd yyyy ':' HH:mm");
        return dateTimeFormatter.format(date);
    }


    public List<SubtaskDTO> getSubtasksDTO() {
        return subtasksDTO;
    }

    public void setSubtasksDTO(List<SubtaskDTO> subtasksDTO) {
        this.subtasksDTO = subtasksDTO;
    }

    private List<SubtaskDTO> subtasksDTO;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
