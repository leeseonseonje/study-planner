package study.planner.app.member

import study.planner.app.member.dto.MemberRequest
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Member(request: MemberRequest) {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    var id: Long? = null

    var name: String? = request.name

    var email: String? = request.email
}