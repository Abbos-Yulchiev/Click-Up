package pdp.uz.clickup.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class ChecklistItemDTO {

    private String name;

    private Long checklistId;

    boolean isResolved;

    private Long assignedUser;
}
