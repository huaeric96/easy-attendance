/**
 * This file holds the functionality of the Student Settings Scene
 * @author  Mitchell Kelly Cs 275
 * @version 1.0, November 2016
 */ 
package easyattendance;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentSettingsController {
    @FXML
    private VBox studentOpenVBox; // student vertial content shown once the page is opened
    @FXML
    private VBox studentSettingsVBox; // class vertial content shown once an action is entered
    @FXML
    private TextField studentNameInput; // input box for student name
    @FXML
    private TextField studentIDInput; // input box for student identification
    @FXML
    private ListView deviceListView; // list of parable devices
    @FXML
    private Text studentPrompt; // prompt for incorrect student input
    
    private String _className; // name of the class students are in
    
    StudentSettingsController(String className){
        _className = className;
    }
    
    /**
     * This method is called when an action is called on GUI load Button
     * and loads a students settings
     */
    @FXML
    private void loadStudentSettings() {
        // String student name from GUI
        String studentName = studentNameInput.getText();
        // array of student settings from file
        ArrayList<String> settings = ReadFile.getStudentSettings(_className, studentName); // get settings line
        if(settings == null) {
            studentPrompt.setText("Please Enter a Valid Student Name");
        }
        
        studentOpenVBox.setVisible(false);
        studentSettingsVBox.setVisible(true);
    }
    
    /**
     * This method is called when an action is called on GUI openSettings Button
     * and opens an empty student settings page
     */
    @FXML
    private void addStudent() {
        studentOpenVBox.setVisible(false);
        studentSettingsVBox.setVisible(true);
    }
    
    /**
     * This method is called when an action is called on GUI saveSettings Button
     * and saves the student settings to file
     */
    @FXML
    private void saveStudentSettings() {
        // Student name from GUI
        String studentName = studentNameInput.getText();
        // Student Identification number from GUI
        String studentID = studentIDInput.getText();
        // Student device ID from GUI
        String device = deviceListView.getSelectionModel().getSelectedItem().toString();
        
        //get reference to the stage
        Stage popupStage = new Stage();
        popupStage = (Stage) studentOpenVBox.getScene().getWindow();
        popupStage.close();
        
        ReadFile.saveStudentSettings(studentName, studentID, device);
    }
    
    /**
     * This method is called when an action is called on GUI pairDevice Button
     * and runs the bluetooth search for devices
     * then populates the ListView with device IDs
     */
    @FXML
    private void pairDevice(){
        //PairDevice.getDevices();
    }
     
}
