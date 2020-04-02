package be.ucll.taskmanager;

import be.ucll.taskmanager.service.ServiceException;
import be.ucll.taskmanager.service.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
public class TaskTest {
    @Test(expected = ServiceException.class)
    @Transactional
    public void initializing_task_without_description_throws_exception(){
        new Task(null, LocalDateTime.now(),"yeet");
    }
    @Test(expected = ServiceException.class)
    @Transactional
    public void initializing_task_without_title_throws_exception(){
        new Task("description", LocalDateTime.now(),null);
    }
}
