package study.planner.app.studyplan.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import study.planner.app.studyplan.dto.StudyPlanRegistrationRequest
import study.planner.app.studyplan.service.StudyPlanService

@Controller
@RequestMapping("/study-plan")
class StudyPlanController(

        private val studyPlanService: StudyPlanService,
) {

    @GetMapping("/{memberId}")
    fun studyPlanRegistrationForm(model: Model, @PathVariable memberId: Long): String {
        println("studyPlan")
        val request = StudyPlanRegistrationRequest(memberId, null, null, 0, null)
        model.addAttribute("studyPlan", request)

        return "studyplans/createStudyPlanForm"
    }

    @PostMapping("")
    fun studyPlanRegistration(request: StudyPlanRegistrationRequest) {
        studyPlanService.studyPlanRegistration(request)
    }
}