package com.example.kotlinvaadin.view

import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.applayout.DrawerToggle
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.component.html.Anchor
import com.vaadin.flow.component.html.H3
import com.vaadin.flow.component.html.H4
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


        val logoutDialog = Dialog().apply {
            isCloseOnEsc = true
            isCloseOnOutsideClick = false
        }

        logoutDialog.add(
                H4("Do you want to logout?"),
                Anchor("/perform_logout", "Confirm"),
                Button("Cancel") { logoutDialog.close() }
        )

        val logoutBtn = Button("Logout")
        logoutBtn.addClickListener { logoutDialog.open() }


        addToNavbar(true, DrawerToggle(), H3("White Cube"))

        addToDrawer(
                Tabs(
                        Tab().apply {
                            add(
                                    RouterLink("Home", HomeView::class.java)
                            )
                        },
                        Tab().apply {
                            add(
                                    RouterLink("Search", SearchView::class.java)
                            )
                        },
                        Tab().apply {
                            add(
                                    RouterLink("About", AboutView::class.java)
                            )
                        },
                        Tab().apply {
                            add(
                                    logoutBtn
                            )
                        }
                ).apply {
                    orientation = Tabs.Orientation.VERTICAL
                }
        )

    }

}