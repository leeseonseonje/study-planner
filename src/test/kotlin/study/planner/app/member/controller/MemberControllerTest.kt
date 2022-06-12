package study.planner.app.member.controller

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import study.planner.app.member.dto.MemberRequest
import study.planner.app.member.service.MemberService

@WebMvcTest(MemberController::class.java)
internal class MemberControllerTest(

        @Autowired
        val mvc: MockMvc,

        @MockBean
        val memberService: MemberService
) {

    @Test
    fun save() {

        doNothing().`when`(memberService.save(any()))
        mvc.perform(post("/member"))
                .andExpect(status().isOk)
    }

}
