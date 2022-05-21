package study.planner.app.plantransaction

import study.planner.app.plantransaction.dto.PlanTransactionRequest
import study.planner.app.studyplan.domain.StudyPlan
import java.time.LocalDate
import javax.persistence.*
import javax.persistence.FetchType.*

@Entity
class PlanTransaction(
        request: PlanTransactionRequest,

        @ManyToOne(fetch = LAZY)
        @JoinColumn(name = "study_plan_id")
        var studyPlan: StudyPlan?,

        var day: LocalDate = LocalDate.now()
) {

    @Id
    @GeneratedValue
    @Column(name = "plan_transaction_id")
    var id: Long? = null

    var dayFigure: Int? = request.dayFigure
}