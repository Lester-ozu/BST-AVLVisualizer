import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

 public class homeSceneController {

    @FXML private Button avlButton, bstButton;
    @FXML ImageView themeMode;
    @FXML Label mainTitleLabel, xLabel, helloLabel;

    private boolean nightMode = false;
    private Image nightModeIcon = new Image("nightMode.png");
    private Image lightModeIcon = new Image("lightMode.png");
    private Label notifLabel, inOrderLabel, preOrderLabel, postOrderLabel, minLabel, maxLabel, heightLabel, savePicLabel;

    public void toAVLPane(ActionEvent event) {

        notifLabel = new Label("  Welcome to AVL!  ");
        notifLabel.setStyle(
            "-fx-background-radius: 20; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;"
        );
        notifLabel.setFont(new Font("Montserrat Medium", 12));

        Stage modalStage = new Stage();
        FlowPane bstPane = new FlowPane(10, 13);
        bstPane.setAlignment(Pos.CENTER);

        AVLTree avlTree = new AVLTree();
        AVLPane BTVisuals = new AVLPane(avlTree);
        BTVisuals.setPrefSize(450, 250);
        BTVisuals.setStyle(
            "-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-width: 2; -fx-border-color: black;"
        );

        ScrollPane binaryScroll = new ScrollPane(BTVisuals);
        binaryScroll.setPrefSize(452, 253);

        Slider speedSlider = new Slider();
        speedSlider.setOrientation(Orientation.VERTICAL);
        speedSlider.setMin(0.1);
        speedSlider.setMax(3);
        speedSlider.setValue(2.0);
        speedSlider.setShowTickMarks(true);
        speedSlider.setShowTickLabels(true);
        speedSlider.setPrefSize(50, 90);

        TextField addField = new TextField();
        addField.setPrefSize(90, 25);
        addField.setStyle(
            "-fx-background-radius: 20; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;"
        );
        addField.setFont(new Font("Montserrat Medium", 12));
        addField.setAlignment(Pos.CENTER);

        Button addButton = new Button("Add");
        addButton.setPrefSize(80, 20);
        addButton.setStyle(
            "-fx-background-radius: 20; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;"
        );
        addButton.setFont(new Font("Montserrat Medium", 12));
        addButton.setOnAction(e -> {

            if(!addField.getText().trim().matches("\\d+")) {
                
                notifLabel.setText("  AVL only accepts integers!  ");
                notifLabel.setStyle(
                    "-fx-background-radius: 20; -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 20;"
                );
            }
            
            else {

                if(!avlTree.addNode(Integer.parseInt(addField.getText().trim()))) {

                    notifLabel.setText("  " + addField.getText().trim() + " is already in the BST!  ");
                    notifLabel.setStyle(
                        "-fx-background-radius: 20; -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 20;"
                    );
                    addField.clear();
                    return;
                }

                BTVisuals.displayTree();

                notifLabel.setText("  " + addField.getText().trim() + " added to AVL!  ");
                notifLabel.setStyle(
                    "-fx-background-radius: 20; -fx-border-color: green; -fx-border-width: 2; -fx-border-radius: 20;"
                );

                updateStats(avlTree);
                resizePane(BTVisuals, avlTree);
            }

            addField.clear();
        });
        
        HBox addNodes = new HBox();
        addNodes.setSpacing(10.0);
        addNodes.setPrefWidth(190);
        addNodes.setAlignment(Pos.CENTER);
        addNodes.getChildren().addAll(addField, addButton);

        TextField searchField = new TextField();
        searchField.setPrefSize(90, 25);
        searchField.setStyle(
            "-fx-background-radius: 20; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;"
        );
        searchField.setFont(new Font("Montserrat Medium", 12));
        searchField.setAlignment(Pos.CENTER);

        Button searchButton = new Button("Search");
        searchButton.setPrefSize(80, 20);
        searchButton.setStyle(
            "-fx-background-radius: 20; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;"
        );
        searchButton.setFont(new Font("Montserrat Medium", 12));
        searchButton.setOnAction(e -> {

            if(!searchField.getText().trim().matches("\\d+")) {

                notifLabel.setText("  AVL only contains integers!  ");
                notifLabel.setStyle(
                    "-fx-background-radius: 20; -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 20;"
                );
            }

            else {

                if(avlTree.contains(Integer.parseInt(searchField.getText().trim()))) {

                    notifLabel.setText("  " + searchField.getText().trim() + " is in the AVL!  ");
                    notifLabel.setStyle(
                        "-fx-background-radius: 20; -fx-border-color: green; -fx-border-width: 2; -fx-border-radius: 20;"
                    );
                }

                else {

                    notifLabel.setText("  " + searchField.getText().trim() + " is not in the AVL!  ");
                    notifLabel.setStyle(
                        "-fx-background-radius: 20; -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 20;"
                    );
                }
            }

            searchField.clear();
        });

        HBox searchNodes = new HBox();
        searchNodes.setSpacing(10.0);
        searchNodes.setPrefWidth(190);
        searchNodes.setAlignment(Pos.CENTER);
        searchNodes.getChildren().addAll(searchField, searchButton);

        TextField removeField = new TextField();
        removeField.setPrefSize(90, 25);
        removeField.setStyle(
            "-fx-background-radius: 20; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;"
        );
        removeField.setFont(new Font("Montserrat Medium", 12));
        removeField.setAlignment(Pos.CENTER);

        Button removeButton = new Button("Remove");
        removeButton.setPrefSize(80, 20);
        removeButton.setStyle(
            "-fx-background-radius: 20; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;"
        );
        removeButton.setFont(new Font("Montserrat Medium", 12));
        removeButton.setOnAction(e -> {

            if(!removeField.getText().trim().matches("\\d+")) {

                notifLabel.setText("  AVL only contains integers!  ");
                notifLabel.setStyle(
                    "-fx-background-radius: 20; -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 20;"
                );
            }
            
            else {

                if(avlTree.remove(Integer.parseInt(removeField.getText().trim()))) {

                    notifLabel.setText("  " + removeField.getText().trim() + " has been removed!  ");
                    notifLabel.setStyle(
                        "-fx-background-radius: 20; -fx-border-color: green; -fx-border-width: 2; -fx-border-radius: 20;"
                    );

                    BTVisuals.displayTree();
                }

                else {

                    notifLabel.setText("  Cannot delete since " + removeField.getText().trim() + " is not in the AVL!  ");
                    notifLabel.setStyle(
                        "-fx-background-radius: 20; -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 20;"
                    );
                }
            }

            removeField.clear();
        });

        HBox removeNodes = new HBox();
        removeNodes.setSpacing(10.0);
        removeNodes.setPrefWidth(190);
        removeNodes.setAlignment(Pos.CENTER);
        removeNodes.getChildren().addAll(removeField, removeButton);

        FlowPane crudPane = new FlowPane(10, 13);
        crudPane.setAlignment(Pos.CENTER);
        crudPane.setPrefSize(50, 90);
        crudPane.getChildren().addAll(addNodes, searchNodes, removeNodes);

        inOrderLabel = new Label("In Order: ");
        inOrderLabel.setFont(new Font("Montserrat Medium", 12));
        inOrderLabel.setLayoutX(5);
        inOrderLabel.setLayoutY(5);
        inOrderLabel.setOnMouseClicked(e -> {

            BTVisuals.visualizeTraversal("In-Order", speedSlider.getValue());
        });
        inOrderLabel.setOnMouseEntered(e -> {

            inOrderLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-cursor: hand;");
        });
        inOrderLabel.setOnMouseExited(e -> {

            inOrderLabel.setStyle("");
        });

        preOrderLabel = new Label("Pre Order: ");
        preOrderLabel.setFont(new Font("Montserrat Medium", 12));
        preOrderLabel.setLayoutX(5);
        preOrderLabel.setLayoutY(25);
        preOrderLabel.setOnMouseClicked(e -> {

            BTVisuals.visualizeTraversal("Pre-Order", speedSlider.getValue());
        });
        preOrderLabel.setOnMouseEntered(e -> {

            preOrderLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-cursor: hand;");
        });
        preOrderLabel.setOnMouseExited(e -> {

            preOrderLabel.setStyle("");
        });

        postOrderLabel = new Label("Post Order: ");
        postOrderLabel.setFont(new Font("Montserrat Medium", 12));
        postOrderLabel.setLayoutX(5);
        postOrderLabel.setLayoutY(45);
        postOrderLabel.setOnMouseClicked(e -> {

            BTVisuals.visualizeTraversal("Post-Order", speedSlider.getValue());
        });
        postOrderLabel.setOnMouseEntered(e -> {

            postOrderLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-cursor: hand;");
        });
        postOrderLabel.setOnMouseExited(e -> {

            postOrderLabel.setStyle("");
        });

        minLabel = new Label("Min: ");
        minLabel.setFont(new Font("Montserrat Medium", 12));
        minLabel.setLayoutX(5);
        minLabel.setLayoutY(65);

        maxLabel = new Label("Max: ");
        maxLabel.setFont(new Font("Montserrat Medium", 12));
        maxLabel.setLayoutX(5);
        maxLabel.setLayoutY(85);

        heightLabel = new Label("Height: ");
        heightLabel.setFont(new Font("Montserrat Medium", 12));
        heightLabel.setLayoutX(120);
        heightLabel.setLayoutY(65);

        savePicLabel = new Label("Save Tree Picture?");
        savePicLabel.setFont(new Font("Montserrat Medium", 9));
        savePicLabel.setLayoutX(120);
        savePicLabel.setLayoutY(85);
        savePicLabel.setTextFill(Color.BLUE);
        savePicLabel.setOnMouseEntered(e -> {

            savePicLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-cursor: hand;");
        });

        savePicLabel.setOnMouseExited(e -> {

            savePicLabel.setStyle("");
        });

        savePicLabel.setOnMouseClicked(e ->{

            Alert ifSaveTree = new Alert(AlertType.CONFIRMATION);
            ifSaveTree.setTitle("SAVE?");
            ifSaveTree.setHeaderText("Do you want to save tree picture?");
            ifSaveTree.setContentText("Click Ok to Proceed or Cancel.");

            if(ifSaveTree.showAndWait().orElse(ButtonType.NO) == ButtonType.OK) {

                saveTreePicture(BTVisuals, modalStage);
            }
        });

        Pane statsPane = new Pane();
        statsPane.setPrefSize(220, 90);
        statsPane.getChildren().addAll(inOrderLabel, preOrderLabel, postOrderLabel, minLabel, maxLabel, heightLabel, savePicLabel);
        statsPane.setStyle(
            "-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-width: 2; -fx-border-color: black;"
        );

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(statsPane.widthProperty());
        clip.heightProperty().bind(statsPane.heightProperty());

        statsPane.setClip(clip);

        bstPane.getChildren().addAll(notifLabel, binaryScroll, crudPane, statsPane, speedSlider);

        Scene scene = new Scene(bstPane, 500, 450);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.setTitle("AVL Tree");
        modalStage.getIcons().add(new Image("avlIcon.jpg"));
        modalStage.setScene(scene);
        modalStage.setResizable(false);
        modalStage.show();

        BTVisuals.displayTree();
    }

    public void toBSTPane(ActionEvent event) {

        notifLabel = new Label("  Welcome to BST!  ");
        notifLabel.setStyle(
            "-fx-background-radius: 20; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;"
        );
        notifLabel.setFont(new Font("Montserrat Medium", 12));

        Stage modalStage = new Stage();
        FlowPane bstPane = new FlowPane(10, 13);
        bstPane.setAlignment(Pos.CENTER);

        BinarySearchTree binaryTree = new BinarySearchTree();
        BSTPane BTVisuals = new BSTPane(binaryTree);
        BTVisuals.setPrefSize(450, 250);
        BTVisuals.setStyle(
            "-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-width: 2; -fx-border-color: black;"
        );

        ScrollPane binaryScroll = new ScrollPane(BTVisuals);
        binaryScroll.setPrefSize(452, 253);

        TextField addField = new TextField();
        addField.setPrefSize(90, 25);
        addField.setStyle(
            "-fx-background-radius: 20; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;"
        );
        addField.setFont(new Font("Montserrat Medium", 12));
        addField.setAlignment(Pos.CENTER);

        Button addButton = new Button("Add");
        addButton.setPrefSize(80, 20);
        addButton.setStyle(
            "-fx-background-radius: 20; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;"
        );
        addButton.setFont(new Font("Montserrat Medium", 12));
        addButton.setOnAction(e -> {

            if(!addField.getText().trim().matches("\\d+")) {
                
                notifLabel.setText("  BST only accepts integers!  ");
                notifLabel.setStyle(
                    "-fx-background-radius: 20; -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 20;"
                );
            }
            
            else {

                if(!binaryTree.addNode(Integer.parseInt(addField.getText().trim()))) {

                    notifLabel.setText("  " + addField.getText().trim() + " is already in the BST!  ");
                    notifLabel.setStyle(
                        "-fx-background-radius: 20; -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 20;"
                    );
                    addField.clear();
                    return;
                }
                
                BTVisuals.displayTree();

                notifLabel.setText("  " + addField.getText().trim() + " added to BST!  ");
                notifLabel.setStyle(
                    "-fx-background-radius: 20; -fx-border-color: green; -fx-border-width: 2; -fx-border-radius: 20;"
                );

                updateStats(binaryTree);
                resizePane(BTVisuals, binaryTree);
            }

            addField.clear();
        });
        
        HBox addNodes = new HBox();
        addNodes.setSpacing(10.0);
        addNodes.setPrefWidth(190);
        addNodes.setAlignment(Pos.CENTER);
        addNodes.getChildren().addAll(addField, addButton);

        TextField searchField = new TextField();
        searchField.setPrefSize(90, 25);
        searchField.setStyle(
            "-fx-background-radius: 20; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;"
        );
        searchField.setFont(new Font("Montserrat Medium", 12));
        searchField.setAlignment(Pos.CENTER);

        Button searchButton = new Button("Search");
        searchButton.setPrefSize(80, 20);
        searchButton.setStyle(
            "-fx-background-radius: 20; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;"
        );
        searchButton.setFont(new Font("Montserrat Medium", 12));
        searchButton.setOnAction(e -> {

            if(!searchField.getText().trim().matches("\\d+")) {

                notifLabel.setText("  BST only contains integers!  ");
                notifLabel.setStyle(
                    "-fx-background-radius: 20; -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 20;"
                );
            }

            else {

                if(binaryTree.contains(Integer.parseInt(searchField.getText().trim()))) {

                    notifLabel.setText("  " + searchField.getText().trim() + " is in the BST!  ");
                    notifLabel.setStyle(
                        "-fx-background-radius: 20; -fx-border-color: green; -fx-border-width: 2; -fx-border-radius: 20;"
                    );
                }

                else {

                    notifLabel.setText("  " + searchField.getText().trim() + " is not in the BST!  ");
                    notifLabel.setStyle(
                        "-fx-background-radius: 20; -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 20;"
                    );
                }
            }

            searchField.clear();
        });

        HBox searchNodes = new HBox();
        searchNodes.setSpacing(10.0);
        searchNodes.setPrefWidth(190);
        searchNodes.setAlignment(Pos.CENTER);
        searchNodes.getChildren().addAll(searchField, searchButton);

        TextField removeField = new TextField();
        removeField.setPrefSize(90, 25);
        removeField.setStyle(
            "-fx-background-radius: 20; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;"
        );
        removeField.setFont(new Font("Montserrat Medium", 12));
        removeField.setAlignment(Pos.CENTER);

        Button removeButton = new Button("Remove");
        removeButton.setPrefSize(80, 20);
        removeButton.setStyle(
            "-fx-background-radius: 20; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;"
        );
        removeButton.setFont(new Font("Montserrat Medium", 12));
        removeButton.setOnAction(e -> {

            if(!removeField.getText().trim().matches("\\d+")) {

                notifLabel.setText("  BST only contains integers!  ");
                notifLabel.setStyle(
                    "-fx-background-radius: 20; -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 20;"
                );
            }
            
            else {

                if(binaryTree.remove(Integer.parseInt(removeField.getText().trim()))) {

                    notifLabel.setText("  " + removeField.getText().trim() + " has been removed!  ");
                    notifLabel.setStyle(
                        "-fx-background-radius: 20; -fx-border-color: green; -fx-border-width: 2; -fx-border-radius: 20;"
                    );

                    BTVisuals.displayTree();
                }

                else {

                    notifLabel.setText("  Cannot delete since " + removeField.getText().trim() + " is not in the BST!  ");
                    notifLabel.setStyle(
                        "-fx-background-radius: 20; -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 20;"
                    );
                }
            }

            removeField.clear();
        });

        HBox removeNodes = new HBox();
        removeNodes.setSpacing(10.0);
        removeNodes.setPrefWidth(190);
        removeNodes.setAlignment(Pos.CENTER);
        removeNodes.getChildren().addAll(removeField, removeButton);

        FlowPane crudPane = new FlowPane(10, 13);
        crudPane.setAlignment(Pos.CENTER);
        crudPane.setPrefSize(50, 90);
        crudPane.getChildren().addAll(addNodes, searchNodes, removeNodes);

        inOrderLabel = new Label("In Order: ");
        inOrderLabel.setFont(new Font("Montserrat Medium", 12));
        inOrderLabel.setLayoutX(5);
        inOrderLabel.setLayoutY(5);

        preOrderLabel = new Label("Pre Order: ");
        preOrderLabel.setFont(new Font("Montserrat Medium", 12));
        preOrderLabel.setLayoutX(5);
        preOrderLabel.setLayoutY(25);

        postOrderLabel = new Label("Post Order: ");
        postOrderLabel.setFont(new Font("Montserrat Medium", 12));
        postOrderLabel.setLayoutX(5);
        postOrderLabel.setLayoutY(45);

        minLabel = new Label("Min: ");
        minLabel.setFont(new Font("Montserrat Medium", 12));
        minLabel.setLayoutX(5);
        minLabel.setLayoutY(65);

        maxLabel = new Label("Max: ");
        maxLabel.setFont(new Font("Montserrat Medium", 12));
        maxLabel.setLayoutX(5);
        maxLabel.setLayoutY(85);

        heightLabel = new Label("Height: ");
        heightLabel.setFont(new Font("Montserrat Medium", 12));
        heightLabel.setLayoutX(120);
        heightLabel.setLayoutY(65);

        savePicLabel = new Label("Save Tree Picture?");
        savePicLabel.setFont(new Font("Montserrat Medium", 9));
        savePicLabel.setLayoutX(120);
        savePicLabel.setLayoutY(85);
        savePicLabel.setTextFill(Color.BLUE);
        savePicLabel.setOnMouseEntered(e -> {

            savePicLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-cursor: hand;");
        });

        savePicLabel.setOnMouseExited(e -> {

            savePicLabel.setStyle("");
        });

        savePicLabel.setOnMouseClicked(e ->{

            Alert ifSaveTree = new Alert(AlertType.CONFIRMATION);
            ifSaveTree.setTitle("SAVE?");
            ifSaveTree.setHeaderText("Do you want to save tree picture?");
            ifSaveTree.setContentText("Click Ok to Proceed or Cancel.");

            if(ifSaveTree.showAndWait().orElse(ButtonType.NO) == ButtonType.OK) {

                saveTreePicture(BTVisuals, modalStage);
            }
        });

        Pane statsPane = new Pane();
        statsPane.setPrefSize(220, 90);
        statsPane.getChildren().addAll(inOrderLabel, preOrderLabel, postOrderLabel, minLabel, maxLabel, heightLabel, savePicLabel);
        statsPane.setStyle(
            "-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-width: 2; -fx-border-color: black;"
        );

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(statsPane.widthProperty());
        clip.heightProperty().bind(statsPane.heightProperty());

        statsPane.setClip(clip);

        bstPane.getChildren().addAll(notifLabel, binaryScroll, crudPane, statsPane);

        Scene scene = new Scene(bstPane, 500, 450);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.setTitle("Binary Search Tree");
        modalStage.setScene(scene);
        modalStage.setResizable(false);
        modalStage.getIcons().add(new Image("bstIcon.jpg"));
        modalStage.show();

        BTVisuals.displayTree();
    }

    public void exitProgram(MouseEvent event) {

        System.exit(0);
    }

    public void changeTheme(MouseEvent event) {

        if(!nightMode) nightMode = true; else nightMode = false;

        if(nightMode) {

            themeMode.setImage(nightModeIcon);
            ((Stage)(((Node)event.getSource()).getScene().getWindow())).getScene().getRoot().setStyle(
                "-fx-background-color: black; -fx-background-radius: 20; -fx-padding: 20;" +
                "-fx-border-color: white; -fx-border-width: 2; -fx-border-radius: 20;"
            );

            mainTitleLabel.setTextFill(Color.WHITE);
            xLabel.setTextFill(Color.WHITE);
            helloLabel.setTextFill(Color.WHITE);
            avlButton.setTextFill(Color.WHITE);
            bstButton.setTextFill(Color.WHITE);
        }

        else {

            themeMode.setImage(lightModeIcon);
            ((Stage)(((Node)event.getSource()).getScene().getWindow())).getScene().getRoot().setStyle(
                "-fx-background-color: white; -fx-background-radius: 20; -fx-padding: 20;" +
                "-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;"
            );

            mainTitleLabel.setTextFill(Color.BLACK);
            xLabel.setTextFill(Color.BLACK);
            helloLabel.setTextFill(Color.BLACK);
            avlButton.setTextFill(Color.BLACK);
            bstButton.setTextFill(Color.BLACK);
        }
    }

    private void resizePane(BSTPane BTVisuals, BinarySearchTree binaryTree) {

        if (binaryTree == null) {
            System.err.println("binaryTree is null");
            return;
        }

        double width = Math.max(BTVisuals.getPrefWidth(), binaryTree.getHeight() * 250);
        double height = Math.max(BTVisuals.getPrefHeight(), binaryTree.getHeight() * 50);
        
        BTVisuals.setPrefSize(width, height);
    }

    private void resizePane(AVLPane BTVisuals, AVLTree binaryTree) {

        if (binaryTree == null) {
            System.err.println("binaryTree is null");
            return;
        }

        double width = Math.max(BTVisuals.getPrefWidth(), binaryTree.getHeight() * 150);
        double height = Math.max(BTVisuals.getPrefHeight(), binaryTree.getHeight() * 50);
        
        BTVisuals.setPrefSize(width, height);
    }

    private void updateStats(BinarySearchTree binaryTree) {

        inOrderLabel.setText("In Order: " + binaryTree.inOrder());
        preOrderLabel.setText("Pre Order: " + binaryTree.preOrder());
        postOrderLabel.setText("Post Order: " + binaryTree.postOrder());
        minLabel.setText("Min: " + binaryTree.findMin(binaryTree.getRoot()).getData());
        maxLabel.setText("Max: " + binaryTree.findMax(binaryTree.getRoot()).getData());
        heightLabel.setText("Height: " + binaryTree.getHeight());
    }

    private void updateStats(AVLTree binaryTree) {

        inOrderLabel.setText("In Order: " + binaryTree.inOrder());
        preOrderLabel.setText("Pre Order: " + binaryTree.preOrder());
        postOrderLabel.setText("Post Order: " + binaryTree.postOrder());
        minLabel.setText("Min: " + binaryTree.findMin(binaryTree.getRoot()).getData());
        maxLabel.setText("Max: " + binaryTree.findMax(binaryTree.getRoot()).getData());
        heightLabel.setText("Height: " + binaryTree.getHeight());
    }

    private void saveTreePicture(Pane pane, Stage stage) {

        WritableImage image = new WritableImage((int) pane.getWidth(), (int) pane.getHeight());
        pane.snapshot(null, image);

        FileChooser fileChooser = new FileChooser();

        File projectDir = new File(System.getProperty("user.dior"), "Tree Pictures");
        if(!projectDir.exists()) projectDir.mkdirs();

        fileChooser.setInitialDirectory(projectDir);
        fileChooser.setTitle("Save Tree Picture");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Portable Network Graphic", "*.png"));

        File file = fileChooser.showSaveDialog(stage);

        if(file != null) {

            try {

                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            }

            catch (IOException e) {

                System.err.println("Error saving tree picture: " + e.getMessage());
            }
        }
    }
}
