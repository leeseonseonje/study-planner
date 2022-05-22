package study.planner.app.studyplan.domain

import study.planner.app.member.Member
import study.planner.app.studyplan.domain.PlanStatus.*
import study.planner.app.studyplan.dto.StudyPlanRegistrationRequest
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.time.temporal.ChronoUnit.*
import javax.persistence.*
import javax.persistence.FetchType.*
import kotlin.math.roundToInt

@Entity
class StudyPlan {
    @Id
    @GeneratedValue
    @Column(name = "study_plan_id")
    val id: Long? = null

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    var member: Member?

    var title: String?

    var content: String?

    var currentFigure: Int

    var completeFigure: Int

    var expectCompleteDate: LocalDate?

    var registrationDate: LocalDate

    var completeDate: LocalDate?

    var status: PlanStatus

    private constructor(member: Member?, title: String?, content: String?, currentFigure: Int, completeFigure: Int, expectCompleteDate: LocalDate?, registrationDate: LocalDate, completeDate: LocalDate?, status: PlanStatus) {
        this.member = member
        this.title = title
        this.content = content
        this.currentFigure = currentFigure
        this.completeFigure = completeFigure
        this.expectCompleteDate = expectCompleteDate
        this.registrationDate = registrationDate
        this.completeDate = completeDate
        this.status = status
    }

    companion object {
        fun of(request: StudyPlanRegistrationRequest, member: Member?): StudyPlan {
            return StudyPlan(
                    member,
                    request.title,
                    request.content,
                    0,
                    request.completeFigure,
                    request.expectCompleteDate,
                    LocalDate.now(),
                    null,
                    ING
            )
        }
    }
    fun currentFigureCalculate(todayFigure: Int) {
        this.currentFigure += todayFigure
        complete()
    }

    fun currentProgress(): Double {
        val progress = this.currentFigure.toDouble().div(this.completeFigure).times(100)
        return (progress * 10).roundToInt().div(10.0)
    }

    fun restExpectCompleteDate(): Long? {
        this.expectCompleteDate?.let {
            return registrationDate.until(it, DAYS)
        }
        return null
    }

    fun afterStartDate(): Long {
        return registrationDate.until(LocalDate.now(), DAYS)
    }

    private fun complete() {
        if (currentFigure >= completeFigure) {
            this.status = COMPLETE
        }
    }
}
