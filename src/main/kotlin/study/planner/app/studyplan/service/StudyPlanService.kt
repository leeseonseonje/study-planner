package study.planner.app.studyplan.service

import org.springframework.transaction.annotation.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import study.planner.app.member.repository.MemberRepository
import study.planner.app.studyplan.domain.StudyPlan
import study.planner.app.studyplan.dto.StudyPlanRegistrationRequest
import study.planner.app.studyplan.repository.StudyPlanRepository

@Service
@Transactional
class StudyPlanService(
        private val studyPlanRepository: StudyPlanRepository,
        private val memberRepository: MemberRepository
) {

    fun studyPlanRegistration(request: StudyPlanRegistrationRequest) {
        val member = memberRepository.findByIdOrNull(request.memberId)

        member.let {
            val studyPlan = StudyPlan.of(request, member)
            studyPlanRepository.save(studyPlan)
        }
    }
}