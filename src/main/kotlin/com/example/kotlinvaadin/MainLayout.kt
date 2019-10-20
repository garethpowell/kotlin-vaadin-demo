package com.example.kotlinvaadin

import com.example.kotlinvaadin.view.LogoutView
import com.example.kotlinvaadin.view.SearchView
import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.applayout.DrawerToggle
import com.vaadin.flow.component.html.H3
import com.vaadin.flow.component.page.Viewport
import com.vaadin.flow.component.tabs.Tab
import com.vaadin.flow.component.tabs.Tabs
import com.vaadin.flow.router.RouterLink
import com.vaadin.flow.server.PWA
import com.vaadin.flow.theme.Theme
import com.vaadin.flow.theme.material.Material

@Theme(Material::class)
@Viewport("width=device-width, minimum-scale=1, inital-scale=1, user-scalable=yes, viewport-fit=cover")
@PWA(name = "Demo App", shortName = "Demo")
class MainLayout : AppLayout() {

    init {

        addToNavbar(true, DrawerToggle(), H3("White Cube"))

//        val logoutBtn = RouterLink("Logout", LogoutView::class.java)
//        val logoutTab = Tab().apply { add(RouterLink("Logout", LogoutView::class.java)) }

        val tabs = Tabs(
                Tab("Home"),
                Tab().apply { add(RouterLink("Search", SearchView::class.java)) },
                Tab("About"),
                Tab().apply { add(RouterLink("Logout", LogoutView::class.java)) }
        )

        tabs.orientation = Tabs.Orientation.VERTICAL

        addToDrawer(tabs)

    }

}