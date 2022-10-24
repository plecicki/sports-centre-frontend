package com.kodilla.sportscentrefront.view;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;

@PageTitle("Admin")
public class AdminView extends VerticalLayout {

    public AdminView() {
        Image upImage = new Image("images/background.jpg", "Image at the up of website");
        upImage.setWidthFull();
        add(upImage);


    }
}
