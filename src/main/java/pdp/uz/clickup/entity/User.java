package pdp.uz.clickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pdp.uz.clickup.entity.enums.RoleName;
import pdp.uz.clickup.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
@Entity
public class User extends AbsEntity implements UserDetails {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String initialLatter;

    @OneToOne
    private Attachment avatarId;

    private boolean enabled;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(this.roleName.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    public User(String fullName, String username, String password, boolean enabled) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}
