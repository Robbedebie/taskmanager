package be.ucll.taskmanager;

import be.ucll.taskmanager.domain.DTO.SubtaskDTO;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;


@SpringBootTest
public class SubtaskDTOTest {
    @Test
    @Transactional
    public void test_getters_and_setters(){
        SubtaskDTO subtaskDTO = new SubtaskDTO();
        subtaskDTO.setDescription("desc");
        subtaskDTO.setId(1);
        subtaskDTO.setTitle("title");
        assertEquals(subtaskDTO.getDescription(), "desc");
        assertEquals(subtaskDTO.getId(), 1);
        assertEquals(subtaskDTO.getTitle(), "title");
    }
}
