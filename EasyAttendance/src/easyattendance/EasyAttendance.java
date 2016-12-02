/**
 * This file loads the first scene of the GUI
 * and shows the primary stage
 * @author  Mitchell Kelly Cs 275
 * @version 1.0, November 2016
 */ 

package easyattendance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mikelly
 */
public class EasyAttendance extends Application {
    
    /**
     * This method overrides the start method
     * passed to main in args to display the GUI
     *
     * @param   primaryStage the main stage to be shown in the GUI
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // load page content
        Parent root = FXMLLoader.load(getClass().getResource("FXML/ClassList.fxml"));
        // assign the content to a scene
        Scene scene = new Scene(root, 350, 250);
        
        primaryStage.setTitle("Easy Attendance");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
