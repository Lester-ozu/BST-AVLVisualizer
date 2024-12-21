import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BSTPane extends Pane {
    
    private BinarySearchTree myBT;
    private double radius = 15, vGap = 40;

    public BSTPane(BinarySearchTree myBT) {

        this.myBT = myBT;

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(this.widthProperty());
        clip.heightProperty().bind(this.heightProperty());
        this.setClip(clip);

        this.widthProperty().addListener((observable, oldValue, newValue) -> displayTree());
        this.heightProperty().addListener((observable, oldValue, newValue) -> displayTree());
    }

    public void displayTree() {

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
        myText = new Text(x - 6, y + 4, String.valueOf(root.getData()));

        myText.setStyle("-fx-font-size: 10px;");
        getChildren().addAll(circle, myText);
    }
}
