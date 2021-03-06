package study.planner.app.studyplan.repository

import org.springframework.data.jpa.repository.JpaRepository
import study.planner.app.member.Member
import study.planner.app.studyplan.domain.StudyPlan

interface StudyPlanRepository : JpaRepository<StudyPlan, Long> {

    fun findByMember(member: Member?): List<StudyPlan>

}