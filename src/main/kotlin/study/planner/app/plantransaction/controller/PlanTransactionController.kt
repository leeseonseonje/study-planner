package study.planner.app.plantransaction.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import study.planner.app.plantransaction.dto.PlanTransactionRequest
import study.planner.app.plantransaction.service.PlanTransactionService

@RestController
class PlanTransactionController(

        private val planTransactionService: PlanTransactionService
) {

    @PostMapping("/plan-transaction")
    fun todayStudy(@RequestBody request: PlanTransactionRequest) {
        planTransactionService.todayStudy(request)
    }
}