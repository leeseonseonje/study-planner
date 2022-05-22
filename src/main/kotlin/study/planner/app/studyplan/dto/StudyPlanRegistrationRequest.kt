package study.planner.app.studyplan.dto

import java.time.LocalDate

data class StudyPlanRegistrationRequest(
        var memberId: Long?,
        var title: String?,
        var content: String?,
        var completeFigure: Int,
        var expectCompleteDate: LocalDate?
)
