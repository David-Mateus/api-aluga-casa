package davidmateus.com.alugacasa.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_situation")
public class Situation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "situation_id")
    private  Long id;

    @Column(name = "month", nullable = false, length = 15)
    private String month;
    @Column(name = "status", nullable = false, length = 15)
    private  String status;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    public Situation(){}

    public Situation(Long id, String month, String status, Tenant tenant) {
        this.id = id;
        this.month = month;
        this.status = status;
        this.tenant = tenant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Situation situation = (Situation) o;
        return Objects.equals(id, situation.id) && Objects.equals(month, situation.month) && Objects.equals(status, situation.status) && Objects.equals(tenant, situation.tenant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, month, status, tenant);
    }
}
