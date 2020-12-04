package es.jaimedearcos.templates.configuration.rest

import es.jaimedearcos.templates.service.UserServiceImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod.OPTIONS
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**  Filter to allow Cross Origin (CORS)  */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter : Filter {

    private val log: Logger = LoggerFactory.getLogger(UserServiceImpl::class.java)

    override fun init(filterConfig: FilterConfig?) {
        log.info("CORS Filter enabled");
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {

        val request = servletRequest as HttpServletRequest
        val response = servletResponse as HttpServletResponse

        if (request.getHeader("Origin") != null) {
            // CORS allowance
            val origin = request.getHeader(HttpHeaders.ORIGIN)
            response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin)
            response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true")
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.SET_COOKIE)
            if (request.method == OPTIONS.name) {
                // Preflight response in CORS
                response.status = HttpServletResponse.SC_OK
                response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, PATCH, DELETE")
                response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, request.getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS))
                return
            }
        }
        filterChain.doFilter(request, response)
    }

    override fun destroy() {

    }
}
