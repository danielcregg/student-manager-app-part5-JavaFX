# Student Manager App - Part 5: JavaFX

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-007396?style=flat-square&logo=java&logoColor=white)
![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)

A JavaFX desktop application for managing student records with a graphical user interface. This is Part 5 of a multi-part Student Manager series, evolving the console-based application into a full GUI with buttons, text fields, and file dialogs for adding, removing, viewing, and persisting student data.

## Overview

This project wraps the Student Manager logic in a JavaFX GUI built with `GridPane` layout. Users interact with the application through buttons and text fields rather than the command line. The application supports loading student records from serialized binary files (`.ser`), adding and removing students via form inputs, displaying the student list in a text area, and saving data back to binary files using a file chooser dialog.

## Features

- **JavaFX GUI** -- Full graphical interface with `GridPane`, `Button`, `TextField`, `TextArea`, and `FileChooser`
- **Load from Binary File** -- Open a `.ser` file via file chooser to populate the student list
- **Add Students** -- Enter student ID, first name, and age through text fields
- **Remove Students** -- Delete a student by entering their student ID
- **View Student List** -- Display all students in a formatted text area
- **Show Student Count** -- View the total number of students at any time
- **Save to Binary File** -- Serialize the current student list to a `.ser` file via save dialog
- **Input Validation** -- Validates student ID format (`G00XXXXXX`), name, and age before adding

## Prerequisites

- **Java JDK** 11 or higher with **JavaFX** libraries
- A Java IDE with JavaFX support (Eclipse, IntelliJ IDEA, VS Code) or command-line access with JavaFX SDK

## Getting Started

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/danielcregg/student-manager-app-part5-JavaFX.git
   cd student-manager-app-part5-JavaFX
   ```

2. Open the project in your preferred Java IDE with JavaFX configured, or compile from the command line.

### Usage

**Compile and run from the command line (with JavaFX SDK):**

```bash
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -d bin src/ie/atu/studentmanagerpackage/*.java
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -cp bin ie.atu.studentmanagerpackage.Main
```

Replace `/path/to/javafx-sdk/lib` with your actual JavaFX SDK path. The application window will open, allowing you to manage student records through the GUI.

## Tech Stack

- **Language:** Java
- **GUI Framework:** JavaFX (GridPane, Scene, Stage)
- **Serialization:** `java.io.Serializable`, `ObjectOutputStream`, `ObjectInputStream`
- **I/O:** `java.io` (BufferedReader, BufferedWriter, FileReader, FileWriter)
- **Dev Environment:** VS Code Dev Containers

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
