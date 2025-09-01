package org.serratec.backend.springbootintro.Tasks;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Id (Id_usuario)
 * Usuario que pertence
 * Descricao
 * Titulo
 * Data de inicio
 * Data termino
 * Prioridade da tarefa
 */


@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

    @Column(length = 55)
    private String tituloTarefa;
    private LocalDateTime dataInicio;
    private LocalDateTime dataTermino;
    private String prioridade;

    private UUID idUsuario;

    @CreationTimestamp
    private LocalDateTime createdAt;



}
