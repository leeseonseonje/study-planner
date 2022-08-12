package study.planner.app.studyplan.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import study.planner.app.studyplan.dto.StudyPlanRegistrationRequest
import study.planner.app.studyplan.service.StudyPlanService
import javax.validation.Valid

@Controller
@RequestMapping("/study-plan")
class StudyPlanController(

        private val studyPlanService: StudyPlanService,
) {

    @GetMapping("/{memberId}")
    fun studyPlanRegistrationForm(model: Model, @PathVariable memberId: Long): String {
        val request = StudyPlanRegistrationRequest(memberId, null, null, 0, null)
        model.addAttribute("studyPlan", request)

        return "studyplans/createStudyPlanForm"
    }

    @PostMapping("")
    fun studyPlanRegistration(@Valid @ModelAttribute("studyPlan") request: StudyPlanRegistrationRequest,
                              result:BindingResult): String {

        if (result.hasErrors()) {
            return "studyplans/createStudyPlanForm"
        }

        studyPlanService.studyPlanRegistration(request)
        return ""
    }
}