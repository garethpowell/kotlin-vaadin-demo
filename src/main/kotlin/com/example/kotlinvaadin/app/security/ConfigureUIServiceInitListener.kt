package com.example.kotlinvaadin.app.security

import com.example.kotlinvaadin.view.LoginView
import com.example.kotlinvaadin.app.security.SecurityUtils.isUserLoggedIn
import com.vaadin.flow.router.BeforeEnterEvent
import com.vaadin.flow.server.ServiceInitEvent
import com.vaadin.flow.server.VaadinServiceInitListener
import org.springframework.stereotype.Component


@Component
class ConfigureUIServiceInitListener : VaadinServiceInitListener {

    override fun serviceInit(event: ServiceInitEvent) {
        event.source.addUIInitListener { uiEvent ->
            val ui = uiEvent.ui
            ui.addBeforeEnterListener { this.beforeEnter(it) }
        }
    }

    /**
     * Reroutes the user if (s)he is not authorized to access the view.
     *
     * @param event
     * before navigation event with event details
     */
    private fun beforeEnter(event: BeforeEnterEvent) {
        if (LoginView::class.java != event.navigationTarget && !isUserLoggedIn()) {
            event.rerouteTo(LoginView::class.java)
        }
    }
}