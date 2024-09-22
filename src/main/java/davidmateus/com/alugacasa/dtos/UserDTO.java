package davidmateus.com.alugacasa.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class UserDTO {


    private Long userId;


    private String username;

    // Evitar expor a senha diretamente
//    @JsonIgnore
    private String password;

    private List<TenantDTO> tenants = new ArrayList<TenantDTO>();

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
        UserDTO user = (UserDTO) o;
        return Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(tenants, user.tenants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, tenants);
    }
}
