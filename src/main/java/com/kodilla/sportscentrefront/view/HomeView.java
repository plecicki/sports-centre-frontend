package com.kodilla.sportscentrefront.view;

import com.kodilla.sportscentrefront.backend.connect.client.YouTubeClient;
import com.kodilla.sportscentrefront.backend.connect.domain.MyYouTubeDto;
import com.kodilla.sportscentrefront.backend.connect.domain.TableYouTubeDto;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

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
    public HomeView(YouTubeClient youTubeClient) {
        Image upImage = new Image("images/background.jpg", "Image at the up of website");
        upImage.setWidthFull();
        add(upImage);

        //TODO Fix it
        Label user = new Label("User");
        user.setWidthFull();
        user.setMaxHeight("20");
        add(user);

        MyYouTubeDto[] myYouTubeDto = youTubeClient.getYouTube();
        List<TableYouTubeDto> tableYouTubeDto = getTableYouTube(myYouTubeDto);

        Grid<TableYouTubeDto> gridYT = new Grid<>(TableYouTubeDto.class);
        gridYT.setColumns("publishedAt", "title", "channelTitle", "viewCount", "likeCount");

        gridYT.addComponentColumn(e -> e.getImage()).setHeader("Image");


//        gridYT.addComponentColumn(
//                "imageUrl"
//        ).setHeader("Preview");
//        column.getEditorComponent().getElement().setAttribute(image, new Image(gridYT.getEditor()., "alt text")).
        //gridYT.addComponentColumn(i -> new Image(tableYouTubeDto.get().getImageUrl(), "alt text")).setHeader("Preview");
//        for (final int i=0; i<tableYouTubeDto.size(); i++) {
//            gridYT.addColumn(j -> new Image(tableYouTubeDto.get(i).getImageUrl(), "alt text")).
//                setHeader("Image");
//        }
        gridYT.setItems(tableYouTubeDto);
        add(gridYT);
    }

    private List<TableYouTubeDto> getTableYouTube(MyYouTubeDto[] myYouTubeDto) {

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
