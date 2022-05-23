package study.planner.app.plantransaction.repository

import com.querydsl.core.types.Order.*
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.PathBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import study.planner.app.plantransaction.PlanTransaction
import study.planner.app.plantransaction.QPlanTransaction.*
import study.planner.app.plantransaction.dto.PlanTransactionsDto
import study.planner.app.plantransaction.dto.QPlanTransactionsDto
import javax.persistence.EntityManager


class PlanTransactionRepositoryImpl(

        private val em: EntityManager,
        private val queryFactory: JPAQueryFactory = JPAQueryFactory(em)

) : PlanTransactionRepositoryCustom {

    override fun planAvg(studyPlanId: Long): Double {
        return queryFactory
                .select(planTransaction.dayFigure.avg())
                .from(planTransaction)
                .where(planTransaction.studyPlan.id.eq(studyPlanId))
                .fetchFirst()
    }

    override fun planTransactions(studyPlanId: Long, pageable: Pageable): List<PlanTransactionsDto> {
        val query = queryFactory
                .select(QPlanTransactionsDto(
                        planTransaction.dayFigure,
                        planTransaction.day
                )).from(planTransaction)
                .where(planTransaction.studyPlan.id.eq(studyPlanId))
                .offset(pageable.offset)
                .limit(pageable.pageSize.toLong())
                .orderBy(planTransaction.day.desc())

        TODO("Not yet implemented")
    }
}