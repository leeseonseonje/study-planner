package study.planner.app.plantransaction.repository

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.transaction.annotation.Transactional
import study.planner.app.member.repository.MemberRepository

@SpringBootTest
@Transactional
internal class PlanTransactionRepositoryImplTest(
        @Autowired
        val planTransactionRepository: PlanTransactionRepository
) {

    @Test
    fun test() {
        val planTransactions = planTransactionRepository.planTransactions(2L, PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id")))
        println(planTransactions.totalPages)
        println(planTransactions.totalElements)
    }
}