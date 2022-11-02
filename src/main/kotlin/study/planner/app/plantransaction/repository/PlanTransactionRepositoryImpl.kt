package study.planner.app.plantransaction.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils
import study.planner.app.plantransaction.QPlanTransaction.*
import study.planner.app.plantransaction.repository.dto.PlanTransactionsDto
import study.planner.app.plantransaction.dto.QPlanTransactionsDto
import study.planner.app.studyplan.domain.QStudyPlan.*

class PlanTransactionRepositoryImpl (
        private val queryFactory: JPAQueryFactory

) : PlanTransactionRepositoryCustom {

    override fun dayFigures(studyPlanId: Long): List<Int>? {
        return queryFactory
                .select(planTransaction.dayFigure)
                .from(planTransaction)
                .where(planTransaction.studyPlan.id.eq(studyPlanId))
                .fetch()
    }

    override fun planTransactions(studyPlanId: Long, pageable: Pageable): Page<PlanTransactionsDto> {
        val result = queryFactory
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

        val countQuery = queryFactory
                .select(planTransaction.count())
                .from(planTransaction)
                .where(planTransaction.studyPlan.id.eq(studyPlanId))

        return PageableExecutionUtils.getPage(result, pageable) { countQuery.fetchOne()!! }
    }
}