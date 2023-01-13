package org.openapitools;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.web.servlet.function.ServerResponse.status;

@WebMvcTest
@SpringBootTest
@ActiveProfiles("test")
class OpenApiGeneratorApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Test
    void testStatusMessage() throws Exception{
        this.mockMvc.perform(get("/personnel/status/")).andDo(print()).andExpect(status().isOk());

    }
}
