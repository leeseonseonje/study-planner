package study.planner.app.studyplan.repository

import org.springframework.data.jpa.repository.JpaRepository
import study.planner.app.member.Member
import study.planner.app.studyplan.domain.PlanStatus
import study.planner.app.studyplan.domain.StudyPlan

interface StudyPlanRepository : JpaRepository<StudyPlan, Long> {

    fun findByMemberAndStatus(member: Member?, status: PlanStatus): List<StudyPlan>

}