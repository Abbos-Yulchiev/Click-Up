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

    @Column(nullable = false)
    private String name;

    private String color;

    @ManyToOne
    private WorkSpace workSpace;

    private String initialLetter;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment attachment;

    @ManyToOne(fetch = FetchType.LAZY)
    private User ownerId;

    @Enumerated(value = EnumType.STRING)
    private AccessType accessType;
}
