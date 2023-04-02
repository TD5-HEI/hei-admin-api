package school.hei.haapi.service;


import school.hei.haapi.endpoint.rest.model.DelayPenalty;
import school.hei.haapi.repository.DelayPenaltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DelayPenaltyService {

    @Autowired
    private DelayPenaltyRepository repository;

    public DelayPenalty getLatestDelayPenalty() {
        Optional<DelayPenalty> delayPenalty = repository.findFirstByOrderByCreationDatetimeDesc();
        return delayPenalty.orElseThrow(() -> new NoSuchElementException("No DelayPenalty found in database"));
    }
}
