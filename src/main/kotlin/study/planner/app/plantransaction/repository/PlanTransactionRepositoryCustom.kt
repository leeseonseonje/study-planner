package study.planner.app.plantransaction.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import study.planner.app.plantransaction.repository.dto.PlanTransactionsDto

interface PlanTransactionRepositoryCustom {

    fun dayFigures(studyPlanId: Long): List<Int>?

    fun planTransactions(studyPlanId: Long, pageable: Pageable): Page<PlanTransactionsDto>
}