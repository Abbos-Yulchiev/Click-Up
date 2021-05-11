package pdp.uz.clickup.payload;

import lombok.Data;

@Data
public class ProjectDTO {

    private String name;
    private Long spaceId;
    private boolean accessType;
    private boolean isArchived;
    private String color;
}
