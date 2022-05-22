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
        val studyPlan = studyPlanRepository.findByIdOrNull(request.studyPlanId)

        val todayPlanTransaction = planTransactionRepository.findByStudyPlanAndDay(studyPlan, LocalDate.now())

        todayPlanTransaction?.let {
            it.todayPlanTransactionUpdate(request.dayFigure, studyPlan!!.currentFigure)
            studyPlan.currentFigureCalculate(request.dayFigure)
            return it
        }

        return newTodayPlanTransaction(request, studyPlan)
    }


    private fun newTodayPlanTransaction(request: PlanTransactionRequest, studyPlan: StudyPlan?): PlanTransaction {
        if (studyPlan != null) {
            val dayFigure = request.dayFigure.minus(studyPlan.currentFigure)

            val planTransaction = PlanTransaction.of(dayFigure, studyPlan)

            studyPlan.currentFigureCalculate(request.dayFigure)

            return planTransactionRepository.save(planTransaction)
        } else {
            throw IllegalStateException("존재하지 않는 계획입니다.")
        }
    }
}