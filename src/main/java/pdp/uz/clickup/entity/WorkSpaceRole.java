package pdp.uz.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pdp.uz.clickup.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class WorkSpaceRole extends AbsEntity {

    private String name;

    @ManyToOne
    private WorkSpace workSpaceId;

    @ManyToOne
    private WorkSpaceRole extendsRoleWorkSpace;
}
