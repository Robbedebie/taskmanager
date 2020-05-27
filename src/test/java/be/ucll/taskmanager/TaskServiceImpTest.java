package be.ucll.taskmanager;

import be.ucll.taskmanager.domain.DTO.TaskDTO;
import be.ucll.taskmanager.domain.service.TaskServiceImp;
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
    public void AddTask_adds_one_task(){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDate(LocalDateTime.now());
        taskDTO.setTitle("test");
        taskDTO.setDescription("desc");
        taskServiceImp.addTask(taskDTO);
        List<TaskDTO> tasks = taskServiceImp.getAllTasks();
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(1,tasks.size());
        TaskDTO taak = tasks.get(0);
        assertNotNull(taak);
        assertEquals("test", taskDTO.getTitle());
    }
    public void get_TaskDTO_correctly_converts_task_to_taskDTO(){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDate(LocalDateTime.now());
        taskDTO.setTitle("test");
        taskDTO.setDescription("desc");
        String desc = taskDTO.getDescription();
        String title = taskDTO.getTitle();
        LocalDateTime dateTime = taskDTO.getDate();
        taskServiceImp.addTask(taskDTO);
        TaskDTO task2 = taskServiceImp.getAllTasks().get(0);
        String desc2 = task2.getDescription();
        String title2 = task2.getTitle();
        LocalDateTime dateTime2 = task2.getDate();
        assertNotNull(task2);
        assertEquals(desc, desc2);
        assertEquals(title, title2);
        assertEquals(dateTime, dateTime2);
    }
}
