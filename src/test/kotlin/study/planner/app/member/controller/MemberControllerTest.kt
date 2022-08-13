package study.planner.app.member.controller

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
internal class MemberControllerTest(

        @Autowired
        val mockMvc: MockMvc,
) {

    @Test
    fun createMemberForm() {
        mockMvc
                .perform(get("/member"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(view().name("members/createMemberForm"))
    }

    @Test
    fun createMember() {
        mockMvc
                .perform(post("/member")
                        .param("name", "member")
                        .param("email", "email@email.com")
                        .param("password", "password"))
                .andDo(print())
                .andExpect(status().isFound)
                .andExpect(view().name("redirect:/"))
    }

    @Test
    fun createMemberHasErrors() {
        mockMvc
                .perform(post("/member"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(model().hasErrors())
                .andExpect(view().name("members/createMemberForm"))
    }
}
