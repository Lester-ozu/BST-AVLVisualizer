import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class AVLPane extends Pane {
    
    private AVLTree myBT;
    private double radius = 15, vGap = 40;
    private Hashtable<Integer, Circle> myTable = new Hashtable<>();

    public AVLPane(AVLTree myBT) {

        this.myBT = myBT;

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(this.widthProperty());
        clip.heightProperty().bind(this.heightProperty());
        this.setClip(clip);

        this.widthProperty().addListener((observable, oldValue, newValue) -> displayTree());
        this.heightProperty().addListener((observable, oldValue, newValue) -> displayTree());
    }

    public void displayTree() {

        myTable.clear();
        this.getChildren().clear();
        if(myBT.getRoot() != null && myBT.getRoot().getData() != null) {

            displayTree(myBT.getRoot(), getWidth() / 2, vGap, getWidth() / 4, Color.MEDIUMPURPLE);
        }
    }

    protected void displayTree(TreeNode<Integer> root, double x, double y, double hGap, Color color) {
    
        if(root.getLeftTree() != null) {

            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayTree(root.getLeftTree(), x - hGap, y + vGap, hGap / 2, color);
        }

        if(root.getRightTree() != null) {

            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayTree(root.getRightTree(), x + hGap, y + vGap, hGap / 2, color);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(color);
        circle.setStroke(Color.BLACK);

        Text myText;
        myText = new Text(x - 7, y + 4, String.valueOf(root.getData()));

        myText.setStyle("-fx-font-size: 10px;");
        myTable.put(root.getData(), circle);
        getChildren().addAll(circle, myText);
    }

    public void visualizeTraversal(String traversal, double speed) {

        List<Integer> sequence = new ArrayList<>();
        
        switch(traversal) {

            case "In-Order":
                inOrderTraversal(sequence);
                break;
            case "Post-Order":
                postOrderTraversal(sequence);
                break;
            case "Pre-Order":
                preOrderTraversal(sequence);
                break;
        }

        animateTraversal(sequence, speed);
    }

    private void inOrderTraversal(List<Integer> sequence) {

        String [] list = myBT.inOrder().split(" ");

        for(String s : list) {

            sequence.add(Integer.parseInt(s));
        }
    }

    private void preOrderTraversal(List<Integer> sequence) {

        String [] list = myBT.preOrder().split(" ");

        for(String s : list) {

            sequence.add(Integer.parseInt(s));
        }
    }

    private void postOrderTraversal(List<Integer> sequence) {

        String [] list = myBT.postOrder().split(" ");

        for(String s : list) {

            sequence.add(Integer.parseInt(s));
        }
    }

    private void animateTraversal(List<Integer> sequence, double speed) {

        System.out.println("YESS");

        Timeline timeLine = new Timeline();
        for(int i = 0 ; i < sequence.size() ; i++) {

            int num = sequence.get(i);

            timeLine.getKeyFrames().add(
                new KeyFrame(Duration.seconds(i * speed), e -> highlightNode(num))
            );

            timeLine.getKeyFrames().add(
                new KeyFrame(Duration.seconds(i * speed + speed), e -> resetNodeColor(num))
            );
        }

        timeLine.play();
    }   

    private void highlightNode(int num) {

        Circle nodeCircle = myTable.get(num);

        if(nodeCircle != null) {

            nodeCircle.setFill(Color.RED);
        }

        else System.out.println("Cannot get the circles");
    }

    private void resetNodeColor(int num) {

        Circle nodeCircle = myTable.get(num);

        if(nodeCircle != null) {

            nodeCircle.setFill(Color.MEDIUMPURPLE);
        }

        else System.out.println("Cannot get the circles");
    }
}
