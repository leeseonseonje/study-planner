package study.planner.app.web

import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class LoginCheckInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        val session = request.getSession(false)

        if (session == null || session.getAttribute("member") == null) {
            response.sendRedirect("?redirectURL=" + request.requestURI)
            return false
        }
        return true
    }
}