package study.planner.app.plantransaction.repository

import org.springframework.data.domain.Pageable
import study.planner.app.plantransaction.dto.PlanTransactionsDto

interface PlanTransactionRepositoryCustom {

    fun planAvg(studyPlanId: Long): Double?

    fun planTransactions(studyPlanId: Long, pageable: Pageable): List<PlanTransactionsDto>
}