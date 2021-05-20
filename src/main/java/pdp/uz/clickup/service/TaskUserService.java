package pdp.uz.clickup.service;


import pdp.uz.clickup.payload.ApiResponse;

public interface TaskUserService {

    ApiResponse assignUser(Long taskId, Long userId);

    ApiResponse removeUser(Long taskId, Long userId);

}
