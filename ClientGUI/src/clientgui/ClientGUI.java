
package clientgui;

import java.util.LinkedList;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Alex Geer
 */


public class ClientGUI extends Application {
    //text display
    private TextArea output;
    //client controls
    private LinkedList<Button> buttons; 
    Button tdButton;        //time/date
    Button uptimeButton;    //uptime
    Button memButton;       //memory usage
    Button netstatButton;   //netstat
    Button usersButton;     //current users
    Button processesButton; //running processes
    
    //thread management controls
    ChoiceBox nThreads; //the number of active client threads
    
    @Override
    public void start(Stage primaryStage) {
       
        buttons = new LinkedList();
        buttons.add(tdButton = new Button("time/date"));
        buttons.add(uptimeButton = new Button("uptime"));
        buttons.add(memButton = new Button("memory use"));
        buttons.add(netstatButton = new Button("Netstat"));
        buttons.add(usersButton = new Button("current users"));
        buttons.add(processesButton = new Button("running proc"));
        
        
        
        BorderPane root = new BorderPane();
        
        root.setRight(addHostControls(buttons));
        root.setLeft(addThreadControls());
        root.setCenter(addScroll());
        Scene scene = new Scene(root, 500, 350);
        
        //configure buttons here (currently only two)
        tdButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
              //something happens here
              output.appendText( "should print server time/date \n" );
            }
        });
        
        uptimeButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
             //something else happens here
              output.appendText( "should print server uptime \n" );
            }
        });
       
        
        //show window       
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * 
     * @param controls
     * @return 
     */
    public VBox addHostControls(LinkedList<Button> controls) {
    //create return pane
    VBox vbox = new VBox();
    vbox.setPadding(new Insets(10));
    vbox.setSpacing(8);
    
    //put title
    Text title = new Text("Commands");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    vbox.getChildren().add(title);
    
    //add all buttons in the argument list
    for (Button b : controls) {
        VBox.setMargin(b, new Insets(0, 0, 0, 8));
        vbox.getChildren().add(b);
    }
    
    return vbox;
}
    /**
     * 
     * @return 
     */
    public VBox addThreadControls() {
    VBox vbox = new VBox();
    vbox.setPadding(new Insets(10));
    vbox.setSpacing(8);
    
    //header
    Text title = new Text("Number of Clients");
    vbox.getChildren().add(title);
    
    //choicebox where user selects number of threads to run to simulate clients
    nThreads = new ChoiceBox(FXCollections.observableArrayList("1","5","10","20","30","40","50","60","70","80","90","100","iterations"));
    vbox.getChildren().add(nThreads);
    
    return vbox;

    }
    
    private ScrollPane addScroll() {
        ScrollPane sp = new ScrollPane();
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        //set text output 
        output = new TextArea();
        output.setEditable(false);
        output.setWrapText(true);
        
        //set borders
        sp.setStyle("-fx-border-color: black;");
        //place output in scroll
        sp.setContent(output);
        sp.setFitToHeight(true);
        
        return sp;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
