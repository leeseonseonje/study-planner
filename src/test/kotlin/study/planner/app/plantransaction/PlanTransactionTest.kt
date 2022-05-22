package study.planner.app.plantransaction

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import study.planner.app.plantransaction.dto.PlanTransactionRequest
import study.planner.app.studyplan.domain.StudyPlan

class PlanTransactionTest {

    @Test
    fun todayProgressCalculate() {

        val request = PlanTransactionRequest(1L, 10)
        val planTransaction = PlanTransaction.of(request, null)

        val todayProgress = planTransaction.todayProgress(1000)

        assertThat(todayProgress).isEqualTo(1.0)
    }

    @Test
    @DisplayName("소수점 테스트")
    fun calTest() {
        val request = PlanTransactionRequest(1L, 21)
        val planTransaction = PlanTransaction.of(request, null)

        val todayProgress = planTransaction.todayProgress(1253)

        assertThat(todayProgress).isEqualTo(1.7)
    }

    @Test
    @DisplayName("오늘 수치 더하기")
    fun figurePlusTest() {
        val request = PlanTransactionRequest(1L, 50)
        val planTransaction = PlanTransaction.of(request, null)

        planTransaction.todayPlanTransactionUpdate(163)

        assertThat(planTransaction.dayFigure).isEqualTo(213)
    }
}