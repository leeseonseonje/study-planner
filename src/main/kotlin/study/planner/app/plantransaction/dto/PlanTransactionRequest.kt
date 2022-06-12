package study.planner.app.plantransaction.dto

import java.time.LocalDate

data class PlanTransactionRequest(
        var studyPlanId: Long,
        var dayFigure: Int,
        var day: LocalDate
)
