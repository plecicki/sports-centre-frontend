package com.kodilla.sportscentrefront.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Home")
@Route("sport/home")
@CssImport("./styles/views/home/home-view.css")
public class HomeView extends VerticalLayout {

    public HomeView() {
        Image upImage = new Image("images/background.jpg", "Image at the up of website");
        upImage.setWidthFull();
        add(upImage);

        //TODO Fix it
        Label user = new Label("User");
        user.setWidthFull();
        user.setMaxHeight("20");
        add(user);
    }
}
