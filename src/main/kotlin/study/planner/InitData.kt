package study.planner

import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import study.planner.app.member.Member
import study.planner.app.member.dto.MemberRequest
import study.planner.app.studyplan.domain.StudyPlan
import study.planner.app.studyplan.dto.StudyPlanRegistrationRequest
import java.time.LocalDate
import javax.annotation.PostConstruct
import javax.persistence.EntityManager
import javax.transaction.Transactional

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
            private val em: EntityManager
    ) {

        fun init() {
            val member = Member.of(MemberRequest("선제", "leeseonje9323@gmail.com", "1234"))
            em.persist(member)

            var sum = 100
            for (i in 1..10) {
                sum += 1
                val request = StudyPlanRegistrationRequest(1, "title", "content", 1000, LocalDate.of(2023, 1, i))
                val studyPlan = StudyPlan.of(request, member)
                studyPlan.currentFigure = sum
                em.persist(studyPlan)
            }
            em.flush()
            em.clear()
        }
    }
}