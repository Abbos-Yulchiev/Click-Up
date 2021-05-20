package pdp.uz.clickup.service;


import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.StatusDTO;

public interface StatusService {

    ApiResponse add(StatusDTO statusDTO);
}
