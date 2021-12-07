package com.example.maddexjavafinal.tabs;

import com.example.maddexjavafinal.pojo.Type;
import com.example.maddexjavafinal.tables.PokeTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

import static com.example.maddexjavafinal.database.DBTableVals.typing;

public class AddStatsTab extends Tab {

    private static  AddStatsTab tab;
    public PieChart typeGraph;

    private AddStatsTab() {
        this.setText("Statistics");
        typeGraph = new PieChart();
        typeGraph.setTitle("Types");
        typeGraph.setLabelsVisible(true);

        Text title = new Text("Statistics");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(new Font(25));

        StackPane root = new StackPane();

        ImageView background = new ImageView(new Image("file:src/imgResources/background.png"));
        background.setFitHeight(720);
        background.setFitWidth(1024);

        VBox textContent = new VBox();
        textContent.getChildren().addAll(title);
        textContent.setSpacing(15);

        HBox content = new HBox();
        content.getChildren().addAll(typeGraph);
        content.setSpacing(25);
        genTypeGraph();

        root.getChildren().addAll(background, content, textContent);

        this.setContent(root);
    }

    public void genTypeGraph() {
        PokeTable pokeTable = new PokeTable();

        String[] types = typing;
        ArrayList<PieChart.Data> typingInfo = new ArrayList<>();

        for (String type: types) {
            double amount = pokeTable.getTypeCount(type);
            if (amount > 0) {
                typingInfo.add(new PieChart.Data(type, amount));
            }

        }
        ObservableList<PieChart.Data> typeInfo = FXCollections.observableArrayList(typingInfo);
        typeGraph.setData(typeInfo);
    }

    public static AddStatsTab getInstance() {
        if (tab == null) {
            tab = new AddStatsTab();
        }
        return tab;
    }
}
