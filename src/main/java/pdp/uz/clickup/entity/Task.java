package pdp.uz.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pdp.uz.clickup.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Task extends AbsEntity {

    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @OneToOne
    private Status status;

    @ManyToOne
    private Priority priority;

    @ManyToOne
    private Task parentTaskId;

    private Timestamp startedDate;

    private Timestamp startTimeHas;

    private Timestamp dueDate;

    private Timestamp dueTimeHas;

    private Long estimateTime;

    private Timestamp achievedDate;
}
