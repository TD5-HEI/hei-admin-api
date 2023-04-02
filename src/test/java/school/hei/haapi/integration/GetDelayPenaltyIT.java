package school.hei.haapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import school.hei.haapi.endpoint.rest.model.DelayPenalty;
import school.hei.haapi.repository.DelayPenaltyRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Tests for Get Delay Penalty API")
public class GetDelayPenaltyIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DelayPenaltyRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    private DelayPenalty delayPenalty;

    @BeforeEach
    void setup() {
        delayPenalty = new DelayPenalty();
        delayPenalty.setDelayDuration(10);
        delayPenalty.setCreationDatetime(LocalDateTime.now());
        repository.save(delayPenalty);
    }

    @Test
    @DisplayName("Should return latest delay penalty when calling /api/delay-penalty")
    void shouldReturnLatestDelayPenalty() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/delay-penalty")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        DelayPenalty result = objectMapper.readValue(content, DelayPenalty.class);

        assertThat(result.getId()).isEqualTo(delayPenalty.getId());
        assertThat(result.getDelayDuration()).isEqualTo(delayPenalty.getDelayDuration());
        assertThat(result.getCreationDatetime()).isEqualTo(delayPenalty.getCreationDatetime());
    }

    @Test
    @DisplayName("Should return 404 when no delay penalty found in database")
    void shouldReturnNotFoundWhenNoDelayPenaltyFound() throws Exception {
        repository.deleteAll();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/delay-penalty")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
