package pdp.uz.clickup.payload;


import lombok.Data;
import pdp.uz.clickup.entity.enums.AddType;

import java.sql.Timestamp;

@Data
public class MemberDTO {

    private Long id;
    private String fullName;
    private String email;
    private String roleName;
    private Timestamp lastActiveTime;
    private Long roleId;
    private AddType addType; //ADD, EDIT, REMOVE

}
