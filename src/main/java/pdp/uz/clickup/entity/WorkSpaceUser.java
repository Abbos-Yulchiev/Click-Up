package pdp.uz.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pdp.uz.clickup.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class WorkSpaceUser extends AbsEntity {

    @ManyToOne
    private WorkSpace workSpace;

    @ManyToOne
    private User user;

    @ManyToOne
    private WorkspaceRole workSpaceRole;

    private Timestamp dateInvited;

    private Timestamp dateJoined;

}
