package pdp.uz.clickup.service;

import org.springframework.stereotype.Service;
import pdp.uz.clickup.entity.Projects;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.ProjectDTO;

import java.util.List;

public interface ProjectService {
    List<Projects> get();

    ApiResponse add(ProjectDTO projectDTO);

    ApiResponse edit(Long id, ProjectDTO projectDTO);

    ApiResponse delete(Long id);
}
