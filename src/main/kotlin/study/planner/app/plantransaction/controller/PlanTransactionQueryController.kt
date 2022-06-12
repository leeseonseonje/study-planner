package study.planner.app.plantransaction.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import study.planner.app.plantransaction.dto.PlanTransactionsResponse
import study.planner.app.plantransaction.service.query.PlanTransactionQueryService

@RestController
class PlanTransactionQueryController(

        private val planTransactionQueryService: PlanTransactionQueryService
) {

    @GetMapping("/plan-transactions/{studyPlanId}")
    fun planTransactions(@PathVariable studyPlanId: Long, @RequestParam page: Int): List<PlanTransactionsResponse?> {
        return planTransactionQueryService.planTransactions(studyPlanId, page)
    }
}