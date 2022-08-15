package study.planner.app.studyplan.controller

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import study.planner.app.member.Member
import study.planner.app.member.dto.MemberRequest

@SpringBootTest
@AutoConfigureMockMvc
internal class StudyPlanQueryControllerTest(

        @Autowired
        val mockMvc: MockMvc
) {
    var member: Member? = null
    var session: MockHttpSession = MockHttpSession()

    @BeforeEach
    fun init() {
        member = Member.of(MemberRequest("name", "email", "password"))
        session.setAttribute("member", member)
    }

    @Test
    fun studyPlans() {
        mockMvc
                .perform(get("/study-plans/1")
                        .session(session))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(view().name("/studyplans/studyPlans"))
    }
}
