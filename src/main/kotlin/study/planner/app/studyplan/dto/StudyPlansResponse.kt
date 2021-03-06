package study.planner.app.studyplan.dto

import study.planner.app.studyplan.domain.PlanStatus
import study.planner.app.studyplan.domain.StudyPlan

data class StudyPlansResponse(
        val studyPlanId: Long?,
        val title: String?,
        val content: String?,
        val currentFigure: Int,
        val completeFigure: Int,
        val currentProgress: Double?,
        val expectCompleteRestDate: Long?,
        val status: PlanStatus
) {
    companion object {
        fun toDto(studyPlan: StudyPlan): StudyPlansResponse {
                return StudyPlansResponse(
                        studyPlan.id,
                        studyPlan.title,
                        studyPlan.content,
                        studyPlan.currentFigure,
                        studyPlan.completeFigure,
                        studyPlan.currentProgress(studyPlan.currentFigure),
                        studyPlan.restExpectCompleteDate(),
                        studyPlan.status
                )
        }
    }
}
