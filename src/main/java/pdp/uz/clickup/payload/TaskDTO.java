package pdp.uz.clickup.payload;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class TaskDTO {

    private String name;
    private String description;
    private String status;
    private String priority;
    private Integer parentTaskId;
    private Timestamp startedDate;
    private Timestamp startTimeHas;
    private Timestamp dueDate;
    private Timestamp dueTimeHas;
    private Long estimateTime;
    private Timestamp achievedDate;
}
