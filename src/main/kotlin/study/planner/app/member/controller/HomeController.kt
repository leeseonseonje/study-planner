package study.planner.app.member.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.SessionAttribute
import study.planner.app.member.Member
import study.planner.app.member.dto.LoginRequest

@Controller
class HomeController {

    @GetMapping("/")
    fun home(@SessionAttribute(name = "member", required = false) member: Member?, model: Model): String {
        if (member != null) {
            model.addAttribute("memberName", member.name)
            return "main"
        }
        model.addAttribute("loginRequest", LoginRequest(null, null))
        return "home"
    }
}

