package org.openapitools;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc()
class PersonalV1ApiBackendApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testStatusApiCall() throws Exception {
        //Setup Mock Behavior
        this.mockMvc.perform(get("/personnel/status/"))
                .andExpect(status().isOk());
    }

}
