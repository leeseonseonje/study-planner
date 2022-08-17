package study.planner.app.studyplan.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import study.planner.app.studyplan.domain.PlanStatus
import study.planner.app.studyplan.domain.PlanStatus.*
import study.planner.app.studyplan.dto.StudyPlanDetailsResponse
import study.planner.app.studyplan.dto.StudyPlansResponse
import study.planner.app.studyplan.service.query.StudyPlanQueryService

@Controller
class StudyPlanQueryController(

        private val studyPlanQueryService: StudyPlanQueryService
) {

    @GetMapping("/study-plans/{memberId}")
    fun studyPlans(@PathVariable memberId: Long, @RequestParam status: PlanStatus, model: Model): String {

        val studyPlans = studyPlanQueryService.studyPlans(memberId, status)

        model.addAttribute("studyPlans", studyPlans)

//        return if (status == ING) "/studyplans/studyPlans" else "/studyplans/completeStudyPlans"
        return "/studyplans/studyPlans"
    }

//    @GetMapping("/study-plan/{studyPlanId}")
//    fun studyPlanDetails(@PathVariable studyPlanId: Long): StudyPlanDetailsResponse? {
//        return studyPlanQueryService.studyPlanDetails(studyPlanId)
//    }


}