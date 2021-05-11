package pdp.uz.clickup.payload;


import lombok.Data;
import pdp.uz.clickup.entity.enums.AddType;

@Data
public class AddMemberDTO {

    private String email;
    private Long roleId;
    private Long workspaceId; //ADD, EDIT, REMOVE
}
