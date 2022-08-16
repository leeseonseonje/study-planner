package study.planner.app.studyplan.service.query

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.planner.app.member.repository.MemberRepository
import study.planner.app.plantransaction.repository.PlanTransactionRepository
import study.planner.app.studyplan.domain.PlanStatus
import study.planner.app.studyplan.dto.StudyPlanDetailsResponse
import study.planner.app.studyplan.dto.StudyPlansResponse
import study.planner.app.studyplan.repository.StudyPlanRepository
import kotlin.streams.toList

@Service
@Transactional(readOnly = true)
class StudyPlanQueryService(
        private val memberRepository: MemberRepository,
        private val studyPlanRepository: StudyPlanRepository,
        private val planTransactionRepository: PlanTransactionRepository
) {

    fun studyPlans(memberId: Long, status: PlanStatus): List<StudyPlansResponse> {
        val member = memberRepository.findByIdOrNull(memberId)

        val studyPlans = studyPlanRepository.findByMemberAndStatus(member, status)

        return studyPlans.stream().map { StudyPlansResponse.toDto(it) }.toList()
    }

    fun studyPlanDetails(studyPlanId: Long): StudyPlanDetailsResponse? {
        val studyPlan = studyPlanRepository.findByIdOrNull(studyPlanId)

        val planAvg = planTransactionRepository.planAvg(studyPlanId)

        return StudyPlanDetailsResponse.toDto(studyPlan, planAvg)
    }
}