package study.planner.app.studyplan.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import study.planner.app.studyplan.dto.StudyPlanRegistrationRequest
import study.planner.app.studyplan.service.StudyPlanService

@RestController
class StudyPlanController(

        private val studyPlanService: StudyPlanService
) {

    @PostMapping("/study-plan")
    fun studyPlanRegistration(request: StudyPlanRegistrationRequest) {
        studyPlanService.studyPlanRegistration(request)
    }
}