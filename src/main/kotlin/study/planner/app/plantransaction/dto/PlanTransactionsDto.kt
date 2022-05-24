package study.planner.app.plantransaction.dto

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDate

data class PlanTransactionsDto @QueryProjection constructor(
        val dayFigure: Int,
        val date: LocalDate,
        val currentFigure: Int,
)
