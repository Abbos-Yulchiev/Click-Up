package pdp.uz.clickup.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class WorkspacePermissionDTO {

    private UUID workspaceRoleId;
    private String permissionName;
}

