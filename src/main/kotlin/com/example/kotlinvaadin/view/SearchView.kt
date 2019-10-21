package com.example.kotlinvaadin.view

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route

@Route(value = SearchView.ROUTE, layout = MainLayout::class)
class SearchView : VerticalLayout() {

    companion object {
        const val ROUTE = "search"
    }

    init {

        val searchText = TextField()
        searchText.isClearButtonVisible = true
        searchText.placeholder = "What are you looking for?"


        val searchBtn = Button("Go")

        val horizontalLayout = HorizontalLayout()

        horizontalLayout.add(searchText, searchBtn)

        add(horizontalLayout)
    }

}