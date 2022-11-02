package study.planner.app.studyplan.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import study.planner.app.studyplan.domain.PlanStatus
import study.planner.app.studyplan.service.query.StudyPlanQueryService

@Controller
class StudyPlanQueryController(

        private val studyPlanQueryService: StudyPlanQueryService
) {

    @GetMapping("/study-plans/{memberId}")
    fun studyPlans(@PathVariable memberId: Long, @RequestParam status: PlanStatus, model: Model): String {

        val studyPlans = studyPlanQueryService.studyPlans(memberId, status)

        model.addAttribute("studyPlans", studyPlans)

        return "/studyplan/studyPlans"
    }

    @GetMapping("/study-plan/detail/{studyPlanId}")
    fun studyPlanDetails(@PathVariable studyPlanId: Long, model: Model): String {
        val studyPlanDetails = studyPlanQueryService.studyPlanDetails(studyPlanId)

        model.addAttribute("studyPlanDetails", studyPlanDetails)

        return "/studyplan/studyPlanDetails"
    }


}