package study.planner.app.plantransaction.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import study.planner.app.plantransaction.PlanTransaction
import study.planner.app.plantransaction.dto.PlanTransactionRequest
import study.planner.app.plantransaction.repository.PlanTransactionRepository
import study.planner.app.studyplan.domain.StudyPlan
import study.planner.app.studyplan.repository.StudyPlanRepository
import java.time.LocalDate
import javax.transaction.Transactional

@Service
@Transactional
class PlanTransactionService(

        private val studyPlanRepository: StudyPlanRepository,
        private val planTransactionRepository: PlanTransactionRepository
) {

    fun todayStudy(request: PlanTransactionRequest): PlanTransaction {
        val studyPlan = studyPlanRepository.findByIdOrNull(request.studyPlanId)

        val todayPlanTransaction = planTransactionRepository.findByStudyPlanAndDay(studyPlan, LocalDate.now())

        todayPlanTransaction?.let {
            todayPlanTransaction.todayPlanTransactionUpdate(request.dayFigure)
            return todayPlanTransaction
        }

        return newTodayPlanTransaction(request, studyPlan)
    }

    private fun newTodayPlanTransaction(request: PlanTransactionRequest, studyPlan: StudyPlan?): PlanTransaction {
        val planTransaction = PlanTransaction(request, studyPlan)

        return planTransactionRepository.save(planTransaction)
    }
}