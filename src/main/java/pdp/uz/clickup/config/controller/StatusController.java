package pdp.uz.clickup.config.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.StatusDTO;
import pdp.uz.clickup.service.StatusService;

@RestController
@RequestMapping("/status")
public class StatusController {

    final StatusService service;

    public StatusController(StatusService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public HttpEntity<?> addStatus(StatusDTO statusDTO) {
        final ApiResponse response = service.add(statusDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
}
