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
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    /**
     * Tratamento de erro para preenchimento de campo
     */

    public void setTituloTarefa(String tituloTarefa) throws Exception{
        if(tituloTarefa.length() > 55){
            throw new Exception("Comprimento maximo de 50 caracteres");
        }
        this.tituloTarefa = tituloTarefa;
    }



}
