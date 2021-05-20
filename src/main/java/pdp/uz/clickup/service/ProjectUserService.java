package pdp.uz.clickup.service;


import pdp.uz.clickup.payload.ApiResponse;

public interface ProjectUserService {

    ApiResponse addMember(Long memberId, Long projectId, Long taskId);

    ApiResponse removeMember(Long memberId, Long projectId, Long taskId);
}
