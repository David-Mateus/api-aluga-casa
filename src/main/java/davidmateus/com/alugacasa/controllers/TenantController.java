package davidmateus.com.alugacasa.controllers;

import davidmateus.com.alugacasa.dtos.TenantDTO;
import davidmateus.com.alugacasa.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tenants")
public class TenantController {
    @Autowired
    private TenantService tenantService;

//    public TenantController(TenantService tenantService) {
//        this.tenantService = tenantService;
//    }

    @GetMapping
    public List<TenantDTO> getAllTenants(){
        return tenantService.getAllTenants();
    }
    @GetMapping("/{id}")
    public TenantDTO getTenantById(@PathVariable Long id){
        return tenantService.getTenantById(id);
    }
    @PostMapping
    public TenantDTO createTenant(@RequestBody TenantDTO tenant){
        return tenantService.createTenant(tenant);
    }
    @PutMapping("/{id}")
    public TenantDTO updateTenant(@PathVariable Long id, @RequestBody TenantDTO updateTenant){
        return tenantService.updateTenant(id, updateTenant);
    }
    @DeleteMapping("/{id}")
    public void deleteTenant(@PathVariable Long id){
        tenantService.deleteTenant(id);
    }
}
