package be.ucll.taskmanager.db;

import be.ucll.taskmanager.service.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

}
