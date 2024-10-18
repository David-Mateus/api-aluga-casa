package davidmateus.com.alugacasa.dtos;



import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class SituationDTO extends RepresentationModel<SituationDTO> {

    private  Long id;

    private String month;

    private  String status;


    private TenantDTO tenant;

    public SituationDTO(){}

    public SituationDTO(Long id, String month, String status, TenantDTO tenant) {
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
    @JsonBackReference
    public TenantDTO getTenant() {
        return tenant;
    }

    public void setTenant(TenantDTO tenant) {
        this.tenant = tenant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SituationDTO that = (SituationDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(month, that.month) && Objects.equals(status, that.status) && Objects.equals(tenant, that.tenant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, month, status, tenant);
    }
}
