package pdp.uz.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pdp.uz.clickup.entity.template.AbsEntity;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Space extends AbsEntity {

    private String name;

    private String color;

    private String initials;

    @OneToOne
    private Attachment avatar;

    private boolean accessType;

    @ManyToOne
    private WorkSpace workspace;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User owner;

    @PrePersist
    @PreUpdate
    public void setInitials() {
        this.initials = name.substring(0, 1);
    }
}
