package cl.duocuc.rinconsur.notification

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
class NotificationServiceApplicationTests @Autowired constructor(
    private val context: WebApplicationContext
) {

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    @Test
    fun healthEndpointReportsUp() {
        val response = mockMvc.perform(get("/actuator/health"))
            .andExpect(status().isOk)
            .andReturn()
            .response

        assertThat(response.contentAsString).contains("\"status\":\"UP\"")
        assertThat(response.contentAsString).doesNotContain("components")
    }

    @Test
    fun readinessEndpointReportsUp() {
        val response = mockMvc.perform(get("/actuator/health/readiness"))
            .andExpect(status().isOk)
            .andReturn()
            .response

        assertThat(response.contentAsString).contains("\"status\":\"UP\"")
    }

    @Test
    fun environmentEndpointIsNotExposed() {
        mockMvc.perform(get("/actuator/env"))
            .andExpect(status().isNotFound)
    }
}
