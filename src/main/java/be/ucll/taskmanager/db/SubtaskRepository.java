package be.ucll.taskmanager.db;

import be.ucll.taskmanager.service.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
}
