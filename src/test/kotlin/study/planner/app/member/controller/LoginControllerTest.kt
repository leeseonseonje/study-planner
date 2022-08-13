package study.planner.app.member.controller

import org.assertj.core.api.Assertions.*
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
internal class LoginControllerTest(

        @Autowired
        val mockMvc: MockMvc,
) {

    var member: Member? = null
    var session:MockHttpSession = MockHttpSession()

    @BeforeEach
    fun init() {
        member = Member.of(MemberRequest("name", "email", "password"))
    }
    @Test
    fun home() {
        mockMvc
                .perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(view().name("home"))
    }
    @Test
    fun main() {
        session.setAttribute("member", member)
        mockMvc
                .perform(get("/").session(session))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(view().name("main"))
    }

    @Test
    fun loginHasErrors() {
        mockMvc
                .perform(post("/"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(model().hasErrors())
                .andExpect(view().name("home"))
    }

    @Test
    fun login() {
        val result = mockMvc
                .perform(post("/")
                        .param("email", "leeseonje9323@gmail.com")
                        .param("password", "1234"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(view().name("main"))
                .andReturn()

        assertThat(result.request.session?.getAttribute("member")).isNotNull

    }


}