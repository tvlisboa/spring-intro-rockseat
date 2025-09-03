package org.serratec.backend.springbootintro.Controllers;
import jakarta.servlet.http.HttpServletRequest;
import org.serratec.backend.springbootintro.Repository.TaskRepository;
import org.serratec.backend.springbootintro.Tasks.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @PostMapping("/")
    public TaskModel create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        System.out.println("Entrou no controller" + request.getAttribute("idUser"));
        var task = this.repository.save(taskModel);
        return taskModel;
    }
}
