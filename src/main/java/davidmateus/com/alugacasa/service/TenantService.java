package davidmateus.com.alugacasa.service;

import davidmateus.com.alugacasa.model.Tenant;
import davidmateus.com.alugacasa.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TenantService {
    @Autowired
    private TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }
    public List<Tenant> getAllTenants(){
        return tenantRepository.findAll();
    }
    public Optional<Tenant> getTenantById(Long tenantId){
        return tenantRepository.findById(tenantId);
    }
    public  Tenant createTenant(Tenant tenant){
        return tenantRepository.save(tenant);
    }
    public  Tenant updateTenant(Long tenantId, Tenant updatedTenant){
        return tenantRepository.findById(tenantId)
                .map(tenant -> {
                    tenant.setName(updatedTenant.getName());
                    tenant.setAddress(updatedTenant.getAddress());
                    tenant.setPhone(updatedTenant.getPhone());
                    tenant.setEmail(updatedTenant.getEmail());
                    tenant.setDurationContract(updatedTenant.getDurationContract());
                    return tenantRepository.save(tenant);
                })
                .orElseThrow(() -> new RuntimeException("Tenant not found"));
    }
    public void deleteTenant(Long tenantId){
        tenantRepository.deleteById(tenantId);
    }
}
