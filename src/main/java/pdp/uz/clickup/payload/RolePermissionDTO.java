package pdp.uz.clickup.payload;

import lombok.Data;
import pdp.uz.clickup.entity.enums.WorkspacePermissionName;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RolePermissionDTO {

    @NotNull
    private Long workSpaceRoleId;

    private List<WorkspacePermissionName> workspacePermissionNameList;

    private String addOrDelete;
}
