package com.kodilla.sportscentrefront.view;

import com.kodilla.sportscentrefront.backend.connect.client.WeatherClient;
import com.kodilla.sportscentrefront.backend.connect.client.YouTubeClient;
import com.kodilla.sportscentrefront.backend.connect.domain.MyYouTubeDto;
import com.kodilla.sportscentrefront.backend.connect.domain.TableYouTubeDto;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageTitle("Admin")
public class AdminView extends VerticalLayout {

    @Autowired
    public AdminView(WeatherClient weatherClient, YouTubeClient youTubeClient) {
        Image upImage = new Image("images/background.jpg", "Image at the up of website");
        upImage.setWidthFull();
        add(upImage);

        FormLayout formLayout = HomeView.getFormLayout(weatherClient);
        add(formLayout);

        //--------------------------------------------------------

        Label recommends = new Label("Please, look at YouTube movies which we recommends below!");
        recommends.getStyle().set("text-align", "center");
        recommends.getStyle().set("font-weight", "bold");
        recommends.getStyle().set("font-size", "20px");
        recommends.setWidthFull();
        add(recommends);

        //--------------------------------------------------------
        MyYouTubeDto[] myYouTubeDto = youTubeClient.getYouTube();
        List<TableYouTubeDto> tableYouTubeDto = HomeView.getTableYouTube(myYouTubeDto);


        Grid<TableYouTubeDto> gridYT = HomeView.setGridYouTube(tableYouTubeDto);
        add(gridYT);
    }
}
