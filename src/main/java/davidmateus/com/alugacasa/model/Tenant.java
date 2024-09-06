package davidmateus.com.alugacasa.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = ("tb_tenant"))
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phone;
    private String email;
    private String durationContract;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    private List<Situation> situations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDurationContract() {
        return durationContract;
    }

    public void setDurationContract(String durationContract) {
        this.durationContract = durationContract;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Situation> getSituations() {
        return situations;
    }

    public void setSituations(List<Situation> situations) {
        this.situations = situations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tenant tenant = (Tenant) o;
        return Objects.equals(id, tenant.id) && Objects.equals(name, tenant.name) && Objects.equals(address, tenant.address) && Objects.equals(phone, tenant.phone) && Objects.equals(email, tenant.email) && Objects.equals(durationContract, tenant.durationContract) && Objects.equals(user, tenant.user) && Objects.equals(situations, tenant.situations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone, email, durationContract, user, situations);
    }
}
