package be.ucll.taskmanager.domain.db;

import be.ucll.taskmanager.domain.service.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

}
