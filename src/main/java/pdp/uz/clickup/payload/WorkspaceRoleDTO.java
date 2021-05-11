package pdp.uz.clickup.payload;

import lombok.Data;
import pdp.uz.clickup.entity.WorkSpace;
import pdp.uz.clickup.entity.template.AbsEntity;

import javax.validation.constraints.NotNull;

@Data
public class WorkspaceRoleDTO{

    @NotNull
    private String name;

    @NotNull
    private Long workSpaceId;
}
