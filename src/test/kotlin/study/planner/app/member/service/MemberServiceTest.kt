package study.planner.app.member.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import study.planner.app.member.dto.MemberRequest
import java.lang.IllegalStateException

@Transactional
@SpringBootTest
internal class MemberServiceTest(
        @Autowired
        val memberService: MemberService
) {

    @Test
    fun duplicationsTest() {
        val requestA = MemberRequest("memberA", "leeseonseonje9323@gmail.com", "1234")
        val requestB = MemberRequest("memberA", "leeseonseonje9323@gmail.com", "1234")

        memberService.save(requestA)

        Assertions.assertThatThrownBy {memberService.save(requestB)}.isInstanceOf(IllegalStateException::class.java)

    }
}