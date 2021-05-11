package pdp.uz.clickup.service;

import pdp.uz.clickup.entity.Space;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.SpaceDTO;

import java.util.List;

public interface SpaceService {

    ApiResponse add(SpaceDTO spaceDTO);

    ApiResponse edit(SpaceDTO spaceDTO, Long id);

    ApiResponse delete(Long id);

    List<Space> get();
}
