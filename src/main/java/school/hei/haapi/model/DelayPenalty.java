package school.hei.haapi.model;

import lombok.*;
import org.hibernate.annotations.TypeDef;
import school.hei.haapi.repository.types.PostgresEnumType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"delayPenalty\"")
@Getter
@Setter
@TypeDef(name = "pgsql_enum", typeClass = PostgresEnumType.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DelayPenalty {
    @Id
    private int interestPercent;
    private String interestTimerate;
    private int graceDelay;
    private int applicabilityDelayAfterGrace;
}
