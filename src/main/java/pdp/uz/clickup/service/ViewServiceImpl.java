package pdp.uz.clickup.service;

import org.springframework.stereotype.Service;
import pdp.uz.clickup.entity.View;
import pdp.uz.clickup.repository.ViewRepository;

import java.util.List;

@Service
public class ViewServiceImpl implements ViewService {

    final ViewRepository viewRepository;

    public ViewServiceImpl(ViewRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    @Override
    public List<View> getViews(Long spaceId) {
        return viewRepository.getAllBySpace(spaceId);
    }
}
