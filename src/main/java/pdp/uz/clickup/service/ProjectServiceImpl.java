package pdp.uz.clickup.service;

import org.springframework.stereotype.Service;
import pdp.uz.clickup.entity.Projects;
import pdp.uz.clickup.entity.Space;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.ProjectDTO;
import pdp.uz.clickup.repository.ProjectRepository;
import pdp.uz.clickup.repository.SpaceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    final ProjectRepository projectRepository;
    final SpaceRepository spaceRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, SpaceRepository spaceRepository) {
        this.projectRepository = projectRepository;
        this.spaceRepository = spaceRepository;
    }

    @Override
    public List<Projects> get() {
        return projectRepository.findAll();
    }

    @Override
    public ApiResponse add(ProjectDTO projectDTO) {

        Optional<Space> optionalSpace = spaceRepository.findById(projectDTO.getSpaceId());

        if (!optionalSpace.isPresent()) {
            return new ApiResponse("Space not found", false);
        }

        Projects project = new Projects(projectDTO.getName(), optionalSpace.get(), projectDTO.isAccessType(), projectDTO.isArchived(), projectDTO.getColor());
        projectRepository.save(project);
        return new ApiResponse("Project saved", true);
    }

    @Override
    public ApiResponse edit(Long id, ProjectDTO projectDTO) {

        final Optional<Projects> optionalProject = projectRepository.findById(id);
        if (!optionalProject.isPresent()) {
            return new ApiResponse("Project not found", false);
        }
        final Projects project = optionalProject.get();
        final Optional<Space> optionalSpace = spaceRepository.findById(projectDTO.getSpaceId());
        if (!optionalSpace.isPresent()) {
            return new ApiResponse("Space not found", false);
        }
        final Space space = optionalSpace.get();
        project.setAchieved(projectDTO.isArchived());
        project.setColor(projectDTO.getColor());
        project.setAccessType(projectDTO.isAccessType());
        project.setName(projectDTO.getName());
        project.setSpace(space);
        return new ApiResponse("Project edited", true);
    }

    @Override
    public ApiResponse delete(Long id) {

        final Optional<Projects> optionalProject = projectRepository.findById(id);
        if (!optionalProject.isPresent()) {
            return new ApiResponse("Not found", false);
        }
        projectRepository.deleteById(id);
        return new ApiResponse("Deleted", true);
    }
}
