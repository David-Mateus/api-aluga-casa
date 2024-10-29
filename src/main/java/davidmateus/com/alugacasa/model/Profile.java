package davidmateus.com.alugacasa.model;

import davidmateus.com.alugacasa.enums.ProfileEnum;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProfileEnum name;

    public Profile(Long id, ProfileEnum name) {
        this.id = id;
        this.name = name;
    }

    public Profile() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProfileEnum getName() {
        return name;
    }

    public void setName(ProfileEnum name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) && name == profile.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
