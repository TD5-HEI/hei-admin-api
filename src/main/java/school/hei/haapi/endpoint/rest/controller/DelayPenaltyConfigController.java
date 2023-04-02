package school.hei.haapi.endpoint.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.hei.haapi.endpoint.rest.model.DelayPenalty;
import school.hei.haapi.service.DelayPenaltyService;

@RestController
@RequestMapping("/api")
public class DelayPenaltyConfigController {
    private final DelayPenaltyService delayPenaltyService;

    @Autowired
    public DelayPenaltyConfigController(DelayPenaltyService delayPenaltyService) {
        this.delayPenaltyService = delayPenaltyService;
    }

    @GetMapping("/delay-penalty")
    public ResponseEntity<DelayPenalty> getDelayPenalty() {
        try {
            DelayPenalty delayPenalty = delayPenaltyService.getLatestDelayPenalty();
            return ResponseEntity.ok(delayPenalty);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
