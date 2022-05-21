package study.planner.app.studyplan.domain

import study.planner.app.member.Member
import study.planner.app.studyplan.dto.StudyPlanRegistrationRequest
import java.time.LocalDate
import javax.persistence.*
import javax.persistence.FetchType.*

@Entity
class StudyPlan(
        request: StudyPlanRegistrationRequest,

        @ManyToOne(fetch = LAZY)
        @JoinColumn(name = "member_id")
        var member: Member?,

        var registrationDate: LocalDate = LocalDate.now(),

        var status: PlanStatus = PlanStatus.ING
) {
    @Id
    @GeneratedValue
    @Column(name = "study_plan_id")
    val id: Long? = null

    var title: String? = request.title

    var content: String? = request.content

    var completeFigure: Int? = request.completeFigure

    var currentFigure: Int? = null

    var expectCompleteDate: LocalDate? = request.expectCompleteDate

    var completeDate: LocalDate? = null
}
