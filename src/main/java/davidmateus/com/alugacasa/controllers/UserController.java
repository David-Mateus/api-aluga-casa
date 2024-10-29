package davidmateus.com.alugacasa.controllers;


import davidmateus.com.alugacasa.dtos.UserDTO;
import davidmateus.com.alugacasa.service.UserService;

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
@RequestMapping("api/v1/user")
@Tag(name = "User", description = "Endoints para Managing users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Finds all users", description = "Finds all users",
    tags = {"User"},
    responses = {@ApiResponse(
            description = "Success",
            responseCode = "200",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation =  UserDTO.class))
                    )

            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
}
    )
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    // dados so pode ser passado no path
    @GetMapping("/{id}")
    @Operation(summary = "Finds a user", description = "Finds a user", tags = {"User"},
    responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = UserDTO.class))
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    })
    //Cors de forma local
    //@CrossOrigin(origins = {"http://localhost:8080", "https://erudio.com.br"})
    public UserDTO getUserById(@PathVariable(value = "id") Long id){
        return userService.getUserById(id);
    }
    //dados pode ser passado no body
    @PostMapping
    @Operation(summary = "Adds a new user", description = "Adds a new user por passing in a json", tags = {"User"},
    responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = UserDTO.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    })
    //@CrossOrigin(origins = {"http://localhost:8080", "https://erudio.com.br"})
    public UserDTO createUser(@RequestBody UserDTO user){
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "update a user", tags = {"User"},
    responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

    })
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO updateUser){
        return userService.updateUser(id, updateUser);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete a user", description = "delete a user", tags = {"User"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = UserDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })
    public  void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
