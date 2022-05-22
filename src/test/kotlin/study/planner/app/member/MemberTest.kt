package study.planner.app.member

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import study.planner.app.member.dto.MemberRequest
import study.planner.app.member.repository.MemberRepository

@SpringBootTest
internal class MemberTest(
        @Autowired
        val memberRepository: MemberRepository
) {

    @Test
    fun test() {
        val request = MemberRequest("seon", "leeseonje9323@gmail.com")
        val member = Member.of(request)
        println(member.name)
        println(member.email)
        val savedMember = memberRepository.save(member)

        assertThat(member).isEqualTo(savedMember)
    }
}