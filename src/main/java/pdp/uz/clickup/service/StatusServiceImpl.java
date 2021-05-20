package pdp.uz.clickup.service;

import org.springframework.stereotype.Service;
import pdp.uz.clickup.entity.Category;
import pdp.uz.clickup.entity.Projects;
import pdp.uz.clickup.entity.Space;
import pdp.uz.clickup.entity.Status;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.StatusDTO;
import pdp.uz.clickup.repository.CategoryRepository;
import pdp.uz.clickup.repository.ProjectRepository;
import pdp.uz.clickup.repository.SpaceRepository;
import pdp.uz.clickup.repository.StatusRepository;

import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {

    final StatusRepository statusRepository;
    final SpaceRepository spaceRepository;
    final ProjectRepository projectRepository;
    final CategoryRepository categoryRepository;

    public StatusServiceImpl(StatusRepository statusRepository, SpaceRepository spaceRepository, ProjectRepository projectRepository, CategoryRepository categoryRepository) {
        this.statusRepository = statusRepository;
        this.spaceRepository = spaceRepository;
        this.projectRepository = projectRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ApiResponse add(StatusDTO statusDTO) {

        final Optional<Space> optionalSpace = spaceRepository.findById(statusDTO.getSpaceId());
        if (!optionalSpace.isPresent()) {
            return new ApiResponse("Space not found", false);
        }

        final Optional<Projects> optionalProject = projectRepository.findById(statusDTO.getProjectId());
        if (!optionalProject.isPresent()) {
            return new ApiResponse("Project not found", false);
        }

        final Optional<Category> optionalCategory = categoryRepository.findById(statusDTO.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ApiResponse("Category not found", false);
        }

        Status status = new Status(
                statusDTO.getName(), optionalSpace.get(), optionalProject.get(), optionalCategory.get(),
                statusDTO.getColor(),statusDTO.getType()
                );
        statusRepository.save(status);
        return new ApiResponse("Status saved!", true);
    }

}
