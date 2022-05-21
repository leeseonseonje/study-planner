package study.planner.app.plantransaction.repository

import org.springframework.data.jpa.repository.JpaRepository
import study.planner.app.plantransaction.PlanTransaction
import study.planner.app.studyplan.domain.StudyPlan
import java.time.LocalDate

interface PlanTransactionRepository : JpaRepository<PlanTransaction, Long> {

    fun findByStudyPlanAndDay(studyPlan: StudyPlan?, day: LocalDate): PlanTransaction?
}