package be.ucll.taskmanager;

import be.ucll.taskmanager.domain.service.ServiceException;
import be.ucll.taskmanager.domain.service.Subtask;
import be.ucll.taskmanager.domain.service.Task;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class TaskTest {
    @Test(expected = ServiceException.class)
    @Transactional
    public void initializing_task_without_description_throws_exception(){
        new Task(null, LocalDateTime.now(),"title");
    }
    @Test(expected = ServiceException.class)
    @Transactional
    public void initializing_task_with_empty_description_throws_exception(){
        new Task("", LocalDateTime.now(),"title");
    }
    @Test(expected = ServiceException.class)
    @Transactional
    public void initializing_task_with_only_spaces_as_description_throws_exception(){
        new Task("       ", LocalDateTime.now(),"title");
    }
    @Test(expected = ServiceException.class)
    @Transactional
    public void initializing_task_without_title_throws_exception(){
        new Task("description", LocalDateTime.now(),null);
    }
    @Test(expected = ServiceException.class)
    @Transactional
    public void initializing_task_with_empty_string_as_title_throws_exception(){
        new Task("description", LocalDateTime.now(),"");
    }
    @Test(expected = ServiceException.class)
    @Transactional
    public void initializing_task_with_only_spaces_as_title_throws_exception(){
        new Task("description", LocalDateTime.now(),"     ");
    }
    @Test(expected = ServiceException.class)
    @Transactional
    public void initializing_task_without_date_throws_exception(){
        new Task("description", null,"titel");
    }
    @Test
    @Transactional
    public void add_subtask_adds_subtask(){
        List<Subtask> subtasks = new ArrayList<>();
        Task mainTask = new Task("desc", LocalDateTime.now(), "title");
        Subtask subtask = new Subtask("titel", "desc");
        subtasks.add(subtask);
        mainTask.addSubtask(subtask);
        assertEquals(mainTask.getSubtasks(), subtasks);
    }
    @Test
    @Transactional
    public void Task_getters_test(){
        Task task = new Task("desc", LocalDateTime.now(), "title");
        assertEquals(task.getDescription(), "desc");
        assertEquals(task.getTitle(), "title");
        assertNotNull(task.getUuid());
        assertNotNull(task.getDate());
    }
    @Test
    @Transactional
    public void Task_setters_test(){
        Task task = new Task("desc", LocalDateTime.now(), "title");
        task.setTitle("title2");
        task.setDescription("desc2");
        assertEquals(task.getDescription(), "desc2");
        assertEquals(task.getTitle(), "title2");
    }
    @Test
    @Transactional
    public void getDate_returns_something(){
        Task task = new Task("desc", LocalDateTime.now(), "title");
        assertNotNull(task.getDate());
    }
    @Test
    @Transactional
    public void testing_all_constructors(){
        Task task = new Task("desc", LocalDateTime.now(), "title");
        Task task2 = new Task(UUID.randomUUID(),"desc", LocalDateTime.now(),"title");
        assertEquals(task.getTitle(),"title");
        assertEquals(task2.getTitle(), "title");
        assertEquals(task.getDescription(), "desc");
        assertEquals(task2.getDescription(), "desc");
        assertNotNull(task2.getUuid());
    }
    @Test
    @Transactional
    public void getDate_test(){
        Task task = new Task("desc", LocalDateTime.of(1999, Month.MARCH,12, 12,12), "title");
        LocalDate date = LocalDate.of(1999, Month.MARCH, 12);
        LocalTime time = LocalTime.of(12,12);
        LocalDateTime march12 = LocalDateTime.of(date, time);
        assertEquals(task.getdate(), march12);
    }
    @Test
    @Transactional
    public void setDate_test(){
        Task task = new Task("desc", LocalDateTime.of(1998, Month.APRIL,1, 1,12), "title");
        LocalDate date = LocalDate.of(1999, Month.MARCH, 12);
        LocalTime time = LocalTime.of(12,12);
        LocalDateTime march12 = LocalDateTime.of(date, time);

        task.setDate(LocalDateTime.of(1999, Month.MARCH, 12, 12,12));
        assertEquals(task.getdate(), march12);
    }



}
