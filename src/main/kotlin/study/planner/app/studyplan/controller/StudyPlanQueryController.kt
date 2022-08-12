package study.planner.app.studyplan.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import study.planner.app.studyplan.dto.StudyPlanDetailsResponse
import study.planner.app.studyplan.dto.StudyPlansResponse
import study.planner.app.studyplan.service.query.StudyPlanQueryService

@RestController
class StudyPlanQueryController(

        private val studyPlanQueryService: StudyPlanQueryService
) {

//    @GetMapping("/study-plans/{memberId}")
//    fun studyPlans(@PathVariable memberId: Long): List<StudyPlansResponse> {
//        return studyPlanQueryService.studyPlans(memberId)
//    }

//    @GetMapping("/study-plan/{studyPlanId}")
//    fun studyPlanDetails(@PathVariable studyPlanId: Long): StudyPlanDetailsResponse? {
//        return studyPlanQueryService.studyPlanDetails(studyPlanId)
//    }


}