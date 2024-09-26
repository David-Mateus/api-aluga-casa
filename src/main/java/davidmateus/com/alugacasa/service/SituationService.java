package davidmateus.com.alugacasa.service;

import davidmateus.com.alugacasa.dtos.SituationDTO;
import davidmateus.com.alugacasa.exceptions.ResourceNotFoundException;
import davidmateus.com.alugacasa.mapper.DozerMapper;
import davidmateus.com.alugacasa.model.Situation;
import davidmateus.com.alugacasa.repository.SituationRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SituationService {

    @Autowired
    private SituationRepository situationRepository;

    @Transactional
    public List<SituationDTO> getAllSituation(){

        return DozerMapper.parseListObject(situationRepository.findAll(), SituationDTO.class);
    }
    @Transactional
    public SituationDTO getSituationById(Long id){
        var entity = situationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not records found for this ID!"));
        return DozerMapper.parseObject(entity, SituationDTO.class);
    }
    public SituationDTO createPost(SituationDTO situation){
        var entity = DozerMapper.parseObject(situation, Situation.class);
        return DozerMapper.parseObject(situationRepository.save(entity), SituationDTO.class);
    }

    public SituationDTO updateSituation(Long id, SituationDTO updateSituation){
        return situationRepository.findById(id)
                .map(situation -> {
                    situation.setMonth(updateSituation.getMonth());
                    situation.setStatus(updateSituation.getStatus());
                    Hibernate.initialize(situation.getTenant());
                    return DozerMapper.parseObject(situationRepository.save(situation), SituationDTO.class);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Situation not found"));
    }
    public void deleteSituation(Long id){
        var entity = situationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        situationRepository.delete(entity);
    }
}
