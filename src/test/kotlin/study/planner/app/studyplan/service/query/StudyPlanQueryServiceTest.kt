package study.planner.app.studyplan.service.query

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional
import study.planner.app.member.repository.MemberRepository
import study.planner.app.studyplan.domain.PlanStatus.*
import study.planner.app.studyplan.domain.StudyPlan
import study.planner.app.studyplan.controller.dto.StudyPlanRegistrationRequest
import study.planner.app.studyplan.repository.StudyPlanRepository
import java.time.LocalDate

@Transactional
@SpringBootTest
internal class StudyPlanQueryServiceTest(
        @Autowired
        val studyPlanQueryService: StudyPlanQueryService,
        @Autowired
        val memberRepository: MemberRepository,
        @Autowired
        val studyPlanRepository: StudyPlanRepository
) {

    @Test
    fun test() {
        val member = memberRepository.findByIdOrNull(0L)

        val findByMember = studyPlanRepository.findByMemberAndStatus(member, ING)

        println(findByMember.isEmpty())
    }

    @Test
    fun studyPlans() {
        val member = memberRepository.findByIdOrNull(1L)
        val now = LocalDate.now()

        for (i in 0 until 5) {
            val studyPlan = StudyPlan.of(StudyPlanRegistrationRequest(1L, "title", "content", 1000,
                    LocalDate.of(now.year.plus(1), now.month, now.dayOfMonth)), member)
            studyPlanRepository.save(studyPlan)
        }
        val studyPlans = studyPlanQueryService.studyPlans(1L, ING)

        for (studyPlan in studyPlans) {
            assertThat(studyPlan.currentFigure).isEqualTo(0)
            assertThat(studyPlan.currentProgress).isEqualTo(0.0)
            assertThat(studyPlan.completeFigure).isEqualTo(1000)
            assertThat(studyPlan.expectCompleteRestDate).isEqualTo(365)
        }
    }

    @Test
    fun studyPlanDetails() {
        val member = memberRepository.findByIdOrNull(1L)
        val now = LocalDate.now()

        val studyPlan = StudyPlan.of(StudyPlanRegistrationRequest(1L, "title", "content", 1000,
                LocalDate.of(now.year.plus(1), now.month, now.dayOfMonth)), member)
        studyPlan.currentFigureCalculate(100)
        val savedStudyPlan = studyPlanRepository.save(studyPlan)

        val studyPlanDetails = studyPlanQueryService.studyPlanDetails(savedStudyPlan.id!!)

        assertThat(studyPlanDetails?.currentFigure).isEqualTo(100)
        assertThat(studyPlanDetails?.currentProgress).isEqualTo(10.0)
        assertThat(studyPlanDetails?.completeFigure).isEqualTo(1000)
        assertThat(studyPlanDetails?.expectCompleteRestDate).isEqualTo(365)
        assertThat(studyPlanDetails?.startDate).isEqualTo(LocalDate.now())
        assertThat(studyPlanDetails?.afterStartDate).isEqualTo(0)
        assertThat(studyPlanDetails?.status).isEqualTo(ING)
    }
}