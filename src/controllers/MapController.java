package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import db.ConUtil;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MapController implements Initializable {

    @FXML
    private TextArea description;
    @FXML
    private TextArea state;
    @FXML
    private Pane bg1;
    @FXML
    private Pane bg2;
    @FXML
    private Pane bg3;
    @FXML
    private Pane bg4;
    @FXML
    private Pane bg5;
    @FXML
    private Pane bg6;
    @FXML
    private Pane bg7;
    @FXML
    private Pane bg8;
    @FXML
    private Pane bg9;
    @FXML
    private Pane bg10;

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    void england(MouseEvent event) throws SQLException {
        state.setText("England");
        description.setText("London - No superlative is too great when it comes to describing London, the capital of England and the\n"
                + "UK. This bustling city is history personified from Westminster Abbey to Buckingham Palace – be sure to\n"
                + "watch the Changing of the Guards. Shopping, from Knightsbridge to Carnaby Street, is a must\n"
                + "Fossil hunters may want to make a beeline for the Jurassic Coast, a section in southern England that runs\n"
                + "roughly from Bournemouth to Exmouth. The rocks date back 185 million years to when the continents\n"
                + "were crunching up against each other and then drifting apart.");
        bg1.setVisible(true);
        bg2.setVisible(false);
        bg3.setVisible(false);
        bg4.setVisible(false);
        bg5.setVisible(false);
        bg6.setVisible(false);
        bg7.setVisible(false);
        bg8.setVisible(false);
        bg9.setVisible(false);
        bg10.setVisible(false);

    }

    @FXML
    void finland(MouseEvent event) {
        state.setText("Finland");
        description.setText("With so much unspoiled landscape, Finland is a nature lover’s dream destination. As the country’s\n"
                + "capital , Helsinki is the most popular place to visit. While there are dozens of excellent museums and\n"
                + "galleries in Helsinki, one of the best to see is the National Museum of Finland, which does a great job of\n"
                + "tracing Finnish history over time. Also if you want to experience the beauty of Lapland, then Rovaniemi\n"
                + "is the ultimate gateway to it all. Visitors can tour Santa Claus Village, get stamps from the Santa Claus\n"
                + "Post Office and even visit the Santa-themed underground amusement park. Moreover they can visit the\n"
                + "Korundi House of Culture, the Pilke Science and the Jätkänkynttilä Bridge.");
        bg1.setVisible(false);
        bg2.setVisible(true);
        bg3.setVisible(false);
        bg4.setVisible(false);
        bg5.setVisible(false);
        bg6.setVisible(false);
        bg7.setVisible(false);
        bg8.setVisible(false);
        bg9.setVisible(false);
        bg10.setVisible(false);

    }

    @FXML
    void france(MouseEvent event) {
        state.setText("France");
        description.setText("Paris known as the City of Love and Capital Of Fashion is the most popular tourist destination. the city is\n"
                + "well known for its iconic landmarks like the Eiffel Tower, Arc de Triomphe, Versailles Palace, Sacre-Coeur\n"
                + "and Notre Dame Cathedral. Paris is also home to some of the world’s finest museums that include the\n"
                + "Louvre Museum and Musee d’Orsay.");
        bg1.setVisible(false);
        bg2.setVisible(false);
        bg3.setVisible(true);
        bg4.setVisible(false);
        bg5.setVisible(false);
        bg6.setVisible(false);
        bg7.setVisible(false);
        bg8.setVisible(false);
        bg9.setVisible(false);
        bg10.setVisible(false);
    }

    @FXML
    void germany(MouseEvent event) {
        state.setText("Germany");
        description.setText("Berlin\n"
                + "Berlin is one of the most interesting cities in Europe, and certainly the one that has changed the most in\n"
                + "the last 20 years. Once the symbol of the Cold War, it has become Europe’s capital of cool – a vibrant\n"
                + "mix of fashion, design, music, and art.\n"
                + "The Romantic Road, Germany’s most scenic drive, the Romantic Road, offers visitors the opportunity to\n"
                + "discover the rich culture, picturesque scenery, and medieval character of this fantastic country.\n"
                + "Hamburg also has a great view and everyone should probably visit it.");
        bg1.setVisible(false);
        bg2.setVisible(false);
        bg3.setVisible(false);
        bg4.setVisible(true);
        bg5.setVisible(false);
        bg6.setVisible(false);
        bg7.setVisible(false);
        bg8.setVisible(false);
        bg9.setVisible(false);
        bg10.setVisible(false);
    }

    @FXML
    void hungary(MouseEvent event) {
        state.setText("Hungary");
        description.setText("Budapest-When you first set eyes on spectacular Buda Castle (Budavári Palota) in Budapest, you will\n"
                + "appreciate why so many people consider the city the 'Paris of the East' This spectacular historic\n"
                + "landmark-now a UNESCO World Heritage Site-ranks right up there with Versailles in terms of its majestic\n"
                + "proportions and wonderful design.\n"
                + "The beautiful Danube River flows through Hungary from north to south, and as it passes through\n"
                + "Budapest, it splits the city in two. One of the best sunset views of the river and of both Buda and Pest is\n"
                + "from the Freedom Bridge, a favorite spot for locals.");
        bg1.setVisible(false);
        bg2.setVisible(false);
        bg3.setVisible(false);
        bg4.setVisible(false);
        bg5.setVisible(true);
        bg6.setVisible(false);
        bg7.setVisible(false);
        bg8.setVisible(false);
        bg9.setVisible(false);
        bg10.setVisible(false);
    }

    @FXML
    void iceland(MouseEvent event) {
        state.setText("Iceland");
        description.setText("Illuminated by the Northern Lights in the winter and the midnight sun in the summer, Iceland shines in\n"
                + "any season, offering you a travel experience unlike anywhere else on the planet.The Blue Lagoon is\n"
                + "Iceland’s most popular tourist destination. This manmade lake is fed by superheated seawater vented\n"
                + "from a nearby lava flow. There are also a lot of attractive places such as Golden Circle Route ,\n"
                + "Vatnajokull National Park and Reykjavik.");
        bg1.setVisible(false);
        bg2.setVisible(false);
        bg3.setVisible(false);
        bg4.setVisible(false);
        bg5.setVisible(false);
        bg6.setVisible(true);
        bg7.setVisible(false);
        bg8.setVisible(false);
        bg9.setVisible(false);
        bg10.setVisible(false);
    }

    @FXML
    void italy(MouseEvent event) {
        state.setText("Italy");
        description.setText("Rome\n"
                + "A standard stop on many European vacation itineraries, Rome is not to be missed. Here, you will find the\n"
                + "most important relic from the Roman Empire (the Colosseum), the best food in Italy (Rome is home to\n"
                + "many Michelin-starred restaurants) and, of course, the center of Catholicism (the Vatican).\n"
                + "Amalfi Coast, travelers say the journey is more than worth it. The region covers more than two dozen\n"
                + "miles of coastline and is home to 13 colorful seaside towns, each with its own unique story. While here,\n"
                + "hike, relax on the beach and eat to your heart's delight.\n"
                + "Cinque Terre, located on Italy northern Ligurian coast, is made up of five colorful, seaside towns tucked\n"
                + "seamlessly on the region cliff-side terrain");
        bg1.setVisible(false);
        bg2.setVisible(false);
        bg3.setVisible(false);
        bg4.setVisible(false);
        bg5.setVisible(false);
        bg6.setVisible(false);
        bg7.setVisible(true);
        bg8.setVisible(false);
        bg9.setVisible(false);
        bg10.setVisible(false);
    }

    @FXML
    void portugal(MouseEvent event) {
        state.setText("Portugal");
        description.setText("The Algarve offers an enchanting mix of\n"
                + "warm weather, trendy beaches,\n"
                + "social events, and hiking trails. This region of Portugal is mostly known for its treasured gems such as\n"
                + "Camilo Beach.\n"
                + "Before Lisbon became the capital of Portugal, there was Coimbra. A charming, quaint town that blends\n"
                + "its dynamic culture with fado music rhythms, historical sites, and gorgeous gardens.");
        bg1.setVisible(false);
        bg2.setVisible(false);
        bg3.setVisible(false);
        bg4.setVisible(false);
        bg5.setVisible(false);
        bg6.setVisible(false);
        bg7.setVisible(false);
        bg8.setVisible(true);
        bg9.setVisible(false);
        bg10.setVisible(false);
    }

    @FXML
    void spain(MouseEvent event) {
        state.setText("Spain");
        description.setText("Madrit , Spain's large capital city showcases the country incredible history. It';s a perfect holiday\n"
                + "destination, as there are royal palaces, marching soldiers, changing of the guards, and hundreds of\n"
                + "museums to visit.Also for all sport lovers , there you can find the Santiago Bernabeu stadium.\n"
                + "Barcelona, Its coastal location gives it more of a resort feel, complete with warm, sunny weather to\n"
                + "enjoy on most days of the year. It's on the country northeast coast, by the Mediterranean Sea, and also\n"
                + "seems to have a much more modern, progressive vibe than Madrid and other Spanish cities.");
        bg1.setVisible(false);
        bg2.setVisible(false);
        bg3.setVisible(false);
        bg4.setVisible(false);
        bg5.setVisible(false);
        bg6.setVisible(false);
        bg7.setVisible(false);
        bg8.setVisible(false);
        bg9.setVisible(true);
        bg10.setVisible(false);
    }

    @FXML
    void turkey(MouseEvent event) {
        state.setText("Turkey");
        description.setText("The surreal, swooping rock valleys of Cappadocia are every photographer's dream. Cliff ridges and hill\n"
                + "crests are home to rippling panoramas of wave-like rock or wacky-shaped pinnacles that have been\n"
                + "formed by millennia of wind and water action.\n"
                + "Istanbul is the dream place. The view , the food and the feeling that it gives you is really breathless.\n"
                + "There are a lot of things to do there such as seeing some beautiful buildings like Dolmabahce Palace or\n"
                + "Hagia Sophia.");
        bg1.setVisible(false);
        bg2.setVisible(false);
        bg3.setVisible(false);
        bg4.setVisible(false);
        bg5.setVisible(false);
        bg6.setVisible(false);
        bg7.setVisible(false);
        bg8.setVisible(false);
        bg9.setVisible(false);
        bg10.setVisible(true);
    }

    public MapController() {
        con = ConUtil.vacDB();
    }

    @FXML
    void closeW(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Main.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
