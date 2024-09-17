package davidmateus.com.alugacasa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = ("tb_tenant"))
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tenant_id",  unique = true)
    private Long id;
    @Column(name = "name", length = 60, nullable = false, unique = true)
    private String name;
    @Column(name = "address", length = 50, nullable = false, unique = true)
    private String address;
    @Column(name = "phone", length = 20, nullable = false, unique = true)
    private String phone;
    @Column(name = "email", length = 60, nullable = false, unique = true)
    private String email;
    @Column(name = "duration", length = 20, nullable = false, unique = true)
    private Integer durationContract;

    //Varios inquilinos pode ser de um usuario
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL)
    private List<Situation> situations = new ArrayList<Situation>();

    public Tenant(){}

    public Tenant(Long id, String name, String address, String phone, String email, Integer durationContract, User user) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.durationContract = durationContract;
        this.user = user;
    }

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

    public Integer getDurationContract() {
        return durationContract;
    }

    public void setDurationContract(int durationContract) {
        this.durationContract = durationContract;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
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
