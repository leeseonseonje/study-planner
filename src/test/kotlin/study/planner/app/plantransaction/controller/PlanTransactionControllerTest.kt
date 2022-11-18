package study.planner.app.plantransaction.controller

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
import study.planner.app.member.controller.dto.MemberRequest

@SpringBootTest
@AutoConfigureMockMvc
internal class PlanTransactionControllerTest(
        @Autowired
        val mockMvc: MockMvc,
) {
    var member: Member? = null
    var session: MockHttpSession = MockHttpSession()

    @BeforeEach
    fun init() {
        member = Member.of(MemberRequest("name", "email", "password"))
        session.setAttribute("member", member)
    }

    @Test
    fun createPlanTransactionForm() {
        mockMvc
                .perform(get("/plan-transaction/2")
                        .session(session))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(view().name("plan-transaction/createPlanTransactionForm"))
    }
}
