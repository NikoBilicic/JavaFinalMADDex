package com.example.maddexjavafinal.tabs;

import com.example.maddexjavafinal.pojo.Type;
import com.example.maddexjavafinal.tables.PokeTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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
        PokeTable pokeTable = new PokeTable();
        typeGraph = new PieChart();
        typeGraph.setTitle("Types");
        typeGraph.setLabelsVisible(true);

        Text title = new Text("Statistics");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(new Font(25));

        Text totalPokemon = new Text("Total Pokemon: " + pokeTable.getCount());
        totalPokemon.setTextAlignment(TextAlignment.CENTER);
        totalPokemon.setFont(new Font(20));

        Text shinyTotal = new Text("Total Shinies: " + pokeTable.getShinyCount());
        shinyTotal.setTextAlignment(TextAlignment.CENTER);
        shinyTotal.setFont(new Font(20));

        Text typeTotalTitle = new Text("Total Types:");
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text normalTotal = new Text("Normal: " + pokeTable.getTypeCount("normal"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text fightingTotal = new Text("Fighting: " + pokeTable.getTypeCount("fighting"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text flyingTotal = new Text("Flying: " + pokeTable.getTypeCount("flying"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text poisonTotal = new Text("Poison: " + pokeTable.getTypeCount("poison"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text groundTotal = new Text("Ground: " + pokeTable.getTypeCount("ground"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text rockTotal = new Text("Rock: " + pokeTable.getTypeCount("rock"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text bugTotal = new Text("Bug: " + pokeTable.getTypeCount("bug"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text ghostTotal = new Text("Ghost: " + pokeTable.getTypeCount("ghost"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text steelTotal = new Text("Steel: " + pokeTable.getTypeCount("steel"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text fireTotal = new Text("Fire: " + pokeTable.getTypeCount("fire"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text waterTotal = new Text("Water: " + pokeTable.getTypeCount("water"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text grassTotal = new Text("Grass: " + pokeTable.getTypeCount("grass"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text electricTotal = new Text("Electric: " + pokeTable.getTypeCount("electric"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text psychicTotal = new Text("Psychic: " + pokeTable.getTypeCount("psychic"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text iceTotal = new Text("Ice: " + pokeTable.getTypeCount("ice"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text dragonTotal = new Text("Dragon: " + pokeTable.getTypeCount("dragon"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text darkTotal = new Text("Dark: " + pokeTable.getTypeCount("dark"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        Text fairyTotal = new Text("Fairy: " + pokeTable.getTypeCount("fairy"));
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        StackPane root = new StackPane();

        ImageView background = new ImageView(new Image("file:src/imgResources/background.png"));
        background.setFitHeight(720);
        background.setFitWidth(1024);

        VBox textContent = new VBox();
        textContent.getChildren().addAll(title, totalPokemon, typeTotalTitle, normalTotal, fightingTotal, flyingTotal, poisonTotal, groundTotal, rockTotal,
                bugTotal, ghostTotal, steelTotal, fireTotal, waterTotal, grassTotal, electricTotal, psychicTotal, iceTotal, dragonTotal, darkTotal, fairyTotal,
                shinyTotal);
        textContent.setAlignment(Pos.CENTER_RIGHT);
        textContent.setSpacing(10);
        textContent.setPadding(new Insets(0, 200, 0, 0));

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
