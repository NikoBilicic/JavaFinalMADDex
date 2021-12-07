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

    //set to public so content is accessible application wide
    private static  AddStatsTab tab;
    public PieChart typeGraph;
    public static Text totalPokemon;
    public static Text shinyTotal;
    public static Text normalTotal;
    public static Text fightingTotal;
    public static Text flyingTotal;
    public static Text poisonTotal;
    public static Text groundTotal;
    public static Text rockTotal;
    public static Text bugTotal;
    public static Text ghostTotal;
    public static Text steelTotal;
    public static Text fireTotal;
    public static Text waterTotal;
    public static Text grassTotal;
    public static Text electricTotal;
    public static Text psychicTotal;
    public static Text iceTotal;
    public static Text dragonTotal;
    public static Text darkTotal;
    public static Text fairyTotal;



    private AddStatsTab() {
        this.setText("Statistics");
        //call pokeTable and pokeTable functions into view
        PokeTable pokeTable = new PokeTable();
        //initialize stats pie-chart
        typeGraph = new PieChart();
        typeGraph.setTitle("Types");
        typeGraph.setLabelsVisible(true);

        //title for stats tab
        Text title = new Text("Statistics");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(new Font(25));

        //text to display the amount of pokemon in database
        totalPokemon = new Text("Total Pokemon: " + pokeTable.getCount());
        totalPokemon.setTextAlignment(TextAlignment.CENTER);
        totalPokemon.setFont(new Font(20));

        //text to display the amount of shiny pokemon in database
        shinyTotal = new Text("Total Shinies: " + pokeTable.getShinyCount());
        shinyTotal.setTextAlignment(TextAlignment.CENTER);
        shinyTotal.setFont(new Font(20));

        //text to display the amount of pokemon by type in database
        Text typeTotalTitle = new Text("Total Types:");
        typeTotalTitle.setTextAlignment(TextAlignment.CENTER);
        typeTotalTitle.setFont(new Font(20));

        normalTotal = new Text("Normal: " + pokeTable.getTypeCount("normal"));
        normalTotal.setTextAlignment(TextAlignment.CENTER);
        normalTotal.setFont(new Font(15));

        fightingTotal = new Text("Fighting: " + pokeTable.getTypeCount("fighting"));
        fightingTotal.setTextAlignment(TextAlignment.CENTER);
        fightingTotal.setFont(new Font(15));

        flyingTotal = new Text("Flying: " + pokeTable.getTypeCount("flying"));
        flyingTotal.setTextAlignment(TextAlignment.CENTER);
        flyingTotal.setFont(new Font(15));

        poisonTotal = new Text("Poison: " + pokeTable.getTypeCount("poison"));
        poisonTotal.setTextAlignment(TextAlignment.CENTER);
        poisonTotal.setFont(new Font(15));

        groundTotal = new Text("Ground: " + pokeTable.getTypeCount("ground"));
        groundTotal.setTextAlignment(TextAlignment.CENTER);
        groundTotal.setFont(new Font(15));

        rockTotal = new Text("Rock: " + pokeTable.getTypeCount("rock"));
        rockTotal.setTextAlignment(TextAlignment.CENTER);
        rockTotal.setFont(new Font(15));

        bugTotal = new Text("Bug: " + pokeTable.getTypeCount("bug"));
        bugTotal.setTextAlignment(TextAlignment.CENTER);
        bugTotal.setFont(new Font(15));

        ghostTotal = new Text("Ghost: " + pokeTable.getTypeCount("ghost"));
        ghostTotal.setTextAlignment(TextAlignment.CENTER);
        ghostTotal.setFont(new Font(15));

        steelTotal = new Text("Steel: " + pokeTable.getTypeCount("steel"));
        steelTotal.setTextAlignment(TextAlignment.CENTER);
        steelTotal.setFont(new Font(15));

        fireTotal = new Text("Fire: " + pokeTable.getTypeCount("fire"));
        fireTotal.setTextAlignment(TextAlignment.CENTER);
        fireTotal.setFont(new Font(15));

        waterTotal = new Text("Water: " + pokeTable.getTypeCount("water"));
        waterTotal.setTextAlignment(TextAlignment.CENTER);
        waterTotal.setFont(new Font(15));

        grassTotal = new Text("Grass: " + pokeTable.getTypeCount("grass"));
        grassTotal.setTextAlignment(TextAlignment.CENTER);
        grassTotal.setFont(new Font(15));

        electricTotal = new Text("Electric: " + pokeTable.getTypeCount("electric"));
        electricTotal.setTextAlignment(TextAlignment.CENTER);
        electricTotal.setFont(new Font(15));

        psychicTotal = new Text("Psychic: " + pokeTable.getTypeCount("psychic"));
        psychicTotal.setTextAlignment(TextAlignment.CENTER);
        psychicTotal.setFont(new Font(15));

        iceTotal = new Text("Ice: " + pokeTable.getTypeCount("ice"));
        iceTotal.setTextAlignment(TextAlignment.CENTER);
        iceTotal.setFont(new Font(15));

        dragonTotal = new Text("Dragon: " + pokeTable.getTypeCount("dragon"));
        dragonTotal.setTextAlignment(TextAlignment.CENTER);
        dragonTotal.setFont(new Font(15));

        darkTotal = new Text("Dark: " + pokeTable.getTypeCount("dark"));
        darkTotal.setTextAlignment(TextAlignment.CENTER);
        darkTotal.setFont(new Font(15));

        fairyTotal = new Text("Fairy: " + pokeTable.getTypeCount("fairy"));
        fairyTotal.setTextAlignment(TextAlignment.CENTER);
        fairyTotal.setFont(new Font(15));

        //create tab stackpane
        StackPane root = new StackPane();

        //setup pane background
        ImageView background = new ImageView(new Image("file:src/imgResources/background.png"));
        background.setFitHeight(720);
        background.setFitWidth(1024);

        //setup all text content in pane
        VBox textContent = new VBox();
        textContent.getChildren().addAll(title, totalPokemon, typeTotalTitle, normalTotal, fightingTotal, flyingTotal, poisonTotal, groundTotal, rockTotal,
                bugTotal, ghostTotal, steelTotal, fireTotal, waterTotal, grassTotal, electricTotal, psychicTotal, iceTotal, dragonTotal, darkTotal, fairyTotal,
                shinyTotal);
        textContent.setAlignment(Pos.CENTER_RIGHT);
        textContent.setSpacing(10);
        textContent.setPadding(new Insets(0, 200, 0, 0));

        //setup graph in pane
        HBox content = new HBox();
        content.getChildren().addAll(typeGraph);
        content.setSpacing(25);

        //assign all content to pane
        root.getChildren().addAll(background, content, textContent);

        genTypeGraph();
        this.setContent(root);
    }

    //function to generate the pie chart with pokeTable functions
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

    //function to create tab
    public static AddStatsTab getInstance() {
        if (tab == null) {
            tab = new AddStatsTab();
        }
        return tab;
    }
}
