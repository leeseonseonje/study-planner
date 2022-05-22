package study.planner.app.plantransaction.service

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional
import study.planner.app.member.Member
import study.planner.app.member.repository.MemberRepository
import study.planner.app.plantransaction.dto.PlanTransactionRequest
import study.planner.app.plantransaction.repository.PlanTransactionRepository
import study.planner.app.studyplan.domain.StudyPlan
import study.planner.app.studyplan.dto.StudyPlanRegistrationRequest
import study.planner.app.studyplan.repository.StudyPlanRepository
import java.time.LocalDate

@Transactional
@SpringBootTest
internal class PlanTransactionServiceTest(
        @Autowired
        val planTransactionService: PlanTransactionService,
        @Autowired
        val memberRepository: MemberRepository,
        @Autowired
        val studyPlanRepository: StudyPlanRepository,
        @Autowired
        val planTransactionRepository: PlanTransactionRepository
) {

    var request: StudyPlanRegistrationRequest? = null
    var member: Member? = null
    var studyPlan: StudyPlan? = null

    @BeforeEach
    fun init() {
        request = StudyPlanRegistrationRequest(1L, "title", "content", 1000, LocalDate.of(2022, 7, 1))
        member = memberRepository.findByIdOrNull(1L)
        studyPlan = StudyPlan.of(request!!, member)
        studyPlanRepository.save(studyPlan!!)
    }

    @Test
    @DisplayName("오늘 수치 저장  -> 오늘 등록한 계획이 처음이면 저장")
    fun planTransactionSave() {

        val todayStudy = planTransactionService.todayStudy(PlanTransactionRequest(studyPlan?.id!!, 100))

        assertThat(todayStudy.dayFigure).isEqualTo(100)
    }

    @Test
    @DisplayName("오늘 수치 수정 -> 오늘 등록한 계획이 이미 있으면 수정")
    fun funTransactionUpdate() {

        val todayStudy = planTransactionService.todayStudy(PlanTransactionRequest(studyPlan?.id!!, 100))

        assertThat(studyPlan!!.currentFigure).isEqualTo(100)
        assertThat(todayStudy.dayFigure).isEqualTo(100)

        val todayStudy1 = planTransactionService.todayStudy(PlanTransactionRequest(studyPlan?.id!!, 150))
        assertThat(studyPlan!!.currentFigure).isEqualTo(250)
        assertThat(todayStudy1.dayFigure).isEqualTo(250)
    }

    @Test
    fun returnNull() {
        val studyPlanA = studyPlanRepository.findByIdOrNull(studyPlan!!.id)

        val t = t(studyPlanA)
        println(t?.id)
    }

    private fun t(studyPlan: StudyPlan?): StudyPlan? {
        studyPlan.let {
            it?.currentFigureCalculate(100)
            return studyPlan
        }
    }

    @Test
    fun nullRepository() {
        val findByStudyPlanAndDay = planTransactionRepository.findByStudyPlanAndDay(null, LocalDate.now())
        println(findByStudyPlanAndDay?.id)
    }
}