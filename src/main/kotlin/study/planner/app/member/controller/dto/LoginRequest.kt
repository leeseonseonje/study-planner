package study.planner.app.member.controller.dto

import javax.validation.constraints.NotEmpty

data class LoginRequest(

        @field:NotEmpty(message = "이메일을 입력해 주세요.")
        var email: String?,

        @field:NotEmpty(message = "비밀번호를 입력해 주세요.")
        var password: String?
)
