package study.planner.app.studyplan.dto

import study.planner.app.studyplan.domain.PlanStatus
import study.planner.app.studyplan.domain.StudyPlan
import java.time.LocalDate

data class StudyPlanDetailsResponse(
        //제목, 내용, 현재/총 수치, 현재 진행도, 남은 일 수, 예상완료날짜, status(진행중, 완료, 만료), 시작 날짜, 시작 후 몇일
        //하루 평균 수치, 진행도
        val studyPlanId: Long?,
        val title: String?,
        val content: String?,
        val currentFigure: Int,
        val completeFigure: Int,
        val currentProgress: Double,
        val expectCompleteRestDate: Long?,
        val expectCompleteDate: LocalDate?,
        val status: PlanStatus,
        val startDate: LocalDate,
        val afterStartDate: Long,
        val avgFigure: Double,
        val avgProgress: Double
) {
    companion object {
        fun toDto(studyPlan: StudyPlan?, planAvg: Double): StudyPlanDetailsResponse? {
                studyPlan?.let {
                return StudyPlanDetailsResponse(
                        it.id,
                        it.title,
                        it.content,
                        it.currentFigure,
                        it.completeFigure,
                        it.currentProgress(),
                        it.restExpectCompleteDate(),
                        it.expectCompleteDate,
                        it.status,
                        it.registrationDate,
                        it.afterStartDate(),
                        planAvg,
                        it.averageProgress(planAvg)
                )
            }
            return null
        }
    }
}
