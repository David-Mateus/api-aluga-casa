package davidmateus.com.alugacasa.controllers;

import davidmateus.com.alugacasa.dtos.SituationDTO;
import davidmateus.com.alugacasa.dtos.UserDTO;
import davidmateus.com.alugacasa.service.SituationService;
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
@RequestMapping("api/v1/situation")
@Tag(name = "Situation", description = "Endoints para Managing situations")
public class SituationController {
    @Autowired
    private SituationService situationService;

    @GetMapping
    @Operation(summary = "Finds all situations", description = "Finds all situations",
            tags = {"User"},
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation =  SituationDTO.class))
                            )

                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public List<SituationDTO> getAllPosts(){
        return situationService.getAllSituation();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds a situation", description = "Finds a situation", tags = {"Situation"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = SituationDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public SituationDTO getSituation(@PathVariable Long id){
        return situationService.getSituationById(id);
    }

    @PostMapping
    @Operation(summary = "Adds a new situation", description = "Adds a new situation por passing in a json", tags = {"Situation"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = SituationDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public SituationDTO createSituation(@RequestBody SituationDTO situation){
        return  situationService.createPost(situation);

    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a situation", description = "update a situation", tags = {"Situation"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = SituationDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })
    public SituationDTO updateSituation(@PathVariable Long id, @RequestBody SituationDTO updateSituation){
        return situationService.updateSituation(id, updateSituation);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete a situation", description = "delete a situation", tags = {"Situation"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = SituationDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })
    public void deleteSituation(@PathVariable Long id){
        situationService.deleteSituation(id);
    }
}
