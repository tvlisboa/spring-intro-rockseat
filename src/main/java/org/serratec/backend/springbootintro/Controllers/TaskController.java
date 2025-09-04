package org.serratec.backend.springbootintro.Controllers;
import jakarta.servlet.http.HttpServletRequest;
import org.serratec.backend.springbootintro.Repository.TaskRepository;
import org.serratec.backend.springbootintro.Tasks.TaskModel;
import org.serratec.backend.springbootintro.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUsuario((UUID) idUser);

        /**
         * Validacao para criacao de tarefa
         * data inferior a data atual
         * ou data de entrega inferior a data atual
         */

        var currentDate = LocalDateTime.now();
            if(currentDate.isAfter(taskModel.getDataInicio()) || currentDate.isAfter(taskModel.getDataTermino())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data informada é invalida");
            }
            if(taskModel.getCreatedAt().isAfter(taskModel.getDataTermino())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data informada é invalida");
            }

        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tarefa cadastrada com sucesso");
    }

    /**
     * Busca as tarefas em repositorio
     * que sao listadas por id do usuario
     */

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUserId((UUID) idUser);
        return tasks;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable Long id, HttpServletRequest request) {
        var task = this.taskRepository.findById(id).orElseThrow(null);

        if(task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa nao encontrada");
        }

        var idUser = request.getAttribute("idUser");

        if(!taskModel.getIdUsuario().equals(idUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Acesso negado para essa tarefa");
        }

        Utils.copyNonNullProperties(taskModel, task);
        var taskUpdated = this.taskRepository.save(task);
        return ResponseEntity.ok().body(this.taskRepository.save(taskUpdated));
    }
}
