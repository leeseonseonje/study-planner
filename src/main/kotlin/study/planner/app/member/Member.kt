package study.planner.app.member

import study.planner.app.member.dto.MemberRequest
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    var id: Long? = null

    var name: String?

    var email: String?

    private constructor(name: String?, email: String?) {
        this.name = name
        this.email = email
    }

    companion object {
        fun of(request: MemberRequest): Member {
            return Member(request.name, request.email)
        }
    }
}