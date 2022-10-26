package study.planner.app.plantransaction.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import study.planner.app.QuerydslFactory
import study.planner.app.plantransaction.QPlanTransaction.*
import study.planner.app.plantransaction.dto.PlanTransactionsDto
import study.planner.app.plantransaction.dto.QPlanTransactionsDto
import study.planner.app.studyplan.domain.QStudyPlan
import study.planner.app.studyplan.domain.QStudyPlan.*
import javax.persistence.EntityManager

@Repository
class PlanTransactionRepositoryImpl(
        private val queryFactory: JPAQueryFactory

) : PlanTransactionRepositoryCustom {

    override fun dayFigures(studyPlanId: Long): List<Int>? {
        return queryFactory
                .select(planTransaction.dayFigure)
                .from(planTransaction)
                .where(planTransaction.studyPlan.id.eq(studyPlanId))
                .fetch()
    }

    override fun planTransactions(studyPlanId: Long, pageable: Pageable): List<PlanTransactionsDto> {
        return queryFactory
                .select(QPlanTransactionsDto(
                        planTransaction.dayFigure,
                        planTransaction.day,
                        planTransaction.currentFigure,
                )).from(planTransaction)
                .join(planTransaction.studyPlan, studyPlan)
                .where(planTransaction.studyPlan.id.eq(studyPlanId))
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())
                .orderBy(planTransaction.day.desc())
                .fetch()
    }
}