package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Basic calculator application - Petru Birzu
 */

public class Main extends Application {

    // objects
    TextField display;
    Button but0, but1, but2, but3, but4, but5, but6, but7, but8, but9, butEq, butAd, butSu, butMu, butDi, butPo, butClear, butBack;
    StackPane root;
    VBox layout;
    GridPane grid;
    double tempNum = 0, result = 0, tempResult = 0;
    int operation = 0; // 1 = add,  2 = subtract,  3 = divide,  4 = multiply


    @Override
    public void start(Stage primaryStage) throws Exception {

        // root layout
        root = new StackPane();

        // layout
        layout = new VBox(8);
        layout.setPadding(new Insets(5));


        // display field
        display = new TextField();
        display.setText("0");
        display.setEditable(false);
        display.setFont(Font.font("Comic Sans MS", 40));
        display.setMaxSize(265,110);
        display.setStyle("-fx-border-radius: 10;" + "-fx-border-color: dodgerblue;");
        display.setFocusTraversable(false);
        display.setAlignment(Pos.BOTTOM_RIGHT);


        // grid for buttons
        grid = new GridPane();
        grid.setHgap(1.5);
        grid.setVgap(1.5);


        // buttons
        butEq = new Button("=");
        butEq.setDefaultButton(true);
        butEq.setPrefSize(65,60);
        butEq.setStyle("-fx-font-size: 25;" + "-fx-background-color: dodgerblue");

        but0 = new Button("0");
        but0.setPrefSize(130,60);
        but0.setStyle("-fx-font-size: 20");

        but1 = new Button("1");
        but1.setPrefSize(65,60);
        but1.setStyle("-fx-font-size: 20");

        but2 = new Button("2");
        but2.setPrefSize(65,60);
        but2.setStyle("-fx-font-size: 20");

        but3 = new Button("3");
        but3.setPrefSize(65,60);
        but3.setStyle("-fx-font-size: 20");

        but4 = new Button("4");
        but4.setPrefSize(65,60);
        but4.setStyle("-fx-font-size: 20");

        but5 = new Button("5");
        but5.setPrefSize(65,60);
        but5.setStyle("-fx-font-size: 20");

        but6 = new Button("6");
        but6.setPrefSize(65,60);
        but6.setStyle("-fx-font-size: 20");

        but7 = new Button("7");
        but7.setPrefSize(65,60);
        but7.setStyle("-fx-font-size: 20");

        but8 = new Button("8");
        but8.setPrefSize(65,60);
        but8.setStyle("-fx-font-size: 20");

        but9 = new Button("9");
        but9.setPrefSize(65,60);
        but9.setStyle("-fx-font-size: 20");

        butPo = new Button(".");
        butPo.setPrefSize(65,60);
        butPo.setStyle("-fx-font-size: 25");

        butAd = new Button("+");
        butAd.setPrefSize(65,60);
        butAd.setStyle("-fx-font-size: 25;" + "-fx-background-color: dodgerblue");

        butSu = new Button("-");
        butSu.setPrefSize(65,60);
        butSu.setStyle("-fx-font-size: 25;" + "-fx-background-color: dodgerblue");

        butDi = new Button("/");
        butDi.setPrefSize(65,50);
        butDi.setStyle("-fx-font-size: 25;" + "-fx-background-color: dodgerblue");

        butMu = new Button("x");
        butMu.setPrefSize(65,60);
        butMu.setStyle("-fx-font-size: 25;" + "-fx-background-color: dodgerblue");

        butClear = new Button("Clear");
        butClear.setPrefSize(130,50);
        butClear.setStyle("-fx-font-size: 20");

        butBack = new Button("<=]");
        butBack.setPrefSize(65,50);
        butBack.setStyle("-fx-font-size: 20");


        // buttons actions
        but0.setOnAction(event -> numPress("0"));
        but1.setOnAction(event -> numPress("1"));
        but2.setOnAction(event -> numPress("2"));
        but3.setOnAction(event -> numPress("3"));
        but4.setOnAction(event -> numPress("4"));
        but5.setOnAction(event -> numPress("5"));
        but6.setOnAction(event -> numPress("6"));
        but7.setOnAction(event -> numPress("7"));
        but8.setOnAction(event -> numPress("8"));
        but9.setOnAction(event -> numPress("9"));

        butPo.setOnAction(event -> {
            display.setText(display.getText() + ".");
            tempNum = Double.parseDouble(display.getText());
        });


        // backslash button action
        butBack.setOnAction(event1 -> {
            if (!display.getText().equalsIgnoreCase("0") && !display.getText().isEmpty()) {
                display.deleteText(display.getText().length() - 1, display.getText().length());
                if (display.getText().isEmpty()) {
                    display.setText("0");
                }
                tempNum = Double.parseDouble(display.getText());
            }
        });

        // add button action
        butAd.setOnAction(event -> {
            tempOperation();
            operation = 1;
        });

        // subtract button action
        butSu.setOnAction(event -> {
            tempOperation();
            operation = 2;
        });

        // divide button action
        butDi.setOnAction(event -> {
            tempOperation();
            operation = 3;
        });

        // multiply button action
        butMu.setOnAction(event -> {
            tempOperation();
            operation = 4;
        });

        // clear button action
        butClear.setOnAction(event -> {
            display.setText("0");
            tempNum = 0;
            tempResult = 0;
            result = 0;
            operation = 0;
        });


        // EQUALS button action
        butEq.setOnAction(event -> {
            if (!display.getText().equalsIgnoreCase("0") && !display.getText().isEmpty()) {

                switch (operation) {

                    case (1): // add
                        result = tempResult + tempNum;
                        showRes(result);
                        break;


                    case (2): // subtract
                        result = tempResult - tempNum;
                        showRes(result);
                        break;


                    case (3): // divide
                        result = tempResult / tempNum;
                        showRes(result);
                        break;


                    case (4): // multiply
                        result = tempResult * tempNum;
                        showRes(result);
                        break;
                }
            }

            else if (display.getText().equalsIgnoreCase("0") && (operation == 3)) {
                display.setText("Error");
                tempNum = 0;
                tempResult = 0;
                result = 0;
                operation = 0;
            }

            else if (display.getText().equalsIgnoreCase("0") && (operation == 4)) {
                display.setText("0");
            }

            else if (display.getText().equalsIgnoreCase("0")) {
                result = tempResult;
                showRes(result);
            }

            tempResult = 0;
            operation = 0;
        });


        // preparing scene
        grid.add(butClear, 0, 0, 2, 1);
        grid.add(butBack, 2, 0);
        grid.add(butMu, 3, 1);
        grid.add(but7, 0, 1);
        grid.add(but8, 1, 1);
        grid.add(but9, 2, 1);
        grid.add(butDi, 3, 0);
        grid.add(but4, 0, 2);
        grid.add(but5, 1, 2);
        grid.add(but6, 2, 2);
        grid.add(butSu, 3, 2);
        grid.add(but1, 0, 3);
        grid.add(but2, 1, 3);
        grid.add(but3, 2, 3);
        grid.add(butAd, 3, 3);
        grid.add(but0, 0, 4, 2, 1);
        grid.add(butPo, 2, 4);
        grid.add(butEq, 3, 4);

        layout.getChildren().addAll(display, grid);
        root.getChildren().add(layout);

        // setScene, show
        primaryStage.getIcons().add(new Image("sample/calculator.png", 256, 256, true, true));
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root, 270, 400));
        primaryStage.show();

    }


    // number button event method
    public void numPress(String num) {
        if (display.getText().equalsIgnoreCase("0")) {
            display.setText(num);
        }
        else {
            display.setText(display.getText() + num);
        }

        tempNum = Double.parseDouble(display.getText());
    }


    // method to display the result
    public void showRes(double res) {
        NumberFormat vrajeala = DecimalFormat.getInstance();
        vrajeala.setMinimumFractionDigits(0);
        vrajeala.setMaximumFractionDigits(4);
        vrajeala.setMaximumIntegerDigits(7);

        display.setText(vrajeala.format(res));
        tempNum = res;
    }


    // operation button event method
    public void tempOperation() {
        display.setText("0");

        if (tempResult == 0) {
            tempResult = tempNum;
        }

        else {
            switch (operation) {
                case (1):
                    tempResult += tempNum;
                    break;

                case (2):
                    tempResult -= tempNum;
                    break;

                case (3):
                    tempResult /= tempNum;
                    break;

                case (4):
                    tempResult *= tempNum;
                    break;
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

}