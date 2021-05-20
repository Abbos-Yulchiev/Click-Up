package pdp.uz.clickup.service;

import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.TaskDTO;

public interface TaskService {


    ApiResponse addTask(TaskDTO taskDTO);
}
