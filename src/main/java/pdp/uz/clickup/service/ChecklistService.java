package pdp.uz.clickup.service;

import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.ChecklistDTO;
import pdp.uz.clickup.payload.ChecklistItemDTO;

public interface ChecklistService {

    ApiResponse add(ChecklistDTO checklistDTO);

    ApiResponse delete(Long id);

    ApiResponse addChecklistItem(ChecklistItemDTO checklistItemDTO);

    ApiResponse removeChecklistItem(Long checklistItemId);

    ApiResponse assignUserToChecklistItem(Long checklistItemId, Long userId);


}
