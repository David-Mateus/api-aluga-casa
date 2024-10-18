package davidmateus.com.alugacasa.service;

import davidmateus.com.alugacasa.controllers.SituationController;
import davidmateus.com.alugacasa.dtos.SituationDTO;
import davidmateus.com.alugacasa.exceptions.ResourceNotFoundException;
import davidmateus.com.alugacasa.mapper.DozerMapper;
import davidmateus.com.alugacasa.model.Situation;
import davidmateus.com.alugacasa.repository.SituationRepository;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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

        List<SituationDTO> situationDTOS = DozerMapper.parseListObject(situationRepository.findAll(), SituationDTO.class);
        situationDTOS.forEach(situation -> {
            situation.add(linkTo(methodOn(SituationController.class).getSituation(situation.getId())).withSelfRel());
        });
        return situationDTOS;
    }
    @Transactional
    public SituationDTO getSituationById(Long id){
        var entity = situationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not records found for this ID!"));
        SituationDTO situationDTO = DozerMapper.parseObject(entity, SituationDTO.class);
        situationDTO.add(linkTo(methodOn(SituationController.class).getSituation(id)).withSelfRel());
        return situationDTO;
    }
    public SituationDTO createPost(SituationDTO situation){
        var entity = DozerMapper.parseObject(situation, Situation.class);
        var dto =  DozerMapper.parseObject(situationRepository.save(entity), SituationDTO.class);
        dto.add(linkTo(methodOn(SituationController.class).getSituation(dto.getId())).withSelfRel());
        return dto;
    }

    public SituationDTO updateSituation(Long id, SituationDTO updateSituation){
        return situationRepository.findById(id)
                .map(situation -> {
                    situation.setMonth(updateSituation.getMonth());
                    situation.setStatus(updateSituation.getStatus());
                    Hibernate.initialize(situation.getTenant());
                    var dto = DozerMapper.parseObject(situationRepository.save(situation), SituationDTO.class);
                    dto.add(linkTo(methodOn(SituationController.class).getSituation(dto.getId())).withSelfRel());
                    return dto;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Situation not found"));
    }
    public void deleteSituation(Long id){
        var entity = situationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        situationRepository.delete(entity);
    }
}
