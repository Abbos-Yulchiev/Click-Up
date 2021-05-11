package pdp.uz.clickup.service;

import org.springframework.stereotype.Service;
import pdp.uz.clickup.entity.Attachment;
import pdp.uz.clickup.entity.Space;
import pdp.uz.clickup.entity.User;
import pdp.uz.clickup.entity.WorkSpace;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.SpaceDTO;
import pdp.uz.clickup.repository.AttachmentRepository;
import pdp.uz.clickup.repository.SpaceRepository;
import pdp.uz.clickup.repository.UserRepository;
import pdp.uz.clickup.repository.WorkspaceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceImpl implements SpaceService{

    final AttachmentRepository attachmentRepository;
    final WorkspaceRepository workspaceRepository;
    final UserRepository userRepository;
    final SpaceRepository spaceRepository;

    public SpaceImpl(AttachmentRepository attachmentRepository, WorkspaceRepository workspaceRepository, UserRepository userRepository, SpaceRepository spaceRepository) {
        this.attachmentRepository = attachmentRepository;
        this.workspaceRepository = workspaceRepository;
        this.userRepository = userRepository;
        this.spaceRepository = spaceRepository;
    }


    @Override
    public ApiResponse add(SpaceDTO spaceDTO) {

        final Optional<Attachment> optionalAttachment = attachmentRepository.findById(spaceDTO.getAvatarId());
        if (!optionalAttachment.isPresent()) {
            return new ApiResponse("Avatar not found", false);
        }

        final Optional<WorkSpace> optionalWorkspace =
                workspaceRepository.findById(spaceDTO.getWorkspaceId());

        if (!optionalWorkspace.isPresent()) {
            return new ApiResponse("Workspace not found", false);
        }

        final Optional<User> optionalUser = userRepository.findById(spaceDTO.getOwnerId());
        if (!optionalUser.isPresent()) {
            return new ApiResponse("Owner not found", false);
        }

        Space space = new Space(
                spaceDTO.getName(), spaceDTO.getColor(), spaceDTO.getInitials(), optionalAttachment.get(),
                spaceDTO.isAccessType(), optionalWorkspace.get(), optionalUser.get()
        );
        spaceRepository.save(space);
        return new ApiResponse("Space saved!", true);
    }

    @Override
    public ApiResponse edit(SpaceDTO spaceDTO, Long id) {
        final Optional<Space> optionalSpace = spaceRepository.findById(id);
        if (!optionalSpace.isPresent()) {
            return new ApiResponse("Space not found!", false);
        }
        final Space space = optionalSpace.get();
        final Optional<Attachment> optionalAttachment = attachmentRepository.findById(spaceDTO.getAvatarId());
        if (!optionalAttachment.isPresent()) {
            return new ApiResponse("Avatar not found", false);
        }
        final Optional<WorkSpace> optionalWorkspace =
                workspaceRepository.findById(spaceDTO.getWorkspaceId());
        if (!optionalWorkspace.isPresent()) {
            return new ApiResponse("Workspace not found", false);
        }
        final Optional<User> optionalUser = userRepository.findById(spaceDTO.getOwnerId());
        if (!optionalUser.isPresent()) {
            return new ApiResponse("Owner not found", false);
        }
        space.setName(spaceDTO.getName());
        space.setColor(spaceDTO.getColor());
        space.setInitials(spaceDTO.getInitials());
        space.setAvatar(optionalAttachment.get());
        space.setAccessType(spaceDTO.isAccessType());
        space.setWorkspace(optionalWorkspace.get());
        space.setOwner(optionalUser.get());
        return new ApiResponse("Space edited", true);
    }

    @Override
    public ApiResponse delete(Long id) {
        final boolean existsById = spaceRepository.existsById(id);
        if (existsById) {
            spaceRepository.deleteById(id);
        }
        return new ApiResponse("Deleted!", true);
    }

    @Override
    public List<Space> get() {
        return spaceRepository.findAll();
    }

}
