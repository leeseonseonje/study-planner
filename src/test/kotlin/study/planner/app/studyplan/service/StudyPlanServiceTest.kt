package study.planner.app.studyplan.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import study.planner.app.member.Member
import study.planner.app.member.controller.dto.MemberRequest
import study.planner.app.member.repository.MemberRepository
import study.planner.app.studyplan.controller.dto.StudyPlanRegistrationRequest
import java.time.LocalDate

@Transactional
@SpringBootTest
internal class StudyPlanServiceTest(
        @Autowired
        val studyPlanService: StudyPlanService,
        @Autowired
        val memberRepository: MemberRepository
) {

    @Test
    fun registration() {
        val member = Member.of(MemberRequest("name", "email", "1234"))
        val savedMember = memberRepository.save(member)
        val request = StudyPlanRegistrationRequest(
                savedMember.id,
                "title",
                "content",
                1000,
                LocalDate.of(2022, 7, 1))
        studyPlanService.studyPlanRegistration(request)
    }

}
