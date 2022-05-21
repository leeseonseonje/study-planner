package study.planner.app.plantransaction.repository

import org.springframework.data.jpa.repository.JpaRepository
import study.planner.app.plantransaction.PlanTransaction

interface PlanTransactionRepository : JpaRepository<PlanTransaction, Long> {
}