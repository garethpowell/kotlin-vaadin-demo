package com.example.kotlinvaadin.view

import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Route
import com.vaadin.flow.router.RouterLink


@Route(value = LogoutView.ROUTE)
class LogoutView : VerticalLayout() {

    companion object {
        const val ROUTE = "logout"
    }

    init {

        val goodbye = H1("You have been logged out.")
        add(goodbye)

        add(RouterLink("Login", LoginView::class.java))

    }

}
