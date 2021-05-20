package pdp.uz.clickup.payload;

import lombok.Data;
import pdp.uz.clickup.entity.enums.StatusType;

@Data
public class StatusDTO {

    private String name;

    private Long spaceId;

    private Long projectId;

    private Long categoryId;

    private String color;

    private StatusType type;
}
