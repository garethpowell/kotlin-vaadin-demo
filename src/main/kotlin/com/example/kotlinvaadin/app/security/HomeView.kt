package com.example.kotlinvaadin.app.security

import com.example.kotlinvaadin.MainLayout
import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route

@Route(value = HomeView.ROUTE, layout = MainLayout::class)
@PageTitle("Welcome to White Cube")
class HomeView: VerticalLayout() {

    companion object {
        const val ROUTE = "home"
    }

    init {

        val header = H1("Welcome to White Cube")
        add(header)
    }

}