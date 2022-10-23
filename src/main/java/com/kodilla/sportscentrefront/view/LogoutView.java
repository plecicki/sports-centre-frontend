package com.kodilla.sportscentrefront.view;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("sport/logout")
@PageTitle("Logout")
public class LogoutView extends Composite<VerticalLayout> {

    public LogoutView() {
        UI.getCurrent().getPage().setLocation("sport/home");
        VaadinSession.getCurrent().getSession().invalidate();
        VaadinSession.getCurrent().close();
    }
}
