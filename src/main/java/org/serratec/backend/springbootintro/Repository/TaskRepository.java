package org.serratec.backend.springbootintro.Repository;
import org.serratec.backend.springbootintro.Tasks.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository  extends JpaRepository<TaskModel, Long> {
    List<TaskModel> findByIdUserId(UUID id);

}
