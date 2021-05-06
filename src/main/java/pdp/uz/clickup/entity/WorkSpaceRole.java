package pdp.uz.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pdp.uz.clickup.entity.enums.WorkSpaceRoleName;
import pdp.uz.clickup.entity.template.AbsEntity;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class WorkSpaceRole extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private WorkSpace workSpace;

    @Enumerated(EnumType.STRING)
    private WorkSpaceRoleName extendsWorkSpaceRoleName;
}
