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
            return modelAddMember(model, member)
        }
        return notLogin(model)
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
            modelAddMember(model, loginMember)
        } else {
            notLogin(model)
        }
    }

    private fun modelAddMember(model: Model, member: Member): String {
        model.addAttribute("memberName", member.name)
        model.addAttribute("memberId", member.id)
        return "main"
    }

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest, model: Model): String {
        request.session.invalidate()
        notLogin(model)
        return "home"
    }

    private fun notLogin(model: Model): String {
        model.addAttribute("loginRequest", LoginRequest(null, null))
        return "home"
    }
}