package davidmateus.com.alugacasa.controllers;

import davidmateus.com.alugacasa.model.Tenant;
import davidmateus.com.alugacasa.service.TenantService;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/tenants")
public class TenantController {
    private TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public List<Tenant> getAllTenants(){
        return tenantService.getAllTenants();
    }
    @GetMapping("/{id}")
    public Optional<Tenant> getTenantById(@PathVariable Long id){
        return tenantService.getTenantById(id);
    }
    @PostMapping
    public  Tenant createTenant(@RequestBody Tenant tenant){
        return tenantService.createTenant(tenant)
    }
    @PutMapping("/{id}")
    public Tenant updateTenant(@PathVariable Long id, @RequestBody Tenant updateTenant){
        return tenantService.updateTenant(id, updateTenant);
    }
    @DeleteMapping("/{id}")
    public void deleteTenant(@PathVariable Long id){
        tenantService.deleteTenant(id);
    }
}
