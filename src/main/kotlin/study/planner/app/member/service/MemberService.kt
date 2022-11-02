package study.planner.app.member.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.planner.app.member.Member
import study.planner.app.member.controller.dto.LoginRequest
import study.planner.app.member.controller.dto.MemberRequest
import study.planner.app.member.repository.MemberRepository

@Service
@Transactional
class MemberService(
        private val memberRepository: MemberRepository
) {

    fun join(request: MemberRequest) {
        duplicationValidationEmail(request.email)
        val member = Member.of(request)
        memberRepository.save(member)
    }

    private fun duplicationValidationEmail(email: String?) {
        if (memberRepository.existsByEmail(email)) {
            throw IllegalStateException("이미 존재하는 회원입니다.")
        }
    }

    fun loginCheck(loginRequest: LoginRequest): Member? {
        val member = memberRepository.findByEmail(loginRequest.email) ?: return null

        if (!member.password.equals(loginRequest.password)) {
            return null
        }
        return member
    }
}