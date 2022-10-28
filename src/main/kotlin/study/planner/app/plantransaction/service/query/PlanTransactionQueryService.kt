package study.planner.app.plantransaction.service.query

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.planner.app.plantransaction.dto.PlanTransactionsResponse
import study.planner.app.plantransaction.repository.PlanTransactionRepository
import study.planner.app.studyplan.repository.StudyPlanRepository
import kotlin.streams.toList

@Service
@Transactional(readOnly = true)
class PlanTransactionQueryService(
        private val studyPlanRepository: StudyPlanRepository,
        private val planTransactionRepository: PlanTransactionRepository
) {

    fun planTransactions(studyPlanId: Long, page: Int): PlanTransactionsPageable<List<PlanTransactionsResponse?>> {
        val studyPlan = studyPlanRepository.findByIdOrNull(studyPlanId)

        val planTransactions = planTransactionRepository.planTransactions(studyPlanId, PageRequest.of(page, 10))
        val result = planTransactions.content.stream().map { PlanTransactionsResponse.toDto(it, studyPlan) }.toList()
        return PlanTransactionsPageable(result, planTransactions.totalPages)
    }
}