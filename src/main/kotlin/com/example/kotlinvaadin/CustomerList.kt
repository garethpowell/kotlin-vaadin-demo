package com.example.kotlinvaadin

import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.grid.GridVariant
import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.router.Route

@Route("customer/list", layout = MainView::class)
class CustomerList :  VerticalLayout() {

    private val grid = Grid<Customer>(Customer::class.java)
    private val filterText = TextField()

    init {
        add(H1("Customers"))

        filterText.placeholder = "Filter by name..."
        filterText.isClearButtonVisible = true
        filterText.valueChangeMode = ValueChangeMode.EAGER
        filterText.addValueChangeListener { updateList() }

        grid.setColumns("forename", "surname", "age")
        grid.addThemeVariants(
                GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS,
                GridVariant.LUMO_ROW_STRIPES
        )

        grid.setItems(DEMO_CUSTOMERS)
        add(filterText, grid)

    }

    private fun updateList() {
        if (filterText.value.isNotEmpty()) {
            val searchText = filterText.value.toUpperCase()
            grid.setItems(DEMO_CUSTOMERS.filter {
                it.forename.toUpperCase().contains(searchText)
                        ||
                        it.surname.toUpperCase().contains(searchText)
            })
        } else {
            grid.setItems(DEMO_CUSTOMERS)
        }
    }

}


data class Customer(
        val forename: String,
        val surname: String,
        val age: Int
)

val DEMO_CUSTOMERS = listOf(
        Customer("Gareth", "Powell", 42),
        Customer("Bob", "Smith", 53),
        Customer("Steve", "Jones", 39),
        Customer("David", "Powell", 40),
        Customer("Sheila", "Hughes", 72),
        Customer("Matthew", "Kitching", 46)
)