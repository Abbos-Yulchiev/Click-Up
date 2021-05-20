package pdp.uz.clickup.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskUserDTO {

    private Long taskId;
    private UUID userId;

}
