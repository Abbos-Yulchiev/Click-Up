package pdp.uz.clickup.service;

import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.CommentDTO;

public interface CommentService {

    ApiResponse add(CommentDTO commentDTO);

    ApiResponse edit(Long commentId, CommentDTO commentDTO);

    ApiResponse delete(Long commentId);

}
