package study.planner.app.member.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import study.planner.app.member.Member
import study.planner.app.member.dto.LoginRequest
import study.planner.app.member.dto.MemberRequest
import study.planner.app.member.service.MemberService
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@Controller
class MemberController(
        private val memberService: MemberService
) {

    @GetMapping("/member")
    fun createMemberForm(model: Model): String {
        model.addAttribute("member", MemberRequest(null, null, null))
        return "members/createMemberForm"
    }

    @PostMapping("/member")
    fun createMember(@Valid request: MemberRequest, result: BindingResult): String {
        if (result.hasErrors()) {
            return "members/createMemberForm"
        }

        memberService.save(request)
        return "redirect:/"
    }
}