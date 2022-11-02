package study.planner.app.plantransaction.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import study.planner.app.plantransaction.controller.dto.PlanTransactionRequest
import study.planner.app.plantransaction.service.PlanTransactionService
import java.time.LocalDate
import javax.validation.Valid

@Controller
@RequestMapping("/plan-transaction")
class PlanTransactionController(

        private val planTransactionService: PlanTransactionService
) {

    @GetMapping("/{studyPlanId}")
    fun createPlanTransactionForm(model: Model, @PathVariable studyPlanId: Long): String {
        val request = PlanTransactionRequest(studyPlanId, null, LocalDate.now())
        model.addAttribute("planTransaction", request)

        return "plan-transaction/createPlanTransactionForm"
    }

    @PostMapping("")
    fun todayStudy(@Valid @ModelAttribute("planTransaction") request: PlanTransactionRequest, result: BindingResult): String {
        if (result.hasErrors()) {
            return "plan-transaction/createPlanTransactionForm"
        }

        planTransactionService.todayStudy(request)
        return "redirect:/"
    }
}