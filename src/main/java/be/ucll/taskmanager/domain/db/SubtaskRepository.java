package be.ucll.taskmanager.domain.db;

import be.ucll.taskmanager.domain.service.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
}
