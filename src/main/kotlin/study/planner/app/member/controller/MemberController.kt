package study.planner.app.member.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import study.planner.app.member.dto.MemberRequest
import study.planner.app.member.service.MemberService

@RestController
class MemberController(
        private val memberService: MemberService
) {

    @PostMapping("/member")
    fun save(@RequestBody request: MemberRequest) {
        memberService.save(request)
    }
}