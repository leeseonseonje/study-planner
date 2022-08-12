package study.planner.app.studyplan.dto

import java.time.LocalDate
import javax.validation.constraints.NotEmpty

data class StudyPlanRegistrationRequest(
        var memberId: Long?,
        @field:NotEmpty(message = "제목을 입력해 주세요.")
        var title: String?,
        var content: String?,
        @field:NotEmpty(message = "목표를 입력해 주세요.")
        var completeFigure: Int,
        var expectCompleteDate: LocalDate?
)
