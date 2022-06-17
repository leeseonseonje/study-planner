package study.planner.app.member.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.SessionAttribute
import study.planner.app.member.Member
import study.planner.app.member.dto.LoginRequest
import study.planner.app.member.service.MemberService
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@Controller
class LoginController(

        private val memberService: MemberService
) {

    @GetMapping("/")
    fun home(@SessionAttribute(name = "member", required = false) member: Member?, model: Model): String {
        if (member != null) {
            model.addAttribute("memberName", member.name)
            return "main"
        }
        model.addAttribute("loginRequest", LoginRequest(null, null))
        return "home"
    }

    @PostMapping("/")
    fun login(@Valid loginRequest: LoginRequest, result: BindingResult, request: HttpServletRequest, model: Model): String {

        if (result.hasErrors()) {
            return "home"
        }

        val loginMember = memberService.loginCheck(loginRequest)

        return loginCheck(loginMember, request, model)
    }

    private fun loginCheck(loginMember: Member?, request: HttpServletRequest, model: Model): String {
        return if (loginMember != null) {
            val session = request.session
            session.setAttribute("member", loginMember)
            model.addAttribute("memberName", loginMember.name)
            "main"
        } else {
            model.addAttribute("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.")
            "home"
        }
    }

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest, model: Model): String {
        request.session.invalidate()
        model.addAttribute("loginRequest", LoginRequest(null, null))
        return "home"
    }
}