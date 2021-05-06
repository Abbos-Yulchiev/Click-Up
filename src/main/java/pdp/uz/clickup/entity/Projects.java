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

    @OneToMany(mappedBy = "id")
    private List<Space> space;

    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    private boolean achieved;

    private String color;

    @ManyToMany
    private List<User> user;
}
