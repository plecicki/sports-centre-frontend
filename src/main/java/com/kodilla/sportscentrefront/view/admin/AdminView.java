package com.kodilla.sportscentrefront.view.admin;

import com.kodilla.sportscentrefront.backend.connect.client.WeatherClient;
import com.kodilla.sportscentrefront.backend.connect.client.YouTubeClient;
import com.kodilla.sportscentrefront.backend.connect.domain.MyYouTubeDto;
import com.kodilla.sportscentrefront.backend.connect.domain.TableYouTubeDto;
import com.kodilla.sportscentrefront.view.HomeView;
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

        try {
            FormLayout formLayout = HomeView.getFormLayout(weatherClient);
            add(formLayout);
        } catch (Exception e) {
            Label weatherError = new Label("Weather API error!");
            weatherError.getStyle().set("text-align", "center");
            weatherError.getStyle().set("font-weight", "bold");
            weatherError.getStyle().set("font-size", "20px");
            weatherError.setWidthFull();
            add(weatherError);
        }

        //--------------------------------------------------------

        try {
            MyYouTubeDto[] myYouTubeDto = youTubeClient.getYouTube();
            List<TableYouTubeDto> tableYouTubeDto = HomeView.getTableYouTube(myYouTubeDto);

            Label recommends = new Label("Please, look at YouTube movies which we recommends below!");
            recommends.getStyle().set("text-align", "center");
            recommends.getStyle().set("font-weight", "bold");
            recommends.getStyle().set("font-size", "20px");
            recommends.setWidthFull();
            add(recommends);

            Grid<TableYouTubeDto> gridYT = HomeView.setGridYouTube(tableYouTubeDto);
            add(gridYT);
        } catch (Exception e) {
            Label yTError = new Label("YouTube API error!");
            yTError.getStyle().set("text-align", "center");
            yTError.getStyle().set("font-weight", "bold");
            yTError.getStyle().set("font-size", "20px");
            yTError.setWidthFull();
            add(yTError);
        }
    }
}
