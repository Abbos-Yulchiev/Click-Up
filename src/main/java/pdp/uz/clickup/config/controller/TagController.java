package pdp.uz.clickup.config.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.TagDTO;
import pdp.uz.clickup.service.TagService;

@RestController
@RequestMapping("/tag")
public class TagController {

    final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody TagDTO tagDTO) {
        final ApiResponse response = tagService.add(tagDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/edit")
    public HttpEntity<?> edit(@RequestParam Long id, @RequestBody TagDTO tagDTO) {
        final ApiResponse response = tagService.edit(id, tagDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @DeleteMapping
    public HttpEntity<?> delete(@RequestParam Long id) {
        final ApiResponse apiResponse = tagService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }
}
