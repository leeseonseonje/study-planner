package study.planner.app.plantransaction.service.query

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional
import study.planner.app.member.repository.MemberRepository
import study.planner.app.plantransaction.PlanTransaction
import study.planner.app.plantransaction.dto.PlanTransactionRequest
import study.planner.app.plantransaction.repository.PlanTransactionRepository
import study.planner.app.plantransaction.service.PlanTransactionService
import study.planner.app.studyplan.domain.StudyPlan
import study.planner.app.studyplan.dto.StudyPlanRegistrationRequest
import study.planner.app.studyplan.repository.StudyPlanRepository
import java.time.LocalDate
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.abs

@Transactional
@SpringBootTest
internal class PlanTransactionQueryServiceTest(
        @Autowired
        private val planTransactionQueryService: PlanTransactionQueryService,
        @Autowired
        private val planTransactionRepository: PlanTransactionRepository,
        @Autowired val planTransactionService: PlanTransactionService,
        @Autowired
        private val studyPlanRepository: StudyPlanRepository,
        @Autowired
        private val memberRepository: MemberRepository
){

    @Test
    fun planTransactionsTest() {

        val member = memberRepository.findByIdOrNull(1L)
        val studyPlan = StudyPlan.of(StudyPlanRegistrationRequest(1L, "title", "content", 1500, LocalDate.now()), member)
        val savedStudyPlan = studyPlanRepository.save(studyPlan)
        for (i in 1..10) {
            val now = i.times(10)
            planTransactionService.todayStudy(PlanTransactionRequest(savedStudyPlan.id!!, now, LocalDate.of(i, 12, 25)))
        }

        val planTransactions = planTransactionQueryService.planTransactions(savedStudyPlan.id!!, 0)

        for (planTransaction in planTransactions.data) {
            assertThat(planTransaction?.dayFigure).isEqualTo(10)
            assertThat(planTransaction?.dayProgress).isEqualTo(0.7)
        }

    }
}