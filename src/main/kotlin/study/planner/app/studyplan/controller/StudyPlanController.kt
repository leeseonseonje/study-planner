package study.planner.app.studyplan.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import study.planner.app.member.repository.MemberRepository
import study.planner.app.studyplan.dto.StudyPlanRegistrationRequest
import study.planner.app.studyplan.service.StudyPlanService
import java.time.LocalDate
import javax.validation.Valid

@Controller
@RequestMapping("/study-plan")
class StudyPlanController(

        private val studyPlanService: StudyPlanService,
        private val memberRepository: MemberRepository,
) {

    @GetMapping("/{memberId}")
    fun studyPlanRegistrationForm(model: Model, @PathVariable memberId: Long): String {

        val findMember = memberRepository.findById(memberId).get()

        val request = StudyPlanRegistrationRequest(memberId, null, null,
                0, null)

        model.addAttribute("memberName", findMember.name)
        model.addAttribute("memberId", memberId)
        model.addAttribute("studyPlan", request)

        return "studyplan/createStudyPlanForm"
    }

    @PostMapping("")
    fun studyPlanRegistration(@Valid @ModelAttribute("studyPlan") request: StudyPlanRegistrationRequest,
                              result:BindingResult): String {

        request.expectCompleteDate?.let {
            if (it.isBefore(LocalDate.now())) {
                result.addError(FieldError("studyPlan", "expectCompleteDate",
                        "입력한 날짜는 과거에요"))
            }
        }

        return registrationRedirect(result, request)
    }

    private fun registrationRedirect(result: BindingResult, request: StudyPlanRegistrationRequest): String {
        if (result.hasErrors()) {
            return "studyplan/createStudyPlanForm"
        }

        studyPlanService.studyPlanRegistration(request)
        return "main"
    }
}