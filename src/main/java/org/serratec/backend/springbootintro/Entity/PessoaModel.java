package org.serratec.backend.springbootintro.Entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Getters and Setters
 * O GET e usado para usar a informacao do atributo
 * o SET e usado para atribuir uma informacao ao atributo
 */

@Data
@Entity(name = "tb_users")
public class PessoaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String email;
    private String telefone;
    private String funcao;

    @CreationTimestamp
    private LocalDateTime createTime;
}
