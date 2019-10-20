package com.example.kotlinvaadin.app.security

import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import org.springframework.security.web.savedrequest.HttpSessionRequestCache


/**
 * HttpSessionRequestCache that avoids saving internal framework requests.
 */
internal class CustomRequestCache : HttpSessionRequestCache() {

    /**
     * {@inheritDoc}
     *
     * If the method is considered an internal request from the framework, we skip
     * saving it.
     *
     * @see SecurityUtils.isFrameworkInternalRequest
     */
    override fun saveRequest(request: HttpServletRequest, response: HttpServletResponse?) {
        if (!SecurityUtils.isFrameworkInternalRequest(request)) {
            super.saveRequest(request, response)
        }
    }

}