package study.planner

import org.springframework.stereotype.Component
import study.planner.app.member.Member
import study.planner.app.member.controller.dto.MemberRequest
import study.planner.app.plantransaction.PlanTransaction
import study.planner.app.plantransaction.controller.dto.PlanTransactionRequest
import study.planner.app.plantransaction.service.PlanTransactionService
import study.planner.app.studyplan.domain.StudyPlan
import study.planner.app.studyplan.controller.dto.StudyPlanRegistrationRequest
import study.planner.app.studyplan.service.StudyPlanService
import java.time.LocalDate
import javax.annotation.PostConstruct
import javax.persistence.EntityManager
import javax.transaction.Transactional
import kotlin.random.Random

@Component
class InitData(
        private val initService: InitService
) {

    @PostConstruct
    fun init() {
        initService.init()
    }

    @Component
    @Transactional
    class InitService(
            private val em: EntityManager,
            private val planTransactionService: PlanTransactionService,
    ) {

        fun init() {
            val member = Member.of(MemberRequest("선제", "leeseonje9323@gmail.com", "1234"))
            em.persist(member)

            var sum = 100
            for (i in 1..10) {
                sum += 1
                val request = StudyPlanRegistrationRequest(1, "title", "content", 1000, LocalDate.of(9999, 12, 31))
                val studyPlan = StudyPlan.of(request, member, LocalDate.of(2022, 10, 1))
                if (i % 2 == 0) {
                    println(i % 2)
                    studyPlan.currentFigureCalculate(1000)
                } else {
                    studyPlan.currentFigure = 0
                }
//                val planTransaction = PlanTransaction.of(sum, studyPlan, LocalDate.now())
                em.persist(studyPlan)
//                em.persist(planTransaction)
                if (studyPlan.id?.toInt() == 2) {
                    var num = 100
                    for (i in 1..31) {
                        val re = PlanTransactionRequest(studyPlan.id!!, num, LocalDate.of(2022, 10, i))
                        val todayStudy = planTransactionService.todayStudy(re)
                        val random = java.util.Random()
                        val r = random.nextInt(20) + 1
                        num += r
                    }
                }
            }
            em.flush()
            em.clear()

        }
    }
}