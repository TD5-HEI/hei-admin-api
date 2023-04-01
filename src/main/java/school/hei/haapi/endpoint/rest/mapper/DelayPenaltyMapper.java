package school.hei.haapi.endpoint.rest.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.haapi.model.DelayPenalty;

@Component
@AllArgsConstructor
public class DelayPenaltyMapper {
    public DelayPenalty toDomain(school.hei.haapi.endpoint.rest.model.DelayPenalty rest){
        return DelayPenalty.builder()
                .interestPercent(rest.getInterestPercent())
                .interestTimerate(String.valueOf(rest.getInterestTimerate()))
                .graceDelay(rest.getGraceDelay())
                .applicabilityDelayAfterGrace(rest.getApplicabilityDelayAfterGrace())
                .build();
    }
}
