package davidmateus.com.alugacasa.service;

import davidmateus.com.alugacasa.controllers.TenantController;
import davidmateus.com.alugacasa.controllers.UserController;
import davidmateus.com.alugacasa.dtos.TenantDTO;
import davidmateus.com.alugacasa.dtos.UserDTO;
import davidmateus.com.alugacasa.exceptions.ResourceNotFoundException;
import davidmateus.com.alugacasa.mapper.DozerMapper;
import davidmateus.com.alugacasa.model.Tenant;
import davidmateus.com.alugacasa.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public List<TenantDTO> getAllTenants(){

        List<TenantDTO> tenantDTOS = DozerMapper.parseListObject(tenantRepository.findAll(), TenantDTO.class);
        tenantDTOS.forEach(tenant -> {
            tenant.add(linkTo(methodOn(TenantController.class).getTenantById(tenant.getId())).withSelfRel());
        });
        return tenantDTOS;
    }
    @Transactional
    public TenantDTO getTenantById(Long tenantId){
        var entity = tenantRepository.findById(tenantId)
                .orElseThrow(()-> new ResourceNotFoundException("Not records found for this ID!"));
        TenantDTO tenantDTO = DozerMapper.parseObject(entity, TenantDTO.class);
        tenantDTO.add(linkTo(methodOn(TenantController.class).getTenantById(tenantId)).withSelfRel());
        return tenantDTO;
    }
    public TenantDTO createTenant(TenantDTO tenant){
        var entity = DozerMapper.parseObject(tenant, Tenant.class);
        var dto =  DozerMapper.parseObject(tenantRepository.save(entity), TenantDTO.class);
        dto.add(linkTo(methodOn(TenantController.class).getTenantById(dto.getId())).withSelfRel());
        return dto;

    }
    public TenantDTO updateTenant(Long tenantId, TenantDTO updatedTenant){
        return tenantRepository.findById(tenantId)
                .map(tenant -> {
                    tenant.setName(updatedTenant.getName());
                    tenant.setAddress(updatedTenant.getAddress());
                    tenant.setPhone(updatedTenant.getPhone());
                    tenant.setEmail(updatedTenant.getEmail());
                    tenant.setDurationContract(updatedTenant.getDurationContract());
                    var dto = DozerMapper.parseObject(tenantRepository.save(tenant), TenantDTO.class);
                    dto.add(linkTo(methodOn(TenantController.class).getTenantById(dto.getId())).withSelfRel());
                    return dto;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found"));
    }
    public void deleteTenant(Long tenantId){
        var entity = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found"));
        tenantRepository.delete(entity);
    }
}
