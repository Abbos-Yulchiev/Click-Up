package pdp.uz.clickup.service;


import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.TagDTO;

public interface TagService {

    ApiResponse add(TagDTO tagDTO);

    ApiResponse edit(Long tagId, TagDTO tagDTO);

    ApiResponse delete(Long id);
}
