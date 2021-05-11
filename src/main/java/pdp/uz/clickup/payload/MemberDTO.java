package pdp.uz.clickup.payload;


import lombok.Data;
import pdp.uz.clickup.entity.enums.AddType;

@Data
public class MemberDTO {

    private Long id;
    private Long roleId;
    private AddType addType; //ADD, EDIT, REMOVE
}
