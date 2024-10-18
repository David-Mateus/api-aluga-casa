package davidmateus.com.alugacasa.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import davidmateus.com.alugacasa.dtos.UserDTO;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TenantDTO extends RepresentationModel<TenantDTO> {

    private Long id;

    private String name;

    private String address;

    private String phone;

    private String email;

    private Integer durationContract;


    private UserDTO user;

    @JsonManagedReference
    private List<SituationDTO> situations = new ArrayList<>();
    public TenantDTO(){}

    public TenantDTO(Long id, String name, String address, String phone, String email, Integer durationContract, UserDTO user, List<SituationDTO> situations) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.durationContract = durationContract;
        this.user = user;
        this.situations = situations;
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

    public void setDurationContract(Integer durationContract) {
        this.durationContract = durationContract;
    }
    @JsonBackReference
    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

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
        TenantDTO tenantDTO = (TenantDTO) o;
        return Objects.equals(id, tenantDTO.id) && Objects.equals(name, tenantDTO.name) && Objects.equals(address, tenantDTO.address) && Objects.equals(phone, tenantDTO.phone) && Objects.equals(email, tenantDTO.email) && Objects.equals(durationContract, tenantDTO.durationContract) && Objects.equals(user, tenantDTO.user) && Objects.equals(situations, tenantDTO.situations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone, email, durationContract, user, situations);
    }
}
