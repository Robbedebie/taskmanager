package be.ucll.taskmanager;


import be.ucll.taskmanager.domain.service.Subtask;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;


@SpringBootTest
public class SubtaskTest {
    @Test
    @Transactional
    public void test_getters_and_setters(){
        Subtask subtask = new Subtask();
        subtask.setDescription("desc");
        subtask.setTitle("title");
        subtask.setId(1);
        String desc = subtask.getDescription();
        String id = Long.toString(subtask.getId());
        String title = subtask.getTitle();
        assertEquals(desc, "desc");
        assertEquals(id,"1");
        assertEquals(title, "title");
    }
    @Test
    @Transactional
    public void test_getters_and_settersgf(){
        Subtask subtask = new Subtask();
        subtask.setDescription("desc");
        subtask.setTitle("title");
        subtask.setId(1);
        assertEquals(subtask.getDescription(), "desc");
        assertEquals(subtask.getId(),1);
        assertEquals(subtask.getTitle(), "title");
    }
}
