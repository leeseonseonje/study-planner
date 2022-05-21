package study.planner.app.plantransaction

import study.planner.app.plantransaction.dto.PlanTransactionRequest
import study.planner.app.studyplan.domain.StudyPlan
import java.time.LocalDate
import javax.persistence.*
import javax.persistence.FetchType.*
import kotlin.math.roundToInt

@Entity
class PlanTransaction(
        request: PlanTransactionRequest,

        @ManyToOne(fetch = LAZY)
        @JoinColumn(name = "study_plan_id")
        var studyPlan: StudyPlan?,

        var day: LocalDate = LocalDate.now(),

        var dayFigure: Int = request.dayFigure
) {

    @Id
    @GeneratedValue
    @Column(name = "plan_transaction_id")
    var id: Long? = null

    fun todayProgress(completeFigure: Int): Double? {
        val progress = dayFigure.toDouble().div(completeFigure).times(100)
        return (progress * 10).roundToInt().div(10.0)
    }
    fun todayPlanTransactionUpdate(dayFigure: Int) {
        this.dayFigure += dayFigure
    }
}