package school.hei.haapi.endpoint.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import school.hei.haapi.endpoint.rest.mapper.DelayPenaltyMapper;
import school.hei.haapi.endpoint.rest.model.DelayPenalty;
import school.hei.haapi.service.DelayPenaltyService;

@RestController
@AllArgsConstructor
public class DelayPenaltyController {
    private final DelayPenaltyService delayPenaltyService;
    private final DelayPenaltyMapper delayPenaltyMapper;

    @PutMapping(value = "/delayPenaltyChange")
    public DelayPenalty updateDelayPenalty(@RequestBody DelayPenalty toUpdate){
        return updateDelayPenalty(toUpdate);
    }
}
