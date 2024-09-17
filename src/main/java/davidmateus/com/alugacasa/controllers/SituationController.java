package davidmateus.com.alugacasa.controllers;

import davidmateus.com.alugacasa.model.Situation;
import davidmateus.com.alugacasa.service.SituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/situation")
public class SituationController {
    @Autowired
    private SituationService situationService;

    public SituationController(SituationService situationService) {
        this.situationService = situationService;
    }

    @GetMapping
    public List<Situation> getAllPosts(){
        return situationService.getAllSituation();
    }

    @GetMapping("/{id}")
    public Situation getSituation(@PathVariable Long id){
        return situationService.getSituationById(id);
    }
    @PostMapping
    public  Situation createSituation(@RequestBody Situation situation){
        return  situationService.createPost(situation);

    }
    @PutMapping("/{id}")
    public Situation updateSituation(@PathVariable Long id, @RequestBody Situation updateSituation){
        return situationService.updateSituation(id, updateSituation);
    }
    @DeleteMapping
    public void deleteSituation(@PathVariable Long id){
        situationService.deleteSituation(id);
    }
}
