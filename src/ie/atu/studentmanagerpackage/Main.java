package ie.atu.studentmanagerpackage;

import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * The Main class holds the main method.
 */
public class Main extends Application {

	StudentManager sm = new StudentManager(); // Used for managing students

	@Override
	public void start(Stage primaryStage) {

		// Createa all requred nodes
		GridPane gridPane1 = new GridPane(); // Create gridpane node to use as root node of scene
		// Create child nodes
		Text txtHeader = new Text("Please select an option below:"); // Create Text node for top of scene 1
		// Create button and file chooser for loading students from CSV file 
		
        Button btnSelectCSVFile = new Button("Get Students from CSV File");
		FileChooser fileChooser = new FileChooser();
		// Add Student Button and text fields
		Button btnAddStudent = new Button("Add Student");
		TextField tfStudentID = new TextField();
		tfStudentID.setPromptText("Student ID");
		TextField tfStudentFirstName = new TextField();
		tfStudentFirstName.setPromptText("First Name");
		TextField tfStudentAge = new TextField();
		tfStudentAge.setPromptText("Age");
		Button btnDelStudent = new Button("Delete Student");
		TextField tfDelStudent = new TextField();
		tfDelStudent.setPromptText("Student ID");
		// Show total number of students
		Button btnShowTotal = new Button("Show Total Students");
		Button btnShowStudentList = new Button("Show Student List");
		Button btnSave = new Button("Save List to binary file");
		FileChooser fileChooser2 = new FileChooser();
		// Add Quit button
		Button btnQuit = new Button("Quit");
		// Create TextArea node for bottom of scene 1 to display output
		TextArea taMyOutput = new TextArea();
		
		// Adding and arranging all the nodes in the grid - add(node, column, row)
		gridPane1.add(txtHeader, 0, 0);
		gridPane1.add(btnSelectCSVFile, 0, 1);	
		gridPane1.add(btnAddStudent, 0, 2);
		gridPane1.add(tfStudentID, 1, 2);
		gridPane1.add(tfStudentFirstName, 2, 2);
		gridPane1.add(tfStudentAge, 4, 2);
		gridPane1.add(btnDelStudent, 0, 3);
		gridPane1.add(tfDelStudent, 1, 3);
		gridPane1.add(btnShowTotal, 0, 4);
		gridPane1.add(btnShowStudentList, 0, 5);
		gridPane1.add(btnSave, 0, 6);
		gridPane1.add(btnQuit, 0, 7);
		gridPane1.add(taMyOutput, 0, 8, 5, 1);

		// Add action to button to load students from CSV file
		btnSelectCSVFile.setOnAction(e -> {
			// Set initial directory to current directory
			fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
			// Choose file
			File studentCSVFile = fileChooser.showOpenDialog(primaryStage);
			//File studentCSVFile = new File(tfLoadStudentFilePath.getText());
			sm.readStudentDataFromCSVFile(studentCSVFile.getAbsolutePath());
			// sm.saveStudentManagerObjectToFile(studentObjectsFile);
			//sm = sm.loadStudentManagerObjectFromFile(studentObjectsFile);
			if (sm == null) {
				taMyOutput.setText("ERROR: File path does not exist\n" + studentCSVFile.getAbsolutePath());
			} else {
				taMyOutput.setText("Students loaded successfully from:\n" + studentCSVFile.getAbsolutePath());
			}
        });

		// Add action to button to save students from binary file
		btnSave.setOnAction(e -> {
			// Set initial directory to current directory
			fileChooser2.setInitialDirectory(new File(System.getProperty("user.dir")));
			//Opening a dialog box
			sm.writeStudentManagerObjectToFile(fileChooser.showSaveDialog(primaryStage).getAbsolutePath());
		});

		// Add Student button action
		btnAddStudent.setOnAction(e -> {
			// If any of the Student fields are empty print prompt message
			try { 
				if (Student.isValid(tfStudentID.getText(), tfStudentFirstName.getText(), Integer.parseInt(tfStudentAge.getText())) == false) {
					taMyOutput.setText("Please enter valid Student details\n");
				} else {
					// Create new Student with information in text fields
				
					// Add student to student list
					if (sm.addStudentToList(tfStudentID.getText(), tfStudentFirstName.getText(), Integer.parseInt(tfStudentAge.getText()))){
						taMyOutput.setText("Student added to list successfully\n");
					} else {
						taMyOutput.setText("Student not added to list\n");
					}
					// Clear input fields
					tfStudentID.clear();
					tfStudentFirstName.clear();
					tfStudentAge.clear();
				}
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
				taMyOutput.setText("Please enter a number for Age");
			}
		});

		// Delete Student button action
		btnDelStudent.setOnAction(e -> {

			if (tfDelStudent.getText().trim().equals("")) { // If text field is empty
				taMyOutput.setText("Please enter the Student Number you want to delete");
			} else {
				boolean status;
				status = sm.removeStudentFromList(tfDelStudent.getText());
				if (status == true) {
					taMyOutput.setText(tfDelStudent.getText() + " has been removed from the student list!");
					tfDelStudent.clear();
				} else {
					taMyOutput.setText("Student " + tfDelStudent.getText() + " not found\n");
					taMyOutput.appendText("No student deleted!");
					tfDelStudent.clear();
				}
			}

		});

		// Show total number of students
		btnShowTotal.setOnAction(e -> {
			taMyOutput.setText("Current Total Students: " + sm.getStudentList().size());
		});

		// Show list of students
		btnShowStudentList.setOnAction(e -> {

			taMyOutput.setText(sm.listAllStudnets());

		});

		// Quit button action
		btnQuit.setOnAction(e -> Platform.exit());

		// Create scene and add the root node i.e. the gridpane
		Scene scene1 = new Scene(gridPane1, 600, 450);
		// Preparing the Stage (i.e. the container of any JavaFX application)
		// Set Stage Title
		primaryStage.setTitle("Student Manager Application");
		// Setting the scene to Stage
		primaryStage.setScene(scene1);
		// Displaying the stage
		primaryStage.show();

	}// End Start Method

	public static void main(String[] args) {
		launch(args);
	}

	// // Main method containing print statement.
	// public static void main(String[] args) {
	//
	//// // Create menu object
	//// Menu menuObject = new Menu();
	//// // Run menu start method
	//// menuObject.start();
	// boolean addStatus;
	//
	// // Add studnets from csv file
	// StudentManager stuManObj = new StudentManager();
	//// File studentCSVFile = new File(".\\resources\\students.csv");
	// File studentManagerByteFile = new File(".\\resources\\students.ser");
	//// stuManObj.loadStudentsFromCSVFile(studentCSVFile);
	// stuManObj =
	// stuManObj.loadStudentManagerObjectFromFile(studentManagerByteFile);
	// System.out.println(stuManObj.findTotalStudents());
	//
	// // Add one more student manually and write out all students to csv file
	// Student studentObj1 = new Student("G00123461", "Jim", "Jones", 1);
	// stuManObj.addStudent(studentObj1);
	// System.out.println(stuManObj.findTotalStudents());
	//// stuManObj.saveStudentsToCSVFile(studentCSVFile);
	// stuManObj.saveStudentManagerObjectToFile(studentManagerByteFile);
	//
	//// Student studentObject1 = new Student("G00123458", "Alf", "Stewart", 3);
	//// Student studentObject2 = new Student("G00123459", "Sally", "Fletcher", 4);
	//// Student studentObject3 = new Student("G00123459", "Sally", "Fletcher", 4);
	////
	//// addStatus = stuManObj.addStudent(studentObject1);
	//// System.out.println(addStatus);
	//// addStatus = stuManObj.addStudent(studentObject2);
	//// System.out.println(addStatus);
	//// addStatus = stuManObj.addStudent(studentObject3);
	//// System.out.println(addStatus);
	//
	// // boolean removeStatus = stuManObj.removeStudent(studentObject1);
	//
	// // System.out.println(removeStatus);
	//
	// System.out.println("The End");
	//
	// } // End main method

} // End Main Class
