package study.planner.app.member.dto

import javax.validation.constraints.NotEmpty

data class MemberRequest(

        @field:NotEmpty(message = "이름을 입력해 주세요.")
        var name: String?,

        @field:NotEmpty(message = "이메일을 입력해 주세요.")
        var email: String?,

        @field:NotEmpty(message = "비밀번호를 입력해 주세요.")
        var password: String?,
)
