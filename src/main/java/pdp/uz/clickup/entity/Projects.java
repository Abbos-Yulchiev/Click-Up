package pdp.uz.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pdp.uz.clickup.entity.enums.AccessType;
import pdp.uz.clickup.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Projects extends AbsEntity {

    private String name;

    @ManyToOne
    private Space space;

    private boolean accessType;

    private boolean achieved;

    private String color;

}
