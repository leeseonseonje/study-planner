package study.planner.app.plantransaction.dto

import java.time.LocalDate
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class PlanTransactionRequest(
        var studyPlanId: Long,

        @field:Min(value = 1, message = "1이상의 값을 입력해주세요.")
        @field:NotNull(message = "목표를 입력해 주세요.")
        var dayFigure: Int?,

        var day: LocalDate
)
