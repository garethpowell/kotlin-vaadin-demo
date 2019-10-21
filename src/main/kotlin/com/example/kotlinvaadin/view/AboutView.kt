package com.example.kotlinvaadin.view

import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route

@Route(value = AboutView.ROUTE, layout = MainLayout::class)
@PageTitle("Welcome to White Cube")
class AboutView: VerticalLayout() {

    companion object {
        const val ROUTE = "about"
    }

    init {

        val header = H1("About")
        add(header)
    }

}