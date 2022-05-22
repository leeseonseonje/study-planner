package study.planner.app.studyplan.service.query

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.planner.app.member.repository.MemberRepository
import study.planner.app.studyplan.dto.StudyPlanDetailsResponse
import study.planner.app.studyplan.dto.StudyPlansResponse
import study.planner.app.studyplan.repository.StudyPlanRepository
import java.util.stream.Stream
import kotlin.streams.toList

@Service
@Transactional(readOnly = true)
class StudyPlanQueryService(
        private val memberRepository: MemberRepository,
        private val studyPlanRepository: StudyPlanRepository,
) {

    fun studyPlans(memberId: Long): List<StudyPlansResponse> {
        val member = memberRepository.findByIdOrNull(memberId)

        val studyPlans = studyPlanRepository.findByMember(member)

        return studyPlans.stream().map {
            StudyPlansResponse(it.id, it.title, it.content, it.currentFigure, it.completeFigure,
                    it.currentProgress(), it.restExpectCompleteDate(), it.status)
        }.toList()
    }

    fun studyPlanDetails(studyPlanId: Long?): StudyPlanDetailsResponse? {
        val studyPlan = studyPlanRepository.findByIdOrNull(studyPlanId)

        return StudyPlanDetailsResponse.toDto(studyPlan)
    }
}