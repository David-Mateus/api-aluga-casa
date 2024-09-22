package davidmateus.com.alugacasa.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TenantDTO {

    private Long id;

    private String name;

    private String address;

    private String phone;

    private String email;

    private Integer durationContract;


    private UserDTO user;


    private List<SituationDTO> situations = new ArrayList<SituationDTO>();

    public TenantDTO(){}

    public TenantDTO(Long id, String name, String address, String phone, String email, Integer durationContract, UserDTO user) {
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @JsonIgnore
    public List<SituationDTO> getSituations() {
        return situations;
    }

    public void setSituations(List<SituationDTO> situations) {
        this.situations = situations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TenantDTO tenant = (TenantDTO) o;
        return Objects.equals(id, tenant.id) && Objects.equals(name, tenant.name) && Objects.equals(address, tenant.address) && Objects.equals(phone, tenant.phone) && Objects.equals(email, tenant.email) && Objects.equals(durationContract, tenant.durationContract) && Objects.equals(user, tenant.user) && Objects.equals(situations, tenant.situations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone, email, durationContract, user, situations);
    }
}
