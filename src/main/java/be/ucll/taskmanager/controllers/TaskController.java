package be.ucll.taskmanager.controllers;

import be.ucll.taskmanager.DTO.SubtaskDTO;
import be.ucll.taskmanager.DTO.TaskDTO;
import be.ucll.taskmanager.service.Subtask;
import be.ucll.taskmanager.service.Task;
import be.ucll.taskmanager.service.TaskServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class TaskController {
    //DTO GEBRUIKEN IN CONTROLLER
    @Autowired
    private TaskServiceImp service;

    @GetMapping("/tasks")
    public String tasksPage(Model model){
        model.addAttribute("tasks", service.getAllTasks());
        return "tasks";
    }
    @GetMapping("/")
    public String indexPagina(Model model){
        return "redirect:/tasks";
    }
    @GetMapping("/tasks/{id}")
    public String getTaskDTO(Model model, @PathVariable("id") String id){
        TaskDTO taskDTO = service.getTaskDTO(UUID.fromString(id));
        model.addAttribute("task",taskDTO);
        return "taskDetail";
    }
    @GetMapping("/tasks/new")
    public String addTaskPage(Model model){
        model.addAttribute("task", new TaskDTO());
        return "addTask";
    }
    @PostMapping("/addTask")
    public String addTask(Model model, @ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addTask";
        }
        service.addTask(taskDTO);
        return "redirect:/tasks";
    }
    //What happens when you click on "EDIT TASK"
    @GetMapping("/tasks/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id){
        TaskDTO taskDTO = service.getTaskDTO(UUID.fromString(id));
        model.addAttribute("task", taskDTO);
        return "editTask";
    }
    //Method to be executed when you submit the edited task
    @PostMapping("/editTask")
    public String editTask(Model model, @ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "editTask";
        }
        service.editTask(taskDTO);
        return "redirect:/tasks/" + taskDTO.getUuid();
    }
    @GetMapping("/tasks/{id}/sub/create")
    public String pageCreateSubtask(Model model, @PathVariable("id") String id){
        Subtask subtask = new Subtask();
        Task mainTask = service.getTask(UUID.fromString(id));
        model.addAttribute("subtask", subtask);
        model.addAttribute("task",mainTask);
        return "addSubtask";
    }
    @PostMapping("/addSubtask")
    public String addSubtask(Model model, @RequestParam(value="id") String id, @ModelAttribute @Valid SubtaskDTO subtask, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("subtask", subtask);
            Task mainTask = service.getTask(UUID.fromString(id));
            model.addAttribute("task", mainTask);
            return "addSubtask";
        }
        UUID uuid = UUID.fromString(id);
        service.addSubtask(uuid, subtask);
        return "redirect:/tasks/" + id;
    }
}
