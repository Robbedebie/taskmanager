package be.ucll.taskmanager.service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Subtask {
    @Id
    @GeneratedValue
    private long id;
    @NotEmpty
    String title;
    @NotEmpty
    String description;

    public Subtask(){}

    public void setId(long id) {
        this.id = id;
    }

    public Subtask(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
