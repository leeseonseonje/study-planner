package study.planner.app.studyplan.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import javax.persistence.EntityManager

class StudyPlanRepositoryImpl (

        private val em: EntityManager,
        private val queryFactory: JPAQueryFactory = JPAQueryFactory(em)

) : StudyPlanRepositoryCustom {


}
