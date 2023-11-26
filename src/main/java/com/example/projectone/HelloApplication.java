package com.example.projectone;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;

public class HelloApplication extends Application {



    static  TextField[] textFields = null;

    int [] list ;
    CoinsIdeix coinsIdeix = new CoinsIdeix();





    Scene scene1,scene2,scene;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        StackPane st9 = new StackPane();
        Image mh9 = new Image("game.jpg");
        ImageView mah9 = new ImageView(mh9);
        mah9.setFitHeight(800);
        mah9.setFitWidth(800);

        GridPane root = new GridPane();

        Label label = new Label("Enter the number of coins: ");
        label.setFont(Font.font("Oranienbaum", 20));
        label.setTextFill(Color.WHITE);



        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);


        TextField Coins = new TextField();
        Coins.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));


         Coins.setOnAction(e->{
             try {


                 int n = Integer.parseInt(Coins.getText());

                 if (n % 2 != 0) {
                     Alert alert2 = new Alert(
                             Alert.AlertType.NONE, "The Game Is not fair", ButtonType.OK);

                     if (alert2.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                     }
                     return;
                 }
                 coinsIdeix.setN(n);

                 //  n = Integer.parseInt(Coins.getText());
                 textFields = new TextField[n];


                 for (int i = 0; i < textFields.length; i++) {
                     textFields[i] = new TextField();
                     textFields[i].setPrefWidth(50);
                     textFields[i].setPrefHeight(50);
                     textFields[i].setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
                     hBox.getChildren().add(textFields[i]);

                 }
                 list = new int[n];

                 stage.setScene(scene2);

             }catch (NumberFormatException ex){
                 Alert alert2 = new Alert(
                         Alert.AlertType.NONE, "Enter a number", ButtonType.OK);

                 if (alert2.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                 }
             }
         });



        root.setMargin(label, new Insets(10, 10, 10, 10));
        root.setMargin(Coins, new Insets(10, 10, 10, 10));

        root.add(Coins, 1, 0);
        root.add(label, 0, 0);

        root.setAlignment(Pos.CENTER);
        st9.getChildren().addAll(mah9,root);

//==================================================================================================
        Image Imeg = new Image("game.jpg");
        ImageView pic = new ImageView(Imeg);
        pic.setFitHeight(800);
        pic.setFitWidth(800);
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(pic);
        Button button1 = new Button("Start", new ImageView(new Image("start.png")));
        borderPane.setCenter(hBox);
        button1.setPrefWidth(120);
        button1.setPrefHeight(30);
        button1.setTextFill(Color.BLACK);
        button1.setContentDisplay(ContentDisplay.TOP);

        Button button4 = new Button("Play Again" , new ImageView(new Image("replay.png")));
        button4.setPrefWidth(120);
        button4.setPrefHeight(30);
        button4.setTextFill(Color.BLACK);
        button4.setContentDisplay(ContentDisplay.TOP);
        button4.setOnAction(e->{

            coinsIdeix.setCoins(null);
            coinsIdeix.setN(0);
            coinsIdeix.setTable(null);
            coinsIdeix.getPlayerone().clear();
            coinsIdeix.getPlayertwo().clear();
            coinsIdeix.getPlayerOneindices().clear();
            coinsIdeix.getPlayerTwoindices().clear();
            hBox.getChildren().clear();
            button1.setDisable(false);
            stage.setScene(scene1);
        });



        button1.setOnAction(e1-> {
            int flag =0 ;
            for(int i = 0 ; i<textFields.length;i++){
                if(textFields[i].getText().isEmpty()){

                    flag = 1 ;
                }
            }
            if(flag==1){
                Alert alert1 = new Alert(
                        Alert.AlertType.NONE, "You must fill the Text Fields", ButtonType.OK);

                if (alert1.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }

                return;
            }
            else


                button4.setDisable(true);
                button1.setDisable(true);

                for (int i = 0; i < textFields.length; i++) {

                    list[i] = Integer.parseInt(textFields[i].getText());
                }
                coinsIdeix.setCoins(list);


                int[][] table = coinsIdeix.DPTable(list, list.length);
                coinsIdeix.assignCoinsToPlayersForPlayerOneAndTwo(list, table);
                coinsIdeix.assignCoinsToPlayersIndices(list, table);


                Alert alert = new Alert(
                        Alert.AlertType.NONE, "The Optimal Strategy is: " + coinsIdeix.PrintTheOptimalStrategy(list, list.length), ButtonType.OK);

                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }


                Alert alert1 = new Alert(
                        Alert.AlertType.NONE, "The DP Table is : " + "\n" + coinsIdeix.PrintDPTable(list, list.length), ButtonType.OK);

                if (alert1.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }


                Alert alert2 = new Alert(
                        Alert.AlertType.NONE, "The Coins That give The Expected result is : " + "\n" + coinsIdeix.getPlayerone(), ButtonType.OK);

                if (alert2.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }


                Alert alert3 = new Alert(
                        Alert.AlertType.NONE, "Now let see How The Game Work", ButtonType.OK);

                if (alert3.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }

                System.out.println(coinsIdeix.getPlayerOneindices());
                System.out.println(coinsIdeix.getPlayerTwoindices());


                SequentialTransition sequentialTransition = new SequentialTransition();

                int numCoins = Math.min(coinsIdeix.getPlayerOneindices().size(), coinsIdeix.getPlayerTwoindices().size());

                for (int j = 0; j < numCoins; j++) {
                    TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1));
                    transition1.setNode(textFields[coinsIdeix.getPlayerOneindices().get(j)]);
                    transition1.setToY(-100);
                    transition1.setCycleCount(1);
                    sequentialTransition.getChildren().add(transition1);

                    TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1));
                    transition2.setNode(textFields[coinsIdeix.getPlayerTwoindices().get(j)]);
                    transition2.setToY(100);
                    transition2.setCycleCount(1);
                    sequentialTransition.setDelay(Duration.seconds(3));
                    sequentialTransition.getChildren().add(transition2);

                }

                sequentialTransition.play();

                sequentialTransition.setOnFinished(e -> {
                    button4.setDisable(false);
                    button1.setDisable(true);
                });



        });


        HBox vBox = new HBox();
      //  vBox.setAlignment(Pos.CENTER);
        vBox.setMargin(button1, new Insets(0, 0, 0, 250));
        vBox.setMargin(button4, new Insets(0, 0, 0, 70));
        vBox.getChildren().addAll(button1,button4);

        borderPane.setBottom(vBox);





        scene2 = new Scene(borderPane, 800, 800);
//==================================================================================================
        StackPane st11 = new StackPane();
        Image mh11 = new Image("game.jpg");
        ImageView mah11 = new ImageView(mh11);
        mah11.setFitHeight(800);
        mah11.setFitWidth(800);



            GridPane root1 = new GridPane();
            Button button2 = new Button("Play", new ImageView(new Image("play.png")));
            button2.setPrefWidth(115);
            button2.setPrefHeight(50);
        button2.setTextFill(Color.BLACK);
        button2.setContentDisplay(ContentDisplay.TOP);
            button2.setOnAction(e->{
                stage.setScene(scene1);
            });
            Button button3 = new Button("Exit" , new ImageView(new Image("power.png")));
            button3.setPrefWidth(115);
            button3.setPrefHeight(50);
        button3.setTextFill(Color.BLACK);
        button3.setContentDisplay(ContentDisplay.TOP);
            button3.setOnAction(e->{
                System.exit(0);
            });
            root1.add(button2, 0, 0);
            root1.add(button3, 0, 1);
        st11.getChildren().addAll(mah11,root1);
            root1.setAlignment(Pos.CENTER);
        scene = new Scene(st11, 800, 800);
//==================================================================================================
         scene1 = new Scene(st9, 800, 800);
        stage.setTitle("Optimal game Strategy ");
        stage.setScene(scene);
        stage.show();


    }


    public static void main(String[] args) {
        launch();
    }
}