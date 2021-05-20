package pdp.uz.clickup.config.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pdp.uz.clickup.entity.View;
import pdp.uz.clickup.service.ViewService;

import java.util.List;

@RestController
@RequestMapping("/view")
public class ViewController {

    final ViewService viewService;

    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }

    @GetMapping
    public HttpEntity<?> get(@RequestParam Long spaceId) {
        final List<View> views = viewService.getViews(spaceId);
        return ResponseEntity.status(views.isEmpty() ? 409 : 201).body(views);
    }
}
