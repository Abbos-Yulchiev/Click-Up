package pdp.uz.clickup.payload;

import lombok.Data;

@Data
public class SpaceDTO {

    private String name;
    private String color;
    private String initials;
    private Long avatarId;
    private boolean accessType;
    private Long workspaceId;
    private Long ownerId;
}
