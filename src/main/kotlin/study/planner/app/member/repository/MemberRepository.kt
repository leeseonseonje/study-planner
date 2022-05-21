package study.planner.app.member.repository

import org.springframework.data.jpa.repository.JpaRepository
import study.planner.app.member.Member
import java.util.*

interface MemberRepository : JpaRepository<Member, Long> {

    fun existsByEmail(email: String?): Boolean
}