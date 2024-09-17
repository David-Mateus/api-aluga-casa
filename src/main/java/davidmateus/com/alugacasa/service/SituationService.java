package davidmateus.com.alugacasa.service;

import davidmateus.com.alugacasa.exceptions.ResourceNotFoundException;
import davidmateus.com.alugacasa.model.Situation;
import davidmateus.com.alugacasa.repository.SituationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SituationService {

    @Autowired
    private SituationRepository situationRepository;


    public List<Situation> getAllSituation(){
        return situationRepository.findAll();
    }
    public Situation getSituationById(Long id){
        return situationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not records found for this ID!"));
    }
    public Situation createPost(Situation situation){
        return situationRepository.save(situation);
    }
    public Situation updateSituation(Long id, Situation updateSituation){
        return situationRepository.findById(id)
                .map(situation -> {
                    situation.setMonth(updateSituation.getMonth());
                    situation.setStatus(updateSituation.getStatus());
                    return situationRepository.save(situation);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Situation not found"));
    }
    public void deleteSituation(Long id){
        var entity = situationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        situationRepository.delete(entity);
    }
}
