package pdp.uz.clickup.service;

import pdp.uz.clickup.entity.View;

import java.util.List;

public interface ViewService {

    List<View> getViews(Long spaceId);
}
