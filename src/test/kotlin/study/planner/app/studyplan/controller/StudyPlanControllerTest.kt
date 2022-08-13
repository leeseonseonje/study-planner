package study.planner.app.studyplan.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
internal class StudyPlanControllerTest(

        @Autowired
        val mockMvc: MockMvc,
) {

    @Test
    fun studyPlanRegistrationForm() {
        mockMvc
                .perform(get("/study-plan/1"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(view().name("studyplans/createStudyPlanForm"))
    }

    @Test
    fun studyPlanRegistration() {
        mockMvc
                .perform(post("/study-plan")
                        .param("memberId", "1")
                        .param("title", "title")
                        .param("content", "content")
                        .param("completeFigure", "1000")
                        .param("expectCompleteDate", "9999-12-31"))
                .andDo(print())
                .andExpect(status().isFound)
                .andExpect(view().name("main"))
    }
}