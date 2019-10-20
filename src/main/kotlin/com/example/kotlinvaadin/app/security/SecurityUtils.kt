package com.example.kotlinvaadin.app.security

import com.vaadin.flow.server.ServletHelper.RequestType
import com.vaadin.flow.shared.ApplicationConstants
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import javax.servlet.http.HttpServletRequest


/**
 * SecurityUtils takes care of all such static operations that have to do with
 * security and querying rights from different beans of the UI.
 *
 */
object SecurityUtils {

    /**
     * Tests if some user is authenticated. As Spring Security always will create an [AnonymousAuthenticationToken]
     * we have to ignore those tokens explicitly.
     */
    internal fun isUserLoggedIn(): Boolean {
        val authentication = SecurityContextHolder.getContext().authentication
        return (authentication != null
                && authentication !is AnonymousAuthenticationToken
                && authentication.isAuthenticated)
    }

    /**
     * Tests if the request is an internal framework request. The test consists of
     * checking if the request parameter is present and if its value is consistent
     * with any of the request types know.
     *
     * @param request
     * [HttpServletRequest]
     * @return true if is an internal framework request. False otherwise.
     */
    internal fun isFrameworkInternalRequest(request: HttpServletRequest): Boolean {
        val parameterValue = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER)
        return parameterValue != null && RequestType.values().any { r -> r.identifier == parameterValue }
    }
}// Util methods only