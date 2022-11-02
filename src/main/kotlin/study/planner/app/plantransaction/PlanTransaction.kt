package study.planner.app.plantransaction

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

    var currentFigure: Int

    private constructor(studyPlan: StudyPlan?, day: LocalDate, dayFigure: Int, currentFigure: Int) {
        this.studyPlan = studyPlan
        this.day = day
        this.dayFigure = dayFigure
        this.currentFigure = currentFigure
    }

    companion object {
        fun of(currentFigure: Int, studyPlan: StudyPlan?, day: LocalDate): PlanTransaction {
            val dayFigure = currentFigure.minus(studyPlan?.currentFigure!!)
            return PlanTransaction(studyPlan, day, dayFigure, currentFigure)
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