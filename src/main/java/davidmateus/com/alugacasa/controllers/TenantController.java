package davidmateus.com.alugacasa.controllers;

import davidmateus.com.alugacasa.dtos.TenantDTO;
import davidmateus.com.alugacasa.dtos.UserDTO;
import davidmateus.com.alugacasa.service.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tenants")
@Tag(name = "Tenant", description = "Endoints para Managing tenants")
public class TenantController {
    @Autowired
    private TenantService tenantService;

    @GetMapping
    @Operation(summary = "Finds all tenants", description = "Finds all tenants",
            tags = {"Tenant"},
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation =  TenantDTO.class))
                            )

                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public List<TenantDTO> getAllTenants(){
        return tenantService.getAllTenants();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Finds a tenant", description = "Finds a tenant", tags = {"Tenant"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = TenantDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public TenantDTO getTenantById(@PathVariable Long id){
        return tenantService.getTenantById(id);
    }
    @PostMapping
    @Operation(summary = "Adds a new tenant", description = "Adds a new user por passing in a json", tags = {"Tenant"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = TenantDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public TenantDTO createTenant(@RequestBody TenantDTO tenant){
        return tenantService.createTenant(tenant);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a tenant", description = "update a tenant", tags = {"Tenant"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = TenantDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })
    public TenantDTO updateTenant(@PathVariable Long id, @RequestBody TenantDTO updateTenant){
        return tenantService.updateTenant(id, updateTenant);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete a tenant", description = "delete a tenant", tags = {"Tenant"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = UserDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })
    public void deleteTenant(@PathVariable Long id){
        tenantService.deleteTenant(id);
    }
}
