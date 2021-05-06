package pdp.uz.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pdp.uz.clickup.entity.enums.StatusType;
import pdp.uz.clickup.entity.template.AbsEntity;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Status extends AbsEntity {

    private String name;

    @OneToOne
    private Space space;

    @OneToOne
    private Projects projects;

    @OneToOne(fetch = FetchType.LAZY)
    private Category category;

    private String color;

    @Enumerated(EnumType.STRING)
    private StatusType statusType;
}
