package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Classroom;

public class ClassroomGUI {
	
	//main-pane components
	@FXML
    private Pane mainContainer;
	
	
	//login components
    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;
    
    
    //create-acount components   
    @FXML
    private TextField txtCreateUsername;

    @FXML
    private PasswordField txtCreatePassword;

    @FXML
    private TextField txtFilePhoto;

    @FXML
    private RadioButton radioMale;

    @FXML
    private RadioButton radioFemale;

    @FXML
    private RadioButton radioOther;

    @FXML
    private CheckBox checkSoftware;

    @FXML
    private CheckBox checkTelematic;

    @FXML
    private CheckBox checkIndustrial;

    @FXML
    private DatePicker dateBirthday;

    @FXML
    private ComboBox<String> comboBrowsers;
    
    
    //acount-list components
    @FXML
    private TableView<?> accountList;

    @FXML
    private TableColumn<?, ?> columUsername;

    @FXML
    private TableColumn<?, ?> columGender;

    @FXML
    private TableColumn<?, ?> columCareer;

    @FXML
    private TableColumn<?, ?> columBirthday;

    @FXML
    private TableColumn<?, ?> columBrowser;

    @FXML
    private Label labelUsername;

    @FXML
    private ImageView imgUser;
    
    //Classroom object
    private Classroom classroom;
    
    
    public ClassroomGUI(Classroom classroom) { //Constructor
    	this.classroom = classroom;
    }
    
    public void startLogin() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
    	fxmlLoader.setController(this);
    	
    	Parent root = fxmlLoader.load();
    	mainContainer.getChildren().clear();
    	mainContainer.getChildren().setAll(root);
    }
    
    //login events
    @FXML
    void LogIn(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
    	fxmlLoader.setController(this);
    	
    	Parent root = fxmlLoader.load();
    	mainContainer.getChildren().clear();
    	mainContainer.getChildren().setAll(root);
    }
    
    @FXML
    void SignUp(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("create-account.fxml"));
    	fxmlLoader.setController(this);
    	
    	Parent root = fxmlLoader.load();
    	mainContainer.getChildren().clear();
    	mainContainer.getChildren().setAll(root);
    	
    	comboBrowsers.getItems().addAll("Firefox", "Chrome", "Edge", "Safari", "Opera", "Thor");
    }
    
    
    //create-account events
    @FXML
    void browsePhoto(ActionEvent event) {
		
    }

    @FXML
    void createAccount(ActionEvent event) {
    	
    }

    @FXML
    void signIn(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
    	fxmlLoader.setController(this);
    	
    	Parent root = fxmlLoader.load();
    	mainContainer.getChildren().clear();
    	mainContainer.getChildren().setAll(root);
    }
    
    
    //acount-list events
    @FXML
    void logOut(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
    	fxmlLoader.setController(this);
    	
    	Parent root = fxmlLoader.load();
    	mainContainer.getChildren().clear();
    	mainContainer.getChildren().setAll(root);
    }
}

