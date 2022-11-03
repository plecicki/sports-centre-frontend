package com.kodilla.sportscentrefront.view;

import com.kodilla.sportscentrefront.backend.connect.client.WeatherClient;
import com.kodilla.sportscentrefront.backend.connect.client.YouTubeClient;
import com.kodilla.sportscentrefront.backend.connect.domain.MyYouTubeDto;
import com.kodilla.sportscentrefront.backend.connect.domain.TableYouTubeDto;
import com.kodilla.sportscentrefront.backend.connect.domain.TomWeatherDto;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@PageTitle("Home")
@Route("sport/home")
@CssImport("./styles/views/home/home-view.css")
public class HomeView extends VerticalLayout {

    @Autowired
    public HomeView(YouTubeClient youTubeClient, WeatherClient weatherClient) {
        setId("");

        Image upImage = new Image("images/background.jpg", "Image at the up of website");
        upImage.setWidthFull();
        add(upImage);

        FormLayout loginForm = setLoginForm();
        add(loginForm);

        //------------------------------------------------------

        Button loginButton = new Button("Click to Log In or Register", event -> {
            UI.getCurrent().navigate("/sport/login");
        });
        loginButton.setWidthFull();
        loginButton.setHeight("100px");
        loginButton.getStyle().set("background-color", "#0066ff");
        loginButton.getStyle().set("font-size", "40px");
        loginButton.getStyle().set("color", "#f2f2ff");

        add(loginButton);


        //--------------------------------------------------

        try {
            FormLayout formLayout = getFormLayout(weatherClient);
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
            List<TableYouTubeDto> tableYouTubeDto = getTableYouTube(myYouTubeDto);

            Label recommends = new Label("Please, look at YouTube movies which we recommends below!");
            recommends.getStyle().set("text-align", "center");
            recommends.getStyle().set("font-weight", "bold");
            recommends.getStyle().set("font-size", "20px");
            recommends.setWidthFull();
            add(recommends);

            Grid<TableYouTubeDto> gridYT = setGridYouTube(tableYouTubeDto);
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

    public static FormLayout setLoginForm() {
        FormLayout loginForm = new FormLayout();

        Label userLabel = new Label("USER");
        userLabel.getStyle().set("text-align", "center");
        userLabel.getStyle().set("font-weight", "bold");

        Label adminLabel = new Label("ADMIN");
        adminLabel.getStyle().set("text-align", "center");
        adminLabel.getStyle().set("font-weight", "bold");

        Label userUser = new Label("Username: user");
        userUser.getStyle().set("text-align", "center");

        Label adminInfo = new Label("Included in CV");
        adminInfo.getStyle().set("text-align", "center");

        Label userPass = new Label("Password: pass");
        userPass.getStyle().set("text-align", "center");

        loginForm.add(userLabel, adminLabel, userUser, adminInfo,
                userPass, new Label());
        loginForm.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 2),
                new FormLayout.ResponsiveStep("500px", 2));
        loginForm.getStyle().set("border", "2px solid #0015e7");
        loginForm.setWidthFull();

        return loginForm;
    }

    public static Grid<TableYouTubeDto> setGridYouTube(List<TableYouTubeDto> tableYouTubeDto) {
        Grid<TableYouTubeDto> gridYT = new Grid<>(TableYouTubeDto.class);
        gridYT.setColumns();

        gridYT.addComponentColumn(item -> {
                    Label label = new Label();
                    label.setText(item.getPublishedAt().toString());
                    label.setWhiteSpace(HasText.WhiteSpace.NORMAL);
                    return label;
                })
                .setHeader("Published At");

        gridYT.addComponentColumn(item -> {
                    Label label = new Label();
                    label.setText(item.getTitle());
                    label.setWhiteSpace(HasText.WhiteSpace.NORMAL);
                    return label;
                })
                .setHeader("Title");

        gridYT.addComponentColumn(item -> {
                    Label label = new Label();
                    label.setText(item.getChannelTitle());
                    label.setWhiteSpace(HasText.WhiteSpace.NORMAL);
                    return label;
                })
                .setHeader("Channel Title");

        gridYT.addComponentColumn(item -> {
                    Label label = new Label();
                    label.setText(item.getViewCount());
                    label.setWhiteSpace(HasText.WhiteSpace.NORMAL);
                    return label;
                })
                .setHeader("View Count");

        gridYT.addComponentColumn(item -> {
                    Label label = new Label();
                    label.setText(item.getLikeCount());
                    label.setWhiteSpace(HasText.WhiteSpace.NORMAL);
                    return label;
                })
                .setHeader("Like Count");

        gridYT.addComponentColumn(e -> e.getImage()).setHeader("Image");

        gridYT.addItemClickListener(e -> {
            Runtime rt = Runtime.getRuntime();
            String url = e.getItem().getVideoUrl();
            try {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException exception) {

            }
        });
        gridYT.setItems(tableYouTubeDto);

        return gridYT;
    }

    public static FormLayout getFormLayout(WeatherClient weatherClient) throws Exception {
        TomWeatherDto weather = weatherClient.getWeather();
        Label headingWeather = new Label("TOMORROW WEATHER");
        headingWeather.getStyle().set("text-align", "center");
        headingWeather.getStyle().set("font-size", "28px");
        headingWeather.getStyle().set("font-weight", "bold");
        Label address = new Label("Place: " + weather.getResolvedAddress());
        address.getStyle().set("text-align", "center");
        Label date = new Label("Date: " + weather.getDatetime().toString());
        date.getStyle().set("text-align", "center");
        Label temp = new Label("Temperature: " + weather.getTemp().toString());
        temp.getStyle().set("text-align", "center");
        Label humidity = new Label("Humidity: " + weather.getHumidity().toString());
        humidity.getStyle().set("text-align", "center");
        Label sunrise = new Label("Sunrise: " + weather.getSunrise().toString());
        sunrise.getStyle().set("text-align", "center");
        Label sunset = new Label("Sunset: " + weather.getSunset().toString());
        sunset.getStyle().set("text-align", "center");
        Label icon = new Label("Generally weather: " + weather.getIcon());
        icon.getStyle().set("text-align", "center");
        Label description = new Label("Description: " + weather.getDescription());
        description.getStyle().set("text-align", "center");

        FormLayout formLayout = new FormLayout();
        formLayout.add(headingWeather, address, date, temp, sunrise, humidity, sunset, icon, description);

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 2));
        formLayout.setColspan(headingWeather, 2);
        formLayout.setColspan(address, 2);
        formLayout.setColspan(date, 2);
        formLayout.setColspan(icon, 2);
        formLayout.setColspan(description, 2);
        formLayout.getStyle().set("border", "2px solid green");
        formLayout.setWidthFull();
        //TODO Handle errors
        return formLayout;
    }

    public static List<TableYouTubeDto> getTableYouTube(MyYouTubeDto[] myYouTubeDto) {

        List<String> stringImageUrl = Arrays.stream(myYouTubeDto)
                .map(obj -> obj.getImageUrl().toString())
                .collect(Collectors.toList());
        List<String> stringVideoUrl = Arrays.stream(myYouTubeDto)
                .map(obj -> obj.getVideoUrl().toString())
                .collect(Collectors.toList());
        List<LocalDate> stringDate = Arrays.stream(myYouTubeDto)
                .map(obj -> obj.getPublishedAt().toLocalDate())
                .collect(Collectors.toList());

        List<TableYouTubeDto> tableYouTubeDtos = new ArrayList<>();
        int i = 0;
        for (MyYouTubeDto youTubeDto: myYouTubeDto) {
            TableYouTubeDto tableIterate = new TableYouTubeDto(
                    stringDate.get(i),
                    youTubeDto.getTitle(),
                    youTubeDto.getChannelTitle(),
                    new Image(stringImageUrl.get(i), "Load image error"),
                    youTubeDto.getViewCount(),
                    youTubeDto.getLikeCount(),
                    stringVideoUrl.get(i)
            );
            tableYouTubeDtos.add(tableIterate);
            i++;
        }
        return tableYouTubeDtos;
    }
}
