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
public class Space extends AbsEntity {

    private String name;

    private String color;

    @ManyToOne
    private WorkSpace workSpace;

    private String initialLetter;

    @OneToOne
    private Attachment attachment;

    @ManyToOne
    private User ownerId;

    @ManyToMany
    private List<User> user;

    @ManyToMany
    private List<View> view;

    @ManyToOne
    private ClickApps clickApps;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private List<AccessType> accessType;
}
