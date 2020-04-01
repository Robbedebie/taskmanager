package be.ucll.taskmanager;

import be.ucll.taskmanager.DTO.TaskDTO;
import be.ucll.taskmanager.db.SubtaskRepository;
import be.ucll.taskmanager.db.TaskRepository;
import be.ucll.taskmanager.service.Task;
import be.ucll.taskmanager.service.TaskServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class TaskServiceImpTest {
    @Autowired
    private TaskServiceImp taskServiceImp;

    @Test
    @Transactional
    public void testAddTaskAddsOneTask(){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDate(LocalDateTime.now());
        taskDTO.setTitle("test");
        taskDTO.setDescription("yeet");
        taskServiceImp.addTask(taskDTO);

        List<TaskDTO> tasks = taskServiceImp.getAllTasks();
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(1,tasks.size());
        TaskDTO taak = tasks.get(0);
        assertNotNull(tasks);
    }
}
