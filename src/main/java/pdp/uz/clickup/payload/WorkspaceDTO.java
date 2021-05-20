package pdp.uz.clickup.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkspaceDTO {

    @NotNull
    private String name;
    @NotNull
    private String color;
    private Long attachmentId;
}
