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
                        val member = Member(MemberRequest("memberA", "leeseonje9323@gmail.com"))
                        em.persist(member)

                        val studyPlan = StudyPlan(StudyPlanRegistrationRequest(member.id, "title", "content", 1000, LocalDate.of(2022, 7, 1)), member)
                        em.persist(studyPlan)
                }
        }
}