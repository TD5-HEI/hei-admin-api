package school.hei.haapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.haapi.model.DelayPenalty;
import school.hei.haapi.repository.DelayPenaltyRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DelayPenaltyService {
    private final DelayPenaltyRepository delayPenaltyRepository;
    private final DelayPenaltyRepository repository;

    public List<DelayPenalty> getAll(){return repository.findAll();}
    public DelayPenalty getById(String DelayPenaltyId){return delayPenaltyRepository.getById(DelayPenaltyId);}
    public DelayPenalty update(DelayPenalty delayPenalty){return delayPenaltyRepository.save(delayPenalty);}
}
