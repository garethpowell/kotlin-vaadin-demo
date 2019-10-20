package com.example.kotlinvaadin.view

import com.vaadin.flow.component.Tag
import com.vaadin.flow.component.login.LoginOverlay
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import mu.KotlinLogging


@Tag("sa-login-view")
@Route(value = LoginView.ROUTE)
@PageTitle("Login")
class LoginView : VerticalLayout() {

    private val log = KotlinLogging.logger {}

    private val login = LoginOverlay()

    init {
        login.action = "login"
        login.isOpened = true
        login.setTitle("White Cube")
        login.description = "Welcome to the White Cube"
        element.appendChild(login.element)
    }

    companion object {
        const val ROUTE = "login"
    }
}