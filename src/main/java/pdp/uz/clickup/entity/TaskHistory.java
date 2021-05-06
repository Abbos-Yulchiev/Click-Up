package pdp.uz.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pdp.uz.clickup.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class TaskHistory extends AbsEntity {

    @OneToOne
    private Task task;

    private String changeFieldName;

    private Timestamp before;

    private Timestamp after;

    private Date date;
}
