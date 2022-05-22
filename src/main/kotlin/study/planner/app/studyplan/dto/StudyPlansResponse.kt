package study.planner.app.studyplan.dto

import study.planner.app.studyplan.domain.PlanStatus

data class StudyPlansResponse(
        val studyPlanId: Long?,
        val title: String?,
        val content: String?,
        val currentFigure: Int,
        val completeFigure: Int,
        val currentProgress: Double,
        val expectCompleteRestDate: Long?,
        val status: PlanStatus
)
