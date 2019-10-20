package com.example.kotlinvaadin

import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.applayout.DrawerToggle
import com.vaadin.flow.component.html.Image
import com.vaadin.flow.component.page.Viewport
import com.vaadin.flow.component.tabs.Tab
import com.vaadin.flow.component.tabs.Tabs
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.PWA
import com.vaadin.flow.theme.Theme
import com.vaadin.flow.theme.material.Material

@Theme(Material::class)
@Route
@Viewport("width=device-width, minimum-scale=1, inital-scale=1, user-scalable=yes, viewport-fit=cover")
@PWA(name = "Demo App", shortName = "Demo")
class MainView : AppLayout() {

    init {

        val image = Image("https://i.imgur.com/GPpnzs.png", "Vaadin Logo")
        image.height = "44px"
        addToNavbar(DrawerToggle(), image)
        val tabs = Tabs(Tab("Home"), Tab("About"))
        tabs.orientation = Tabs.Orientation.VERTICAL
        addToDrawer(tabs)

        
    }

}