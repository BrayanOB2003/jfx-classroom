package ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Classroom;
import model.UserAccount;

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
    private TableView<UserAccount> accountList;

    @FXML
    private TableColumn<UserAccount, String> columUsername;

    @FXML
    private TableColumn<UserAccount, String> columGender;

    @FXML
    private TableColumn<UserAccount, String> columCareer;

    @FXML
    private TableColumn<UserAccount, String> columBirthday;

    @FXML
    private TableColumn<UserAccount, String> columBrowser;

    @FXML
    private Label labelUsername;

    @FXML
    private ImageView imgUser;
    
    //Classroom object
    private Classroom classroom;
    
    @SuppressWarnings("unused")
	private int contImage;
    
    public ClassroomGUI(Classroom classroom) { //Constructor
    	this.classroom = classroom;
    	contImage = 0;
    }
    
    public void loadLogin() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
    	fxmlLoader.setController(this);
    	
    	Parent root = fxmlLoader.load();
    	mainContainer.getChildren().clear();
    	mainContainer.getChildren().setAll(root);
    }
    
    //login events
    
    public void setInfoTableView() {
    	ObservableList<UserAccount> accounts = FXCollections.observableArrayList(classroom.getUserAcounts());
    	
    	accountList.setItems(accounts);
    	columUsername.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("username"));
		columGender.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("gender"));
		columCareer.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("career"));
		columBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("birthday"));
    	columBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("favoriteBrowser"));
    }
    
    @FXML
    void LogIn(ActionEvent event) throws IOException {
    	
    	boolean logIn = false;
    	
    	if(classroom.getUserAcounts().size() != 0) {
    		
    		for(int i = 0; i < classroom.getUserAcounts().size(); i++) {
        		
        		if(classroom.getUserAcounts().get(i).getUsername().equals(txtUserName.getText()) && 
        				classroom.getUserAcounts().get(i).getPassword().equals(String.valueOf(txtPassword.getCharacters()))) {
        			
            		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
                	fxmlLoader.setController(this);
                	
                	Parent root = fxmlLoader.load();
                	mainContainer.getChildren().clear();
                	mainContainer.getChildren().setAll(root);
            		
                	labelUsername.setText(classroom.getUserAcounts().get(i).getUsername());
                	imgUser.setImage(new Image(classroom.getUserAcounts().get(i).getProfileImage()));
                	setInfoTableView();
                	
                	logIn = true;
                	break;
            	}
    		} 	
    		
    		if(!logIn) {
        		Alert alert = new Alert(AlertType.WARNING);
        		alert.setTitle("Log In Incorrect");
        		alert.setHeaderText(null);
        		alert.setContentText("The username and password given are incorrect");
        		alert.showAndWait();
        	}
    		
    	} else {
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("No accounts");
    		alert.setHeaderText(null);
    		alert.setContentText("There are no accounts created yet");
    		alert.showAndWait();
    		
    	}	
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
    	
    	try {
	    	Stage stage = new Stage();
	    	FileChooser fileChooser = new FileChooser();
	    	fileChooser.setTitle("Open Resource File");
	    	//fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "PNG", "jpg", "png"));
	    	
	    	File file = fileChooser.showOpenDialog(stage);
	    	txtFilePhoto.setText(file.getPath());
		} catch (Exception e) {
			
		} 	
    }
    
    public String copyImage() {
    	
    	String image = "";
    	
    	if(!txtFilePhoto.getText().isEmpty() && txtFilePhoto.getText().contains(".")) {
    		try {
    			String[] prt = txtFilePhoto.getText().split("\\."); 
				Path origin = Paths.get(txtFilePhoto.getText());				
				Path destination = Paths.get("images/imgPhotoProfile" + txtCreateUsername.getText() + "." + prt[1]);
				System.out.print(destination.toAbsolutePath().toString());
				
				Files.copy(origin, destination, StandardCopyOption.REPLACE_EXISTING);
				
				image = destination.toAbsolutePath().toUri().toString();
				
			}catch(IOException e) {
				
			}finally {
				
			}
    	}
    	
		return image;
    	
    	
    }
    
    public int checkGender() {
    	
    	int selected = -1;
    	
    	if(checkSoftware.isSelected()) {
    		selected = 0;
    	} else if(checkIndustrial.isSelected()) {
    		selected  = 1;
    	} else if(checkTelematic.isSelected()) {
    		selected = 2;
    	}
    	 
    	return selected;
    }

    public String selectCareer() {
    	String selectCareer = "";
    	
    	if(checkSoftware.isSelected()) {
    		selectCareer += "Software Engeneering" + "\n";
    	} if(checkIndustrial.isSelected()) {
    		selectCareer += "Industrial Engeneering" + "\n";
    	} if(checkTelematic.isSelected()) {
    		selectCareer += "Telematic Engeneering" + "\n";
    	}
    	
		return selectCareer;
    }
    
    @FXML
    void createAccount(ActionEvent event) {
    	
    	String username = txtCreateUsername.getText();
    	String password = String.valueOf(txtCreatePassword.getCharacters());
    	String image = copyImage();
    	int genderIndex = checkGender();
    	String selectCareer = selectCareer();
    	String date = String.valueOf(dateBirthday.getValue());
    	int browserIndex = comboBrowsers.getSelectionModel().getSelectedIndex();
    	
    	if(!username.equals("") && !password.equals("") && !image.equals("") && genderIndex != -1 && !selectCareer.equals("") &&
    			!date.equals("") && browserIndex != -1) {
    		
    		txtCreateUsername.setText("");
    		txtCreatePassword.setText("");
    		txtFilePhoto.setText("");
    		radioMale.setSelected(false);
    		radioFemale.setSelected(false);
    		radioOther.setSelected(false);
    		checkIndustrial.setSelected(false);
    		checkSoftware.setSelected(false);
    		checkTelematic.setSelected(false);
    		dateBirthday.getEditor().clear();
    		comboBrowsers.getSelectionModel().select("");
    		
    		classroom.addUserAccount(username, password, image, genderIndex, selectCareer, date, browserIndex);
    		contImage++;
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Create account");
    		alert.setHeaderText(null);
    		alert.setContentText("The account was created successfully");
    		alert.showAndWait();
    		
    	} else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Incomplete profile!");
    		alert.setHeaderText(null);
    		alert.setContentText("There are unfilled fields");
    		alert.showAndWait();
    	}
    	
    }

    @FXML
    void selectFemaleRadioButton(ActionEvent event) {
    	radioMale.setSelected(false);
    	radioOther.setSelected(false);
    }

    @FXML
    void selectMaleRadioButton(ActionEvent event) {
    	radioFemale.setSelected(false);
    	radioOther.setSelected(false);
    }

    @FXML
    void selectOtherRadioButton(ActionEvent event) {
    	radioMale.setSelected(false);
    	radioFemale.setSelected(false);
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

