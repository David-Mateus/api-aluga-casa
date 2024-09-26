package davidmateus.com.alugacasa.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import davidmateus.com.alugacasa.model.Tenant;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDTO {
    private Long userId;
    private String username;
    private String password;

    @JsonManagedReference
    private List<TenantDTO> tenants = new ArrayList<>();
    public UserDTO(){}

    public UserDTO(Long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TenantDTO> getTenants() {
        return tenants;
    }

    public void setTenants(List<TenantDTO> tenants) {
        this.tenants = tenants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(userId, userDTO.userId) && Objects.equals(username, userDTO.username) && Objects.equals(password, userDTO.password) && Objects.equals(tenants, userDTO.tenants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, tenants);
    }
}
