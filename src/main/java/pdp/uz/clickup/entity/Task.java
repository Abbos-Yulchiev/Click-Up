package pdp.uz.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pdp.uz.clickup.entity.template.AbsEntity;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Priority priority;

    @ManyToOne
    private Task parentTaskId;

    @Column(nullable = false)
    private Timestamp startedDate;

    @Column(nullable = false)
    private Timestamp startTimeHas;

    @Column(nullable = false)
    private Timestamp dueDate;

    @Column(nullable = false)
    private Timestamp dueTimeHas;

    @Column(nullable = false)
    private Long estimateTime;

    @Column(nullable = false)
    private Timestamp achievedDate;
}
