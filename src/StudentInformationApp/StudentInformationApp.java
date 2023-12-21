package exercise1;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Pos;


public class StudentInformationApp extends Application
{
	
    private ComboBox<String> programComboBox;
    private ComboBox<String> courseComboBox;
    private ListView<String> selectedCoursesListView;
    private TextArea displayTextArea;
    private GridPane pane;
    private CheckBox studentCouncilCheckBox;
    private CheckBox volunteerWorkCheckBox;
    private RadioButton rbCS;
    private RadioButton rbBusiness;

	
	  @Override // Override the start method in the Application class
	  public void start(Stage primaryStage) {		  
	    // Create a pane and set its properties
	    pane = new GridPane();
	    //pane.setAlignment(Pos.CENTER);
	    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
	    pane.setHgap(5.5);
	    pane.setVgap(5.5);
	    
	    // Place nodes in the pane
	    pane.add(new Label("Name:"), 0, 0);
	    pane.add(new TextField(), 1, 0);
	    pane.add(new Label("Address:"), 0, 1); 
	    pane.add(new TextField(), 1, 1);
	    pane.add(new Label("Province:"), 0, 2);
	    pane.add(new TextField(), 1, 2);
	    pane.add(new Label("City:"), 0, 3); 
	    pane.add(new TextField(), 1, 3);
	    pane.add(new Label("Postal Code:"), 0, 4);
	    pane.add(new TextField(), 1, 4);
	    pane.add(new Label("Phone Number:"), 0, 5); 
	    pane.add(new TextField(), 1, 5);
	    pane.add(new Label("Email:"), 0, 6);
	    pane.add(new TextField(), 1, 6);
	    
	    
	    studentCouncilCheckBox = new CheckBox("Student Council");
	    volunteerWorkCheckBox = new CheckBox("Volunteer Work");
	  VBox checkBoxesBox = new VBox(500);
	    checkBoxesBox.getChildren().addAll(studentCouncilCheckBox,volunteerWorkCheckBox);			    
	    
	    
	    
	    rbCS = new RadioButton("Computer Science");
	    rbBusiness = new RadioButton("Business");
        ToggleGroup majorToggleGroup = new ToggleGroup();
        rbCS.setToggleGroup(majorToggleGroup);
        rbBusiness.setToggleGroup(majorToggleGroup);
	    HBox radioButtonsBox = new HBox(20);
	    radioButtonsBox.getChildren().addAll(rbCS, rbBusiness);

	    //programComboBox = new ComboBox<>();
	    //programComboBox.getItems().addAll("Computer Science", "Business");
	    
	    courseComboBox = new ComboBox<>();
	    courseComboBox.setDisable(true);
	    
	    rbCS.setOnAction(e -> {
		    if (rbCS.isSelected()) {
		    	courseComboBox.getItems().setAll("Python", "C#", "Java");
		    }
		    else if (rbBusiness.isSelected()) {
		    courseComboBox.getItems().setAll("Finance", "Management", "Communication");
		    }
		    courseComboBox.setDisable(false);
		  });
	    
	    rbBusiness.setOnAction(e -> {
		    if (rbCS.isSelected()) {
		    	courseComboBox.getItems().setAll("Python", "C#", "Java");
		    }
		    else if (rbBusiness.isSelected()) {
		    courseComboBox.getItems().setAll("Finance", "Management", "Communication");
		    }
		    courseComboBox.setDisable(false);
		  });
	    	
	    //programComboBox.setOnAction(e -> {
	    //if (programComboBox.getValue().equals("Computer Science")) {
	    	//courseComboBox.getItems().setAll("Python", "C#", "Java");
	    //}
	    //else if (programComboBox.getValue().equals("Business")) {
	    	//courseComboBox.getItems().setAll("Finance", "Management", "Communication");
	    //}
	    //courseComboBox.setDisable(false);
	  //});
	    
	    VBox programBox = new VBox(5);
        //programBox.getChildren().addAll(new Label("Program:"), programComboBox, new Label("Course:"), courseComboBox);
	    
        Button addButton = new Button("Add");
        Button removeButton = new Button("Remove");
        addButton.setOnAction(e -> addCourse());
        removeButton.setOnAction(e -> removeCourse());
        HBox buttonsBox = new HBox(5);
        buttonsBox.getChildren().addAll(addButton, removeButton);
  
        
        selectedCoursesListView = new ListView<>();
        VBox coursesBox = new VBox(5);
        coursesBox.getChildren().addAll(radioButtonsBox,courseComboBox,selectedCoursesListView, buttonsBox);
            
   
    displayTextArea = new TextArea();
    displayTextArea.setEditable(false);
   
    
    ScrollPane scrollPane = new ScrollPane(displayTextArea);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    
    VBox rightBox = new VBox(10);
    rightBox.getChildren().addAll(programBox, coursesBox, scrollPane);

    
    Button displayButton = new Button("Display");
    displayButton.setOnAction(e -> displaySelectedInformation());
    
    VBox displayBox = new VBox(5);
    displayBox.getChildren().addAll(displayButton, scrollPane);
    displayBox.setAlignment(Pos.CENTER);

 
    
    
    
    BorderPane rootPane = new BorderPane();
    rootPane.setLeft(pane);
    rootPane.setCenter(new VBox(150, studentCouncilCheckBox, volunteerWorkCheckBox));
    
    rootPane.setRight(rightBox);
    rootPane.setBottom(displayBox);
    BorderPane.setAlignment(displayBox, javafx.geometry.Pos.BOTTOM_CENTER);
    
     //Create a scene and place it in the stage
     Scene scene = new Scene(rootPane, 700, 400);
     primaryStage.setTitle("Student Information"); // set the stage title
     primaryStage.setScene(scene);  // Place the scene in the stage
     primaryStage.show(); //display the stage
  }

private void addCourse() {
    String course = courseComboBox.getValue();
    if (course != null && !selectedCoursesListView.getItems().contains(course)) {
        selectedCoursesListView.getItems().add(course);
    }
}

private void removeCourse() {
    String course = selectedCoursesListView.getSelectionModel().getSelectedItem();
    if (course != null) {
        selectedCoursesListView.getItems().remove(course);
    }
}

private void displaySelectedInformation() {
    StringBuilder sb = new StringBuilder();
    sb.append("Name: ").append(((TextField) pane.getChildren().get(1)).getText()).append("\n");
    sb.append("Address: ").append(((TextField) pane.getChildren().get(3)).getText()).append("\n");
    sb.append("Province: ").append(((TextField) pane.getChildren().get(5)).getText()).append("\n");
    sb.append("City: ").append(((TextField) pane.getChildren().get(7)).getText()).append("\n");
    sb.append("Postal Code: ").append(((TextField) pane.getChildren().get(9)).getText()).append("\n");
    sb.append("Phone Number: ").append(((TextField) pane.getChildren().get(11)).getText()).append("\n");
    sb.append("Email: ").append(((TextField) pane.getChildren().get(13)).getText()).append("\n");
    sb.append("Student Council: ");

    if (studentCouncilCheckBox.isSelected()) {
        sb.append("Yes").append("\n");
    } else {
        sb.append("No").append("\n");
    }
    sb.append("Volunteer Work: ");
    if (volunteerWorkCheckBox.isSelected()) {
        sb.append("Yes").append("\n");
    } else {
        sb.append("No").append("\n");
    }
    sb.append("Major: ");
    if (rbCS.isSelected()) {
        sb.append("Computer Science").append("\n");
    } else if (rbBusiness.isSelected()) {
        sb.append("Business").append("\n");
    }
    sb.append("Course: \n");//.append(courseComboBox.getValue()).append("\n");
    
    ObservableList<String> items = selectedCoursesListView.getItems();
    for (String item : items) {
    	sb.append(item).append("\n");
    }

    displayTextArea.setText(sb.toString());
}
	



  public static void main(String[] args) {
    launch(args);
  }
}
