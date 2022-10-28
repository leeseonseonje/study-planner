package study.planner.app.plantransaction.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import study.planner.app.plantransaction.service.query.PlanTransactionQueryService

@Controller
class PlanTransactionQueryController(

        private val planTransactionQueryService: PlanTransactionQueryService
) {

    @GetMapping("/plan-transactions/{studyPlanId}")
    fun planTransactions(@PathVariable studyPlanId: Long, @RequestParam(defaultValue = "0") page: Int, model: Model): String {

        val pageable = planTransactionQueryService.planTransactions(studyPlanId, page)

        model.addAttribute("planTransactions", pageable.data)
        model.addAttribute("totalPages", pageable.totalPages)

        return "plan-transaction/planTransactions"
    }
}