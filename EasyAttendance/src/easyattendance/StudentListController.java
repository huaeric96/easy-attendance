/**
 * This file holds the functionality of the Student List Scene
 * @author  Mitchell Kelly Cs 275
 * @version 1.0, November 2016
 */ 
package easyattendance;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 *
 * @author mikelly
 */
public class StudentListController implements Initializable {
    
    
    @FXML
    private Button backButton; // button to retutn to a previous page
    @FXML
    private Button markPresentButton; // button to manually mark a student present
    @FXML
    private Button markTardyButton; // button to manually make a student tardy
    @FXML
    private Button markAbsentButton; // button to manually make a student absent
    @FXML
    private VBox studentVBox; // vertial student content
    @FXML
    private ListView studentListView; // list of students viewable on GUI
    
    private ObservableList studentObservList; // editable list of students to be shown on GUI
    private String _className; // name of class being shown
    
    StudentListController(String className){
        _className = className;
    }
    
    /**
     * This method is called when an action is called on GUI back Button
     * and returns the GUI to a previous scene
     */
    @FXML
    private void backButtonAction(){
        try {
        // get reference to the correct stage
        Stage primaryStage = (Stage) backButton.getScene().getWindow();
        // load content
        Parent root = FXMLLoader.load(getClass().getResource("FXML/ClassList.fxml"));
        
        //set content
        Scene classListScene = new Scene(root, 350, 250);
        primaryStage.setScene(classListScene);
        
        } catch (IOException ex) {
            System.out.println("Error Loading ClassList.fxml");
        }
    }
    
    /**
     * This method is called when an action is called on GUI takeRoll button
     * and removes students names from the ListView as their devices are found
     * then marks their attendance appropriately
     */
    @FXML
    private void takeRoll() {
        System.out.println("Help!");
        // remove studets when their device is found
        // mark attendance of that student
    }
    
    /**
     * This method is called when an action is called on GUI openRecords Button
     * and changes to the Attendance Records scene
     */
    @FXML
    private void openRecords() {
        // File loader
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/AttendanceRecords.fxml"));
        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> controllerClass) {
                AttendanceRecordsController controller = new AttendanceRecordsController(_className);
                return controller;
                }
            });
        try {
            // load content
            Parent recordsRoot = fxmlLoader.load();
            
            // set content page
            Scene settingsScene = new Scene(recordsRoot, 350, 250);

            // get reference to the correct stage
            Stage primaryStage = (Stage) studentVBox.getScene().getWindow();
            // show content
            primaryStage.setScene(settingsScene);
        } catch (IOException ex) {
            System.out.println("Error opening AttendanceRecords.fxml");
        }
    }
    
    /**
     * This method is called when an action is called on GUI openSettings Button
     * and opens the settings stage and scene
     */
    @FXML
    private void openSettings() {
        // root the content will go into
        Parent settingsRoot = null;
        // content file loader
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/StudentSettings.fxml"));
        fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> controllerClass) {
                StudentSettingsController controller = new StudentSettingsController(_className);
                return controller;
            }
        });
        try {
            // load content
            settingsRoot = fxmlLoader.load();
            
            // set content to a scene
            Scene settingsScene = new Scene(settingsRoot, 325, 250);
            
            // stage to show content
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(settingsScene);
            popupStage.show();
        } catch (IOException ex) {
            System.out.println("Error opening StudentSettings.fxml");
        }
    }
    
    /**
     * This method is called when an action is called on a GUI markAttendace Button
     * and removes selected student from the ListView
     * then marks their attendance appropriately
     * 
     * @param event the event that called markAttendance method
     */
    @FXML
    private void markAttendance(ActionEvent event) {
        if(event.getSource() == markPresentButton){
            // student name from studentListView on GUI
            String name = studentListView.getSelectionModel().getSelectedItem().toString();
            System.out.println(name + " is present!");
            studentObservList.remove(studentListView.getSelectionModel().getSelectedItem());
            ReadFile.markAttendance(_className, name, "Present");
        }
        else if(event.getSource() == markTardyButton){
            // student name from studentListView on GUI
            String name = studentListView.getSelectionModel().getSelectedItem().toString();
            System.out.println("Tardy!");
            studentObservList.remove(studentListView.getSelectionModel().getSelectedItem());
            ReadFile.markAttendance(_className, name, "Tardy");
            
        }
        else if(event.getSource() == markAbsentButton){
            // student name from studentListView on GUI
            String name = studentListView.getSelectionModel().getSelectedItem().toString();
            System.out.println("Absent!");
            studentObservList.remove(studentListView.getSelectionModel().getSelectedItem());
            ReadFile.markAttendance(_className, name, "Absent");
        }
    }
    
    /**
     * Initializes the StudentList controller on page load
     * @param fxmlFileLocation The location used to resolve relative paths for the root object
     *            or null if the location is not known
     * @param resources the resources used to localize the root object
     *           or null if the root object was not localized
     */
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        ArrayList<String> studentList = ReadFile.getStudents(_className);
        studentObservList = FXCollections.observableList(studentList);
        studentListView.setItems(studentObservList);
    }
}
