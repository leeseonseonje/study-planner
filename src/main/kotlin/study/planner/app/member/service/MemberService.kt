package study.planner.app.member.service

import org.springframework.stereotype.Service
import study.planner.app.member.Member
import study.planner.app.member.dto.MemberRequest
import study.planner.app.member.repository.MemberRepository
import javax.transaction.Transactional

@Service
@Transactional
class MemberService(
        private val memberRepository: MemberRepository
) {

    fun save(request: MemberRequest) {
        duplicationValidationEmail(request.email)
        val member = Member(request)
        memberRepository.save(member)
    }

    private fun duplicationValidationEmail(email: String?) {
        if (memberRepository.existsByEmail(email)) {
            throw IllegalStateException("이미 존재하는 회원입니다.")
        }
    }
}