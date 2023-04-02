package school.hei.haapi.integration;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import school.hei.haapi.SentryConf;
import school.hei.haapi.endpoint.rest.api.PayingApi;
import school.hei.haapi.endpoint.rest.api.TeachingApi;
import school.hei.haapi.endpoint.rest.client.ApiClient;
import school.hei.haapi.endpoint.rest.model.DelayPenalty;
import school.hei.haapi.endpoint.rest.model.Group;
import school.hei.haapi.endpoint.rest.model.Payment;
import school.hei.haapi.endpoint.rest.security.cognito.CognitoComponent;
import school.hei.haapi.integration.conf.TestUtils;

import java.sql.ClientInfoStatus;
import java.util.List;

import static java.lang.Enum.valueOf;
import static java.util.UUID.randomUUID;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.values;
import static school.hei.haapi.integration.conf.TestUtils.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Testcontainers
@ContextConfiguration(initializers = GroupIT.ContextInitializer.class)
@AutoConfigureMockMvc
public class DelayPenaltyIT {
    @MockBean
    private SentryConf sentryConf;

    @MockBean
    private CognitoComponent cognitoComponentMock;

    private static ApiClient anApiClient(String token) {
        return TestUtils.anApiClient(token, GroupIT.ContextInitializer.SERVER_PORT);
    }

    public static DelayPenalty someCreatableDelayPenalty() {
        DelayPenalty delayPenalty = new DelayPenalty();
        delayPenalty.setInterestPercent(2);
        delayPenalty.setInterestTimerate(DelayPenalty.InterestTimerateEnum.valueOf("DAILY"));
        delayPenalty.setGraceDelay(3);
        delayPenalty.setApplicabilityDelayAfterGrace(2);
        return delayPenalty;
    }
    @Test
    void manager_update_delayPenalty_ok() throws Exception{
        ApiClient manager1Client = anApiClient(MANAGER1_TOKEN);
        DelayPenalty toUpdate = someCreatableDelayPenalty();

        PayingApi api = new PayingApi(manager1Client);
        DelayPenalty updated= api.createDelayPenaltyChange(values(toUpdate));
        DelayPenalty actual=api.createDelayPenaltyChange();
        DelayPenalty expected=api.createDelayPenaltyChange();
    }

    @Test
    void teacher_write_ko() {
        ApiClient student1Client = anApiClient(TEACHER1_TOKEN);
        PayingApi api = new PayingApi(student1Client);

        assertThrowsApiException(
                "{\"type\":\"403 FORBIDDEN\",\"message\":\"Access is denied\"}",
                () -> api.createDelayPenaltyChange();
    }

    @Test
    void student_write_ko() {
        ApiClient student1Client = anApiClient(STUDENT1_TOKEN);
        PayingApi api = new PayingApi(student1Client);

        assertThrowsApiException(
                "{\"type\":\"403 FORBIDDEN\",\"message\":\"Access is denied\"}",
                () -> api.createDelayPenaltyChange();
    }
}
