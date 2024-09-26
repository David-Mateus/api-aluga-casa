package davidmateus.com.alugacasa.controllers;

import davidmateus.com.alugacasa.dtos.SituationDTO;
import davidmateus.com.alugacasa.service.SituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/situation")
public class SituationController {
    @Autowired
    private SituationService situationService;

//    public SituationController(SituationService situationService) {
//        this.situationService = situationService;
//    }

    @GetMapping
    public List<SituationDTO> getAllPosts(){
        return situationService.getAllSituation();
    }

    @GetMapping("/{id}")
    public SituationDTO getSituation(@PathVariable Long id){
        return situationService.getSituationById(id);
    }

    @PostMapping
    public SituationDTO createSituation(@RequestBody SituationDTO situation){
        return  situationService.createPost(situation);

    }
    @PutMapping("/{id}")
    public SituationDTO updateSituation(@PathVariable Long id, @RequestBody SituationDTO updateSituation){
        return situationService.updateSituation(id, updateSituation);
    }
    @DeleteMapping("/{id}")
    public void deleteSituation(@PathVariable Long id){
        situationService.deleteSituation(id);
    }
}
