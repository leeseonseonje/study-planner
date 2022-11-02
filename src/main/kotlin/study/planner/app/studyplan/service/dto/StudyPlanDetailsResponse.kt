package study.planner.app.studyplan.service.dto

import study.planner.app.studyplan.domain.PlanStatus
import study.planner.app.studyplan.domain.StudyPlan
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class StudyPlanDetailsResponse(
        //제목, 내용, 현재/총 수치, 현재 진행도, 남은 일 수, 예상완료날짜, status(진행중, 완료, 만료), 시작 날짜, 시작 후 몇일
        //하루 평균 수치, 진행도
        val studyPlanId: Long?,
        val title: String?,
        val content: String?,
        val currentFigure: Int,
        val completeFigure: Int?,
        val currentProgress: Double?,
        val expectCompleteRestDate: Long?,
        val expectCompleteDate: String?,
        val status: PlanStatus,
        val startDate: String,
        val afterStartDate: Long,
        val avgFigure: Int?,
        val avgProgress: Double?
) {
    companion object {
        fun toDto(studyPlan: StudyPlan?, planAvg: Int?): StudyPlanDetailsResponse? {
            val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 d일")
            studyPlan?.let {
                return StudyPlanDetailsResponse(
                        it.id,
                        it.title,
                        it.content,
                        it.currentFigure,
                        it.completeFigure,
                        it.progressConverter(it.currentFigure),
                        it.restExpectCompleteDate(),
                        formatter.format(it.expectCompleteDate),
                        it.status,
                        formatter.format(it.registrationDate),
                        it.afterStartDate(),
                        planAvg,
                        it.progressConverter(planAvg)
                )
            }
            return null
        }
    }
    fun dateTimeFormatter(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 d일")

        return formatter.format(date)
    }
}
