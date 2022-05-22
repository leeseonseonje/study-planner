package study.planner.app.plantransaction.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.planner.app.plantransaction.PlanTransaction
import study.planner.app.plantransaction.dto.PlanTransactionRequest
import study.planner.app.plantransaction.repository.PlanTransactionRepository
import study.planner.app.studyplan.domain.StudyPlan
import study.planner.app.studyplan.repository.StudyPlanRepository
import java.time.LocalDate

@Service
@Transactional
class PlanTransactionService(

        private val studyPlanRepository: StudyPlanRepository,
        private val planTransactionRepository: PlanTransactionRepository
) {

    fun todayStudy(request: PlanTransactionRequest): PlanTransaction {
        val studyPlan = findStudyPlan(request)

        val todayPlanTransaction = planTransactionRepository.findByStudyPlanAndDay(studyPlan, LocalDate.now())

        todayPlanTransaction?.let {
            it.todayPlanTransactionUpdate(request.dayFigure)
            return it
        }

        return newTodayPlanTransaction(request, studyPlan)
    }

    private fun findStudyPlan(request: PlanTransactionRequest): StudyPlan? {
        val studyPlan = studyPlanRepository.findByIdOrNull(request.studyPlanId)

        studyPlan?.currentFigureCalculate(request.dayFigure)
        return studyPlan
    }

    private fun newTodayPlanTransaction(request: PlanTransactionRequest, studyPlan: StudyPlan?): PlanTransaction {
        val planTransaction = PlanTransaction.of(request, studyPlan)

        return planTransactionRepository.save(planTransaction)
    }
}