package study.planner.app.studyplan.controller.dto

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.*

data class StudyPlanRegistrationRequest(

        var memberId: Long?,

        @field:NotEmpty(message = "제목을 입력해 주세요.")
        var title: String?,

        var content: String?,

        @field:Min(value = 1, message = "1이상의 값을 입력해주세요.")
        @field:NotNull(message = "목표를 입력해 주세요.")
        var completeFigure: Int?,

        @field:DateTimeFormat(pattern = "yyyy-MM-dd")
        @field:NotNull(message = "날짜를 입력해 주세요.")
        var expectCompleteDate: LocalDate?
){

}
