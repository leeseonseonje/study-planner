package study.planner.app.plantransaction

import study.planner.app.plantransaction.dto.PlanTransactionRequest
import study.planner.app.studyplan.domain.StudyPlan
import java.time.LocalDate
import javax.persistence.*
import javax.persistence.FetchType.*
import kotlin.math.roundToInt

@Entity
class PlanTransaction {

    @Id
    @GeneratedValue
    @Column(name = "plan_transaction_id")
    var id: Long? = null

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "study_plan_id")
    var studyPlan: StudyPlan?

    var day: LocalDate

    var dayFigure: Int

    private constructor(studyPlan: StudyPlan?, day: LocalDate, dayFigure: Int) {
        this.studyPlan = studyPlan
        this.day = day
        this.dayFigure = dayFigure
    }

    companion object {
        fun of(dayFigure: Int, studyPlan: StudyPlan?): PlanTransaction {
            return PlanTransaction(studyPlan, LocalDate.now(), dayFigure)
        }
    }

    fun todayPlanTransactionUpdate(dayFigure: Int, currentFigure: Int) {
        this.dayFigure += (dayFigure - currentFigure)
    }

    fun todayProgress(completeFigure: Int): Double? {
        val progress = dayFigure.toDouble().div(completeFigure).times(100)
        return (progress * 10).roundToInt().div(10.0)
    }
}