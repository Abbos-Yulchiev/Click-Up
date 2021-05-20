package pdp.uz.clickup.service;

import org.springframework.stereotype.Service;
import pdp.uz.clickup.entity.Tag;
import pdp.uz.clickup.entity.WorkSpace;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.TagDTO;
import pdp.uz.clickup.repository.TagRepository;
import pdp.uz.clickup.repository.WorkspaceRepository;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    final WorkspaceRepository workspaceRepository;
    final TagRepository tagRepository;

    public TagServiceImpl(WorkspaceRepository workspaceRepository, TagRepository tagRepository) {
        this.workspaceRepository = workspaceRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public ApiResponse add(TagDTO tagDTO) {

        final Optional<WorkSpace> optionalWorkspace = workspaceRepository.findById(tagDTO.getWorkspaceId());
        if (!optionalWorkspace.isPresent()) {
            return new ApiResponse("Workspace not found", false);
        }
        Tag tag = new Tag(
                tagDTO.getName(), tagDTO.getColor(), optionalWorkspace.get()
        );
        tagRepository.save(tag);
        return new ApiResponse("Tag added", true);
    }

    @Override
    public ApiResponse edit(Long tagId, TagDTO tagDTO) {

        final Optional<WorkSpace> optionalWorkspace = workspaceRepository.findById(tagDTO.getWorkspaceId());
        if (!optionalWorkspace.isPresent()){
            return new ApiResponse("Workspace not found", false);
        }
        final WorkSpace workspace = optionalWorkspace.get();

        final Optional<Tag> optionalTag = tagRepository.findById(tagId);
        if (!optionalTag.isPresent()) {
            return new ApiResponse("", false);
        }
        final Tag tag = optionalTag.get();
        tag.setName(tagDTO.getName());
        tag.setColor(tagDTO.getColor());
        tag.setWorkSpace(workspace);
        tagRepository.save(tag);
        return new ApiResponse("Edited", true);
    }

    @Override
    public ApiResponse delete(Long id) {

        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
            return new ApiResponse("Deleted" ,true);
        }
        return new ApiResponse("Tag not found" ,false);
    }
}
