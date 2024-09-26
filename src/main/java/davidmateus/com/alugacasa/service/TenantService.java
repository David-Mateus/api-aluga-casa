package davidmateus.com.alugacasa.service;

import davidmateus.com.alugacasa.dtos.TenantDTO;
import davidmateus.com.alugacasa.exceptions.ResourceNotFoundException;
import davidmateus.com.alugacasa.mapper.DozerMapper;
import davidmateus.com.alugacasa.model.Tenant;
import davidmateus.com.alugacasa.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public List<TenantDTO> getAllTenants(){

        return DozerMapper.parseListObject(tenantRepository.findAll(), TenantDTO.class);
    }
    @Transactional
    public TenantDTO getTenantById(Long tenantId){
        var entity = tenantRepository.findById(tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Not records found for this ID!"));
        return DozerMapper.parseObject(entity, TenantDTO.class);
    }
    public TenantDTO createTenant(TenantDTO tenant){
        var entity = DozerMapper.parseObject(tenant, Tenant.class);
        return DozerMapper.parseObject(tenantRepository.save(entity), TenantDTO.class);

    }
    public TenantDTO updateTenant(Long tenantId, TenantDTO updatedTenant){
        return tenantRepository.findById(tenantId)
                .map(tenant -> {
                    tenant.setName(updatedTenant.getName());
                    tenant.setAddress(updatedTenant.getAddress());
                    tenant.setPhone(updatedTenant.getPhone());
                    tenant.setEmail(updatedTenant.getEmail());
                    tenant.setDurationContract(updatedTenant.getDurationContract());
                    return DozerMapper.parseObject(tenantRepository.save(tenant), TenantDTO.class);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found"));
    }
    public void deleteTenant(Long tenantId){
        var entity = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found"));
        tenantRepository.delete(entity);
    }
}
