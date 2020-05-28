package be.ucll.taskmanager;

import be.ucll.taskmanager.domain.DTO.TaskDTO;
import be.ucll.taskmanager.domain.service.Task;
import be.ucll.taskmanager.domain.service.TaskServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

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
        LocalDateTime dateTime = taskDTO.getDate();
        taskServiceImp.addTask(taskDTO);
        List<TaskDTO> tasks = taskServiceImp.getAllTasks();
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(1,tasks.size());
        TaskDTO taak = tasks.get(0);
        assertNotNull(taak);
        String desc = taskDTO.getDescription();
        String title = taskDTO.getTitle();
        LocalDateTime dateTime2 = taskDTO.getDate();
        assertEquals("test", title);
        assertEquals("desc", desc);
        assertEquals(dateTime, dateTime2);

    }
    @Test
    @Transactional
    public void get_TaskDTO_correctly_converts_task_to_taskDTO(){
        Task task = new Task("desc", LocalDateTime.now(), "title");
        UUID uuid = task.getUuid();
        LocalDateTime dateTime = task.getdate();
        taskServiceImp.saveTaskToRepo(task);
        assertNotNull(taskServiceImp.getTaskDTO(uuid));
        TaskDTO taskDTO = taskServiceImp.getTaskDTO(uuid);
        String desc = taskDTO.getDescription();
        String title = taskDTO.getTitle();
        LocalDateTime dateTime2 = taskDTO.getDate();
        assertNotNull(taskDTO);
        assertEquals(desc, "desc");
        assertEquals(title, "title");
        assertEquals(dateTime, dateTime2);
    }
    @Test
    @Transactional
    public void edit_TaskDTO_changes_DTO_values(){
        Task task = new Task("desc", LocalDateTime.now(), "title");
        UUID uuid = task.getUuid();
        LocalDateTime dateTime = task.getdate();
        taskServiceImp.saveTaskToRepo(task);
        TaskDTO taskDTO = new TaskDTO();
        LocalDateTime newdate = LocalDateTime.of(1999, Month.MARCH, 12,12,12);
        taskDTO.setUuid(uuid);
        taskDTO.setDate(newdate);
        taskDTO.setTitle("OtherTitle");
        taskDTO.setDescription("changedDescription");

        taskServiceImp.editTask(taskDTO);
        TaskDTO taskfromrepo = taskServiceImp.getTaskDTO(uuid);
        String desc = taskfromrepo.getDescription();
        String title = taskfromrepo.getTitle();
        LocalDateTime dateTime2 = taskfromrepo.getDate();
        assertEquals(dateTime2, newdate);
        assertEquals(title, "OtherTitle");
        assertEquals(desc, "changedDescription");
    }
}
