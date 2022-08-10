package study.planner.app.plantransaction.dto

import study.planner.app.studyplan.domain.StudyPlan
import java.time.LocalDate

data class PlanTransactionsResponse(
        val day: LocalDate,
        val dayFigure: Int,
        val dayProgress: Double?,
        val currentFigure: Int,
        val completeFigure: Int,
        val currentProgress: Double?
) {
    companion object {
        fun toDto(dto: PlanTransactionsDto, studyPlan: StudyPlan?): PlanTransactionsResponse? {
            studyPlan?.let {
                return PlanTransactionsResponse(
                        dto.date,
                        dto.dayFigure,
                        it.progressConverter(dto.dayFigure),
                        dto.currentFigure,
                        it.completeFigure,
                        it.progressConverter(dto.currentFigure)
                )
            }
            return null
        }
    }
}