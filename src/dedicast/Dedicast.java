/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dedicast;

import filesmodel.SoundModel;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.*;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.Duration;
/**
 *
 * @author mombju
 */
public class Dedicast extends Application {
    
    private MediaPlayer  mediaPlayer ;
    private Point2D  previousLocation;
    private Point2D anchorPoint;
    private ChangeListener<Duration> progressListener;
    private InvalidationListener StatusListener;
    private static Stage PRIMARY_STAGE;
    
    private static final String  STOP_BUTTON_ID = "stop-button";
    private static final String PLAY_BUTTON_ID = "play-button";
    private static final String PAUSE_BUTTON_ID = "pause-button";
    private static final String CLOSE_BUTTON_ID = "close-button";
    private static final String VIS_CONTAINER_ID = "vis-container";
    private static final String SEEK_POS_SLIDER_ID = "play-button";
    private static final String APP_AREA_ID = "app-area";
    private static final String MEDIA_VIEW_ID = "media-view";
    private static final String LIBRARY_PANEL_ID ="library-panel";
    private static final String MENU_BAR ="menu-bar";
    private static final String TOOL_BAR ="tool-bar";
    private static final String REDUCE_BUTTON_ID = "reduce-button" ;
    private static final String FULL_SCREEN_BUTTON_ID = "full-screen-button" ;
    private static final String CONTROL_SIDE_BUTTON = "control-sideBar";
    private static final String SIDE_BAR = "SideBar";
   private static final String LIST_AUDIO_ID ="list-audio";
   private static final String TAB_AUDIO_ID ="tab-audio";
   private static final String LIST_VIDEO_ID ="list-video";
   private static final String TAB_VIDEO_ID ="tab-video";
    private static  TabPane vitrine;
   private static final String TAB_PANE = "tab-pane";
    
   private final Image buttonPlayImage = new Image(Dedicast.class.getResourceAsStream("play.png"));
   private final Image buttonPauseImage = new Image(Dedicast.class.getResourceAsStream("pause.png"));
    private final Image buttonBackwardImage = new Image(Dedicast.class.getResourceAsStream("backWard.png"));
    private final Image buttonForwardImage = new Image(Dedicast.class.getResourceAsStream("forward.png"));
    private final Image buttonLectureAleatoireImage = new Image(Dedicast.class.getResourceAsStream("buttonLectureAleatoire1.png"));
    private final Image buttonLectureContinueImage = new Image(Dedicast.class.getResourceAsStream("buttonLectureContinue1.png"));
    private final Image buttonLectureContinueImage11 = new Image(Dedicast.class.getResourceAsStream("buttonLectureContinue11.png"));
   
    private final Image buttonFullScreenImage = new Image(Dedicast.class.getResourceAsStream("buttonFullScreen.png"));
    private final Image buttonRestoreSizeImage = new Image(Dedicast.class.getResourceAsStream("restoreSize.png"));
    
    public static final String TITRE_COLUMN_NAME = "titre";
  public static final String ARTISTE_COLUMN_NAME = "artist";
  
   public static final String TITRE_AUDIO_COLUMN_NAME = "titre-audio";
  public static final String ARTISTE_AUDIO_COLUMN_NAME = "artist-audio";
  public static final String ALBUM_AUDIO_COLUMN_NAME = "album-audio";
  public static final String GENRE_AUDIO_COLUMN_NAME = "genre-audio";
  
  
 public   ImageView imageViewPlayPause = new ImageView(buttonPlayImage);
    ImageView imageViewBackward = new ImageView(buttonBackwardImage);
    ImageView imageViewForward = new ImageView(buttonForwardImage);
    ImageView imageViewLectureAleatoire = new ImageView(buttonLectureAleatoireImage);
    ImageView imageViewLectureContinue = new ImageView(buttonLectureContinueImage);
    ImageView imageViewLectureContinue11 = new ImageView(buttonLectureContinueImage11);
    
    
    ImageView imageViewRestoreSize = new ImageView(buttonRestoreSizeImage);
    
    
    @Override
    public void start(Stage primaryStage) {
   
//instantiate  stage
   
   PRIMARY_STAGE = primaryStage;
   PRIMARY_STAGE.initStyle(StageStyle.DECORATED);
   PRIMARY_STAGE.centerOnScreen();
  
   
    Group total = new Group();
    Group root = new Group();
    root.getChildren().add(total);
    Scene scene = new Scene(root,800,600);
    
    
    // load javafx stle sheet
    scene.getStylesheets().addAll(getClass().getResource("player-audio.css").toExternalForm());
    PRIMARY_STAGE.setScene(scene);
    
    
    

    //application area
final    Pane applicationArea = createApplicationArea();
   
    // create menu Bar
     Node menuBar = createMenuBar();
    
       // Create a media view to display video 
final    MediaView  mediaView = createMediaView();
    
    //Create a side bar for contextual object
     Node SideBarContent = createSideBarContent();
     
     
     SideBar sideBar = new SideBar(200,SideBarContent);
     
     Button controlButton = sideBar.getControlButton();
     VBox.setVgrow(SideBarContent, Priority.ALWAYS);
    
      sideBar.setId(SIDE_BAR);
    
    //container for random circles bouncing out 
    Node visContainer = new Group();
    visContainer.setId(VIS_CONTAINER_ID);
     
    applicationArea.getChildren().add(visContainer);
    
//Create the button panel
    Node tools = createButtonPanel();
    

   
    
    //create Node the library  panel
    Node libraryPanel = createLibraryPanel();
    

    //create a reduce button
    Node fullScreenButton = createFullScreenButton();
    
   //create a reduce button
    Node reduceButton = createReduceButton();
    
    //create the close button
    Node closeButton = createCloseButton();
     
    
    total.getChildren().addAll( applicationArea, menuBar,
                              controlButton,
                               sideBar , tools ,
                               libraryPanel , fullScreenButton,
                               reduceButton , closeButton);
    total.getChildren().add(1, mediaView);

    //Initialise le full screen mode
    initFullScreenMode();

    // Initialiser la fenetre au mouvement via la souris
    initMovablePlayer();
    
    
    primaryStage.show();
       
    }

/**
Create the node containing the audioplayer's
* stop , pause & play ,foreward ,backward 
* lecture Continue , Lecture aleatoire ... buttons
* 
* 
* @return Node A button panel having play ,
* pause and stop buttons
**/

private Node createButtonPanel(){
    Scene scene =PRIMARY_STAGE.getScene();
     // create toolbar @return (ToolBar controls)
    
    ToolBar controls = new ToolBar();
    controls.setStyle("-fx-background-color:coral;");
            
    

      // play button
     
 Button playPauseButton = new Button(null, imageViewPlayPause);
  playPauseButton.setId("playPauseButton");
  playPauseButton.setStyle("-fx-background-color:transparent;");
  playPauseButton.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent arg0) {
     
      if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
        mediaPlayer.pause();
        imageViewPlayPause.setImage(buttonPlayImage);
      } else {
        mediaPlayer.play();
        imageViewPlayPause.setImage(buttonPauseImage);
      }
} });



controls.getItems().add(playPauseButton);

Button backward = new Button();
backward.setGraphic(imageViewBackward);
backward.setPrefSize(20 , 20);
backward.setStyle("-fx-translate-x:5;");
backward.setStyle("-fx-background-color:coral;");
controls.getItems().add(backward);
backward.setOnMousePressed(new EventHandler<MouseEvent>() {

        public void handle(MouseEvent mouseEvent) {
            if (mediaPlayer != null){
                
            }       }
    });
    
Button foreward = new Button();
foreward.setGraphic(imageViewForward);
foreward.setPrefSize(20 , 20 );
foreward.setStyle("-fx-translate-x:5;");
foreward.setStyle("-fx-background-color:coral;");
controls.getItems().add(foreward);
foreward.setOnMousePressed(new EventHandler<MouseEvent>() {

        public void handle(MouseEvent mouseEvent) {
            if (mediaPlayer != null){
                final MediaPlayer curPlayer = mediaPlayer;
        curPlayer.currentTimeProperty().removeListener(progressListener);
        curPlayer.stop();
                
        TabPane  curPane = (TabPane) PRIMARY_STAGE.getScene().lookup("#"+TAB_PANE);
        Tab activTab = curPane.getSelectionModel().getSelectedItem();
               
        List<MediaPlayer> activList = (List<MediaPlayer>) activTab.getContent();
       
               
        MediaPlayer nextPlayer =activList.get(activList.indexOf(curPlayer)+1 % activList.size());
        nextPlayer.play(); 
            }       }
    });    
    controls.getItems().add(new Separator());
   
   
   
// create slider control : Progress and seek position slider 

Slider progressSlider = new Slider(0,100,1);
progressSlider.setId(SEEK_POS_SLIDER_ID);
progressSlider.valueProperty()
       .addListener((observable)->{
       if(progressSlider.isValueChanging()){
       // must check if media is paused before seeking
 if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED){
 
 //convert seconds to millis
     double dur = progressSlider.getValue() * 1000;
     mediaPlayer.seek(Duration.millis(dur));
     
 }
       }
       
       });//addListenr()
//update slider as media progress
    progressListener = (observable , old_value , new_value)->
            progressSlider.setValue(new_value.toSeconds());


 progressSlider.setStyle("-fx-translate-x:3;");
controls.getItems().add(progressSlider);
controls.getItems().add(new Separator());

Button lectAleat = new Button();
lectAleat.setGraphic(imageViewLectureAleatoire);
lectAleat.setPrefSize(20, 20);
lectAleat.setStyle("-fx-translate-x:3;");
lectAleat.setStyle("-fx-background-color:coral;");
controls.getItems().add(lectAleat);

Button lectCont = new Button();
lectCont.setGraphic(imageViewLectureContinue);
lectCont.setPrefSize(20 , 20);
lectCont.setStyle("-fx-translate-x:3;");
lectCont.setStyle("-fx-background-color:coral;");
controls.getItems().add(lectCont);

// Add the volume label
        Label volumeLabel = new Label("Vol");
         volumeLabel.setStyle("-fx-text-fill:green");
         volumeLabel.setStyle("-fx-translate-x:3;");
        controls.getItems().add(volumeLabel);
      
// Add Volume slider

        Slider   volumeSlider = new Slider(0,100,60);
        volumeSlider.setPrefWidth(100);
       
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (volumeSlider.isValueChanging()) {
                    mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
                }
            if (!volumeSlider.isValueChanging()) {
                            volumeSlider.setValue((int) Math.round(mediaPlayer.getVolume() * 100));
                        }
            }
        });
     volumeSlider.setStyle("-fx-translate-x:3;");
     volumeSlider.setOrientation(Orientation.HORIZONTAL);
        controls.getItems().add(volumeSlider);

        // creation bouton de lecture aleatoire
        



// HBox container for "pocketing" controls: @ return (HBox "tools")
    
    HBox  tools = new HBox(5.0);
    tools.setAlignment(Pos.CENTER);
    tools.setPrefWidth(600);
    tools.setPrefHeight(25);
    tools.setId(TOOL_BAR);
    tools.setStyle("-fx-background-color:coral;");
    tools.setStyle("-fx-border-color:green;");
       
    tools.getChildren().addAll(controls);
    tools.setHgrow(controls ,Priority.ALWAYS);
    
      
   
   tools.setLayoutX(0);
   tools.translateYProperty().bind(scene.heightProperty().subtract(250));
   
  
   
return tools;
}



private void playMedia(MediaPlayer mediaPlayer){

Scene scene = PRIMARY_STAGE.getScene();

if (mediaPlayer != null){
    
    mediaPlayer.setOnPaused(null);
    mediaPlayer.setOnPlaying(null);
    mediaPlayer.setOnReady(null);
    mediaPlayer.currentTimeProperty()
               .removeListener(progressListener);
    mediaPlayer.setAudioSpectrumListener(null);
   
}


mediaPlayer.play();   
mediaPlayer.currentTimeProperty()
           .addListener(progressListener);


mediaPlayer.statusProperty().addListener(StatusListener);


// as the media is playing move the slider in progress
Slider progressSlider = (Slider) scene.lookup("#"+SEEK_POS_SLIDER_ID);
           progressSlider.setValue(0);
           progressSlider.setMax(mediaPlayer.getMedia()
                                        .getDuration()
                                        .toSeconds());
    




// back to the beginning

mediaPlayer.setOnEndOfMedia(()->{

//change button to play and rewind
mediaPlayer.stop();
});// end of media settings




// set up vizualisation circle container


Group visContainer = (Group) PRIMARY_STAGE.getScene()
                                          .lookup("#" + VIS_CONTAINER_ID);
visContainer.prefWidth(PRIMARY_STAGE.getScene().getWidth()-(200));
mediaPlayer.setAudioSpectrumListener(
(double timestamp,
 double duration,
 float[] magnitudes,
 float[] phases )-> {
    visContainer.getChildren().clear();    
    int i =0;
    int x = 2;
    
double y = PRIMARY_STAGE.getScene().getHeight()-(400);
Random rand = new Random(System.currentTimeMillis());

//Build Randoom coloured circles
for (float phase : phases ){
int red = rand.nextInt(200);
int blue = rand.nextInt(200);
int green = rand.nextInt(200);


    Circle circle = new Circle(10);
circle.setCenterX(x+i);
circle.setCenterY(y+(phase*100));
circle.setFill(Color.rgb(red, green, blue, .70));
visContainer.getChildren().add(circle);
i+=5;

;

                    }
});// end set audio spectrum listener


// set the media player to display video

MediaView mediaView = (MediaView) scene.lookup("#"+ MEDIA_VIEW_ID);
mediaView.setMediaPlayer(mediaPlayer);

}//playMedia()


/*Simple Rectanglulaire surface comme surface de la 
fenetre d' application
@return Node a Rectangle node
*/

private Pane createApplicationArea(){
Scene scene = PRIMARY_STAGE.getScene();

Pane applicationArea = new Pane();

Rectangle appArea = new Rectangle();
appArea.setStyle("-fx-fill: green");
// add selector to style app-area
applicationArea.setId("app-area");
appArea.widthProperty().bind(scene.widthProperty().subtract(200));
appArea.heightProperty().bind(scene.heightProperty());
applicationArea.getChildren().add(appArea);

// make the app area rectangle the size of the scene

applicationArea.setLayoutX(0);
applicationArea.setLayoutY(0);



return applicationArea;
}


/**
* Create  a mediaView node
* @retrun MediaView
*/

private MediaView createMediaView(){

Scene scene = PRIMARY_STAGE.getScene();

MediaView mediaView = new MediaView();
mediaView.setId(MEDIA_VIEW_ID);
mediaView.setSmooth(true);

mediaView.fitWidthProperty().bind(PRIMARY_STAGE.getScene().widthProperty().subtract(SideBar.USE_COMPUTED_SIZE));
mediaView.fitHeightProperty().bind(PRIMARY_STAGE.getScene().heightProperty());

mediaView.setLayoutX(0);
mediaView.setLayoutY(25);
//Pour les cas d'erreurs

mediaView.setOnError(mediaErrorEvent->{
mediaErrorEvent.getMediaError()
                .printStackTrace();
});
return mediaView;
}



 
/** reduce button to set application in its min widdth
*@return reduceButton
**/
private Node createFullScreenButton() {
Scene scene = PRIMARY_STAGE.getScene();
    Group fullScreenButton = new Group();
    fullScreenButton.setId(FULL_SCREEN_BUTTON_ID);
    
    Node fullScreenBackground = new Circle(5,0,11);
    fullScreenBackground.setId("full-screen-circle");
    fullScreenBackground.setStyle("-fx-fill:coral;");
    ImageView imageViewFullScreen = new ImageView(buttonFullScreenImage) ;
    
    fullScreenButton.setLayoutX(480);
    fullScreenButton.setLayoutY(1);
    
    fullScreenButton.getChildren().addAll(fullScreenBackground,imageViewFullScreen);
   
    
//exit app
    fullScreenButton.setOnMouseClicked(mouseEvent->{
    PRIMARY_STAGE.setFullScreen(true);
    });
         
    return fullScreenButton;

}
private Node createReduceButton() {
Scene scene = PRIMARY_STAGE.getScene();
    Group reduceButton = new Group();
    reduceButton.setId(REDUCE_BUTTON_ID);
    
    Node reduceBackground = new Circle(5,0,11);
    reduceBackground.setId("reduce-circle");
    
    Node reduceXmark = new Text(2,4,"-");
   
    reduceButton.setLayoutX(510);
    reduceButton.setLayoutY(13);
    
    reduceButton.getChildren().addAll(reduceBackground,reduceXmark);
    //exit app
    reduceButton.setOnMouseClicked(mouseEvent->{
    PRIMARY_STAGE.setIconified(true);
    });
         
    return reduceButton;

}


/**
 * The close button to exit application
 * 
 * @@return Node representating a close button
 */
private Node createCloseButton(){
    Scene scene = PRIMARY_STAGE.getScene();
    Group closeButton = new Group();
    closeButton.setId(CLOSE_BUTTON_ID);
    
    Node closeBackground = new Circle(5,0,11);
    closeBackground.setId("close-circle");
    
    Node closeXmark = new Text(2,4,"X");
    
    closeButton.setLayoutX(535);
    closeButton.setLayoutY(13);
   
    closeButton.getChildren().addAll(closeBackground,closeXmark);
      
    //exit app
    closeButton.setOnMouseClicked(mouseEvent -> Platform.exit());
         
    return closeButton;
   
}   

/**
 * create node generating menu bar  
 * @return menuBar
 */
private Node createMenuBar(){

Scene scene = PRIMARY_STAGE.getScene();    

MenuBar menuBar = new MenuBar();

Menu Fichier = new Menu("Fichier");

MenuItem Ouvrir = new MenuItem("Ouvrir");  
MenuItem  ajouterFichiers =    new MenuItem("Ajouter Fichiers");
MenuItem  ajouterDossiers =    new MenuItem("Ajouter Dossiers");
MenuItem  Infos =    new MenuItem("Infos");
MenuItem  Quitter =    new MenuItem("Quitter");
MenuItem  nvllePlayList =    new MenuItem("Nvlle List");
MenuItem  nvllePlayListAutomatic =    new MenuItem("Nvlle List Aut");

Fichier.getItems().addAll(Ouvrir,Infos,new SeparatorMenuItem(),
                           ajouterFichiers,ajouterDossiers,new SeparatorMenuItem(),
                           nvllePlayList,nvllePlayListAutomatic);

Menu Edition = new Menu("Edition");

MenuItem couper = new MenuItem("Couper");
MenuItem copier = new MenuItem("Copier"); 
MenuItem coller = new MenuItem("Coller");
MenuItem effacer = new MenuItem("Effacer");
MenuItem toutSelectionner = new MenuItem("Tout Sélectionner");
MenuItem Jouer = new MenuItem("Jouer");
MenuItem Prcdnt = new MenuItem("Prcdnt");
MenuItem Svnt = new MenuItem("Svnt");
MenuItem VolumeUp = new MenuItem("Volume + ");
MenuItem VolumeDown = new MenuItem("Volume - ");
MenuItem Aleat = new MenuItem("Aléat");
MenuItem Rpt = new MenuItem("Rpt");

Edition.getItems().addAll(couper,copier,coller,effacer,toutSelectionner,new SeparatorMenuItem(),
                          Jouer,Prcdnt,Svnt,new SeparatorMenuItem(),
                          VolumeUp,VolumeDown,new SeparatorMenuItem(),
                          Aleat,Rpt);

Menu Vue = new Menu("Vue");

MenuItem musique = new MenuItem("Musique");
MenuItem video = new MenuItem("Video"); 
MenuItem livres = new MenuItem("Livres");
MenuItem musicPlaylists = new MenuItem("Music Playlists");
MenuItem videoPlaylists = new MenuItem("Video Playlists");
MenuItem equalizer = new MenuItem("Equalizer");
MenuItem animation = new MenuItem("Animation");
MenuItem sidePane = new MenuItem("Side Pane");
MenuItem courant = new MenuItem("courant ");
MenuItem pleinEcran = new MenuItem("Plein Ecran");

Vue.getItems().addAll(musique,video,livres,musicPlaylists,videoPlaylists,new SeparatorMenuItem(),
                          equalizer,animation,courant,new SeparatorMenuItem(),
                          sidePane,pleinEcran);

Menu Réseau = new Menu("Réseau");

MenuItem trouver = new MenuItem("Trouver");
MenuItem Naviguer = new MenuItem("Naviguer"); 
MenuItem publier = new MenuItem("Publier");
MenuItem contacter = new MenuItem("Contacter");
MenuItem groupes = new MenuItem("Groups");
MenuItem salons = new MenuItem("Salons");

Réseau.getItems().addAll(trouver,contacter,new SeparatorMenuItem(),
                         publier,Naviguer,new SeparatorMenuItem(),
                          groupes,salons);

menuBar.getMenus().addAll(Fichier,Edition,Vue,Réseau);
menuBar.setId(MENU_BAR);
menuBar.setPrefSize(600, 15);
menuBar.setLayoutX(0);
menuBar.translateYProperty().bind(scene.heightProperty().subtract(600));


return menuBar;
}




/**
*Building the library for available
* files both audio and video
*/
private Node createLibraryPanel(){
    Scene scene = PRIMARY_STAGE.getScene();

    
    final List<MediaPlayer> filesVideo = new ArrayList<MediaPlayer>();
    
         
    // Filtrer les videos du répertoire central
                java.nio.file.Path  p = Paths.get("/Users/macbookpro/Documents/Vidéos");
        
try (DirectoryStream<java.nio.file.Path>  ds2 = Files.newDirectoryStream(p,"*.{mp4}"))
			{
                            
                            
			for(java.nio.file.Path  path1 : ds2){
			    String vid = path1.toAbsolutePath().toString();
                            filesVideo.add(createPlayer("file:///" + ( vid).replace("\\", "/").replaceAll(" ", "%20")));
                           
                        }
                        }catch(IOException ex){
			// I/O error encounted during the iteration , the cause is an IOExceptiion
				 ex.printStackTrace();
					}
  
final ObservableList<MediaPlayer> dataVideo = FXCollections.observableArrayList(filesVideo);
    final TableView <MediaPlayer> listVideo = new TableView(dataVideo);
    
   TableColumn<MediaPlayer, String> titreColumn = new TableColumn< >("Titre");   
   TableColumn<MediaPlayer, String>  yearColumn = new TableColumn< >("Artiste");


       
       
       // add a metadataTable for meta data display
    listVideo.setStyle("-fx-font-size: 13px;"+"-fx-background-color:green;");
    listVideo.setId(LIST_VIDEO_ID);
    
    titreColumn.setPrefWidth(150);
    yearColumn.setPrefWidth(150);
 
    titreColumn.setCellValueFactory(new PropertyValueFactory<>(TITRE_COLUMN_NAME));
    yearColumn.setCellValueFactory(new PropertyValueFactory<>(ARTISTE_COLUMN_NAME));
 
    
    
    titreColumn.setCellValueFactory((g)-> new SimpleStringProperty ((String) g.getValue().getMedia().getMetadata().get("title")));
    yearColumn.setCellValueFactory((g)-> new SimpleStringProperty ((String) g.getValue().getMedia().getMetadata().get("year")));
    
   
       
       
    
    listVideo.setItems(dataVideo);
    listVideo.getColumns().addAll(titreColumn,yearColumn);
    
    
    
    listVideo.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<MediaPlayer>(){
            public void changed(ObservableValue<? extends MediaPlayer> ov,MediaPlayer old_val , MediaPlayer new_val ){
             MediaPlayer   mediaPlayer = new_val;   
            }
        
        
        });
   
   
    

// read selected item on 2Click
    
   listVideo.setOnMouseClicked(new EventHandler<MouseEvent>() {

        public void handle(MouseEvent event) {
            if (event.getClickCount() == 2){
               MediaPlayer mediaPlayer ;
                mediaPlayer = listVideo.getSelectionModel().getSelectedItem() ;
                    playMedia(mediaPlayer );
             Scene scene = PRIMARY_STAGE.getScene();
             scene.lookup("#"+VIS_CONTAINER_ID).setVisible(false);
            
            }       }
    });
    
   //resolve audio files
      
   final List<MediaPlayer> filesAudio = new ArrayList<MediaPlayer>(); 
   final List <MediaPlayer> soundModel = (List <MediaPlayer> ) new SoundModel(); 
   
   
   java.nio.file.Path  p1 = Paths.get("/Users/macbookpro/Documents/musik");
             
 try (DirectoryStream<java.nio.file.Path>  ds2 = Files.newDirectoryStream(p1,"*.{mp3}"))
			{
                           
                            for(java.nio.file.Path  path2 : ds2){
			    String sound = path2.toAbsolutePath().toString();
                            filesAudio.add(createPlayer("file:///" + ( sound).replace("\\", "/").replaceAll(" ", "%20"))); 
                                                       
                        }
                        }catch(IOException ex){
			// I/O error encounted during the iteration , the cause is an IOExceptiion
				 ex.printStackTrace();
					}
    
     
    final ObservableList <MediaPlayer>    dataAudio = FXCollections.observableArrayList(filesAudio);
    final   TableView<MediaPlayer > listAudio = new TableView(dataAudio);




    
     // Transférez dans la vue de liste et costumiser
    TableColumn<MediaPlayer, String> titreAudioColumn = new TableColumn<>("Titre");   
    TableColumn<MediaPlayer, String>  artisteAudioColumn = new TableColumn<>("Artiste");
    TableColumn<MediaPlayer, String>  albumAudioColumn = new TableColumn<>("Album");    
    TableColumn<MediaPlayer, String>  genreAudioColumn = new TableColumn<>("Genre");


       
       
       // add a metadataTable for meta data display
    listAudio.setStyle("-fx-font-size: 13px;"+"-fx-background-color:green;");
    listAudio.setId(LIST_AUDIO_ID);
    
   titreAudioColumn.setPrefWidth(150);
   artisteAudioColumn.setPrefWidth(150);
   genreAudioColumn.setPrefWidth(150);
   albumAudioColumn.setPrefWidth(150);
    
   
   titreAudioColumn.setCellValueFactory(new PropertyValueFactory<>(TITRE_AUDIO_COLUMN_NAME));
   artisteAudioColumn.setCellValueFactory(new PropertyValueFactory<>(ARTISTE_AUDIO_COLUMN_NAME));
   albumAudioColumn.setCellValueFactory(new PropertyValueFactory<>(ALBUM_AUDIO_COLUMN_NAME));
   genreAudioColumn.setCellValueFactory(new PropertyValueFactory<>(GENRE_AUDIO_COLUMN_NAME));
       
    
     
    titreAudioColumn.setCellValueFactory((f)-> new SimpleStringProperty ((String) f.getValue().getMedia().getMetadata().get("title")));
    artisteAudioColumn.setCellValueFactory((f)-> new SimpleStringProperty ((String) f.getValue().getMedia().getMetadata().get("artist")));
    albumAudioColumn.setCellValueFactory((f)-> new SimpleStringProperty ((String) f.getValue().getMedia().getMetadata().get("album")));
    genreAudioColumn.setCellValueFactory((f)-> new SimpleStringProperty ((String) f.getValue().getMedia().getMetadata().get("genre")));
    
   
       
       
    
    listAudio.setItems(dataAudio);
    listAudio.getColumns().addAll(titreAudioColumn,artisteAudioColumn,albumAudioColumn,genreAudioColumn);
    
    listAudio.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<MediaPlayer>(){
            public void changed(ObservableValue<? extends MediaPlayer> ov,MediaPlayer old_val , MediaPlayer new_val ){
             MediaPlayer   mediaPlayer = new_val;   
            }
            });
   
     
    
    listAudio.setOnMouseClicked(new EventHandler<MouseEvent>() {

        public void handle(MouseEvent event) {
            if (event.getClickCount() == 2){
               MediaPlayer mediaPlayer ;
                mediaPlayer = listAudio.getSelectionModel().getSelectedItem();
                      playMedia(mediaPlayer );
             Scene scene = PRIMARY_STAGE.getScene();
             scene.lookup("#"+VIS_CONTAINER_ID).setVisible(true);
            }       
       
            }
    });
   
    // Pane as  containers 
 final  TabPane vitrine = new TabPane();
 vitrine.setId(TAB_PANE);
    Tab  audio = new Tab("Musique");
    audio.setId(TAB_AUDIO_ID);
    Tab  video = new Tab("Video");
    video.setId(TAB_VIDEO_ID);
    
   
    video.setContent(listVideo);
        
    audio.setContent(listAudio);
    
    
    vitrine.getTabs().addAll(audio,video);
    
    vitrine.setStyle("-fx-background-color:green;"+"-fx-border-color:coral;"+"-fx-opacity:0.7;");
    
    VBox libraryPanel = new VBox();
    libraryPanel.setId(LIBRARY_PANEL_ID);
    
    libraryPanel.setPrefWidth(600);
    libraryPanel.setPrefHeight(200);
    
    libraryPanel.setStyle("-fx-background-color:green;"+"-fx-border-color:coral;"+"-fx-opacity:0.7;");
    
    
    libraryPanel.getChildren().add(vitrine);
    VBox.setVgrow(listVideo,Priority.ALWAYS);
    VBox.setVgrow(listAudio,Priority.ALWAYS);
      
   libraryPanel.setLayoutX(0);
   libraryPanel.setLayoutY(395);
   
   return libraryPanel;
  
}

/* create Side Bar
*@return sideBar sliding in & out
*/
private Node createSideBarContent(){

        
    final VBox SideBarContent = new VBox() ;
    SideBarContent.setPrefSize(200, 600);
    SideBarContent.setPrefWidth(200);
    SideBarContent.setMinWidth(0);    
    
    final   Button dfltPlayLists = new Button("Source PlayLists");
    dfltPlayLists.setAlignment(Pos.TOP_CENTER);
    dfltPlayLists.setUnderline(true);
    dfltPlayLists.setFont(new Font(18));
    dfltPlayLists.setStyle("-fx-border-color:green ;"+ "-fx-background-color:transparent ;" );
    dfltPlayLists.setPrefWidth( 200  );
    
    final Button musicLists = new Button("Musik Lists");
    musicLists.setAlignment(Pos.BASELINE_CENTER);
    musicLists.setFont(new Font(14));
     musicLists.setStyle("-fx-background-color:bisque ;"+ " -fx-translate-y:10; " );
     musicLists.setPrefWidth( 200  );
    
    final Button videoLists = new Button("Video Lists");
    videoLists.setAlignment(Pos.BASELINE_CENTER);
    videoLists.setFont(new Font(14));
    videoLists.setStyle("-fx-background-color:bisque ;"+ "-fx-translate-y:20;" );
    videoLists.setPrefWidth( 200  );
     
    final Button bookLists = new Button("Livres Lists");
    bookLists.setAlignment(Pos.BASELINE_CENTER);
    bookLists.setFont(new Font(14));
    bookLists.setStyle("-fx-background-color:bisque ;"+"-fx-translate-y:30; " );
    bookLists.setPrefWidth( 200  );
    
    final VBox defPlayPane = new VBox();
    defPlayPane.setPrefSize(200, 175);
    defPlayPane.setMinWidth(0);
    defPlayPane.setStyle("-fx-border-color:grey;");
    defPlayPane.setPadding(new Insets(10,5 , 10,5))   ;
    defPlayPane.getChildren().addAll(dfltPlayLists,musicLists,videoLists,bookLists);
    defPlayPane.setLayoutX(600);    
    defPlayPane.setLayoutY(0);
    defPlayPane.setAlignment(Pos.TOP_CENTER);
    
    
    final Button custPlaylists = new Button("Owna Playlists");
    custPlaylists.setAlignment(Pos.TOP_CENTER);
    custPlaylists.setUnderline(true);
    custPlaylists.setFont(new Font(18));
    custPlaylists.setStyle("-fx-border-color:green ;"+ "-fx-background-color:transparent ;");
  
    
    custPlaylists.setPrefWidth( 200  );
    
    final Button musicUserLists = new Button("User Musik Lists");
    musicUserLists.setAlignment(Pos.BOTTOM_CENTER);
    musicUserLists.setFont(new Font(14));
    musicUserLists.setStyle("-fx-background-color:bisque; "+ " -fx-translate-y:10; " );
    musicUserLists.setPrefWidth( 200  );
   
    
     final Button videoUserLists = new Button("User Video Lists");
    videoUserLists.setAlignment(Pos.BOTTOM_CENTER);
    videoUserLists.setFont(new Font(14));
    videoUserLists.setStyle("-fx-background-color:bisque ;"+ "-fx-translate-y:20;" );
    videoUserLists.setPrefWidth( 200  );
     
     
    final Button bookUserLists = new Button("User Livres Lists");
    bookUserLists.setAlignment(Pos.BOTTOM_CENTER);
    bookUserLists.setFont(new Font(14));
    bookUserLists.setStyle("-fx-background-color:bisque;" +"-fx-translate-y:30; " );
    bookUserLists.setPrefWidth( 200  );
     
    final VBox custPlayPane = new VBox();
    custPlayPane.setPrefSize(200, 175);
    custPlayPane.setMinWidth(0);
    custPlayPane.setPadding(new Insets(10, 5, 10, 5));
    custPlayPane.setStyle("-fx-border-color:grey;");
    custPlayPane.getChildren().addAll(custPlaylists,musicUserLists,videoUserLists,bookUserLists);
    custPlayPane.setLayoutX(600);    
    custPlayPane.setLayoutY(176);
    custPlayPane.setAlignment(Pos.TOP_CENTER);
    
    
    final Label Network = new Label("NetWork Session");
    Network.setAlignment(Pos.TOP_CENTER);
    Network.setUnderline(true);;
    Network.setFont(new Font(18));
    Network.setStyle("-fx-border-color:green;" +"-fx-translate-y:10; ");
    Network.setPrefWidth(200);
    
    final VBox netPane = new VBox();
    netPane.setPrefSize(200, 248);
    netPane.setMinWidth(0);
    netPane.setPadding(new Insets(10,5 , 10,5))   ;
    netPane.setStyle("-fx-border-color:grey;");
    netPane.getChildren().add(Network);
    netPane.setLayoutX(600);    
    netPane.setLayoutY(300);
    netPane.setAlignment(Pos.TOP_CENTER);

   Scene scene = PRIMARY_STAGE.getScene();
   
    
    
    
    SideBarContent.setLayoutX(600);
    SideBarContent.setLayoutY(0);
    
    SideBarContent.getChildren().addAll(defPlayPane,custPlayPane,netPane);
    
        
    return SideBarContent;

}
// animation to slide in & out SideBar
class SideBar extends VBox {
//@return a control button to hide and show the sidebar
    
    public Button getControlButton(){return controlButton ;}
    private final Button controlButton ;
    SideBar (final double expandedwidth,Node...nodes){
    
    getStyleClass().add("SideBar");
    this.setPrefWidth(expandedwidth);
    this.setMinWidth(0);
    
    setLayoutX(600);
    setLayoutY(0);
   

// create a bar to hide and show
    setAlignment(Pos.CENTER);
    getChildren().addAll(nodes);
    
    // create a button to hide and show the sidebar
    controlButton = new Button(">");
    controlButton.setId("control-sideBar");
    controlButton.getStyleClass().add("hide-left");
    
    
    controlButton.setLayoutX(565);
    controlButton.setLayoutY(0);
    
    //apply animation when button is pressed
    controlButton.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent actionEvent) {
            //create an animation to hide sideBar
       final Animation hideSidebar = new Transition(){
            {setCycleDuration(Duration.millis(250));}
            protected void interpolate (double frac){
            final double curWidth = 200 * (1.0 - frac);
            setPrefWidth ( curWidth );
            setTranslateX(-200+curWidth);
            
            }
        } ;
        hideSidebar.onFinishedProperty().set(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent actionEvent) {
        setVisible(false);
        
        
        controlButton.setText(">");
        controlButton.getStyleClass().remove("hide-left");
        controlButton.getStyleClass().add("show-right");
           }
       });
        //create an animation to show side bar 
        final Animation showSideBar = new Transition(){
            {setCycleDuration(Duration.millis(250)); }
            protected void interpolate (double frac){
            final double curWidth = 200 *frac ;
            setPrefWidth(curWidth);
            setTranslateX(-200+curWidth);
            }
        };
        showSideBar.onFinishedProperty().setValue(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent event) {
               controlButton.setText("<");
               controlButton.getStyleClass().add("hide-left");
               controlButton.getStyleClass().remove("show-right");
                   }
       });
               if(showSideBar.statusProperty().get() == Animation.Status.STOPPED 
                && hideSidebar.statusProperty().get() == Animation.Status.STOPPED){ 
        if (isVisible()){
        hideSidebar.play();
        }else{
            setVisible(true);
            showSideBar.play();
        }
        }
        
        }
    });
    }

}



/*
Initialise la scène de façon à permettre
le plein écran au double click
*/
private void initFullScreenMode(){
Scene scene = PRIMARY_STAGE.getScene();

// Full screen toggle
scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

    public void handle(MouseEvent event) {
        if (event.getClickCount() == 2){
            
            scene.lookup("#"+LIBRARY_PANEL_ID).setVisible(false);
            scene.lookup("#"+MENU_BAR).setVisible(false);
            scene.lookup("#"+SIDE_BAR).setVisible(false);
            scene.lookup("#"+TOOL_BAR).setVisible(false);
            scene.lookup("#"+REDUCE_BUTTON_ID).setVisible(false);
            scene.lookup("#" + CLOSE_BUTTON_ID).setVisible(false);
            scene.lookup("#"+ CONTROL_SIDE_BUTTON).setVisible(false);
            scene.lookup("#"+ FULL_SCREEN_BUTTON_ID).setVisible(false);
    
            PRIMARY_STAGE.setFullScreen(!PRIMARY_STAGE.isFullScreen());
           
        } else {
                   scene.lookup("#"+LIBRARY_PANEL_ID).setVisible(true);
                   scene.lookup("#"+MENU_BAR).setVisible(true);
                   scene.lookup("#"+SIDE_BAR).setVisible(true);
                   scene.lookup("#"+TOOL_BAR).setVisible(true);
                   scene.lookup("#"+REDUCE_BUTTON_ID).setVisible(true);
                   scene.lookup("#" + CLOSE_BUTTON_ID).setVisible(true);
                   scene.lookup("#"+ CONTROL_SIDE_BUTTON).setVisible(true);
                   scene.lookup("#"+ FULL_SCREEN_BUTTON_ID).setVisible(true);
                   PRIMARY_STAGE.setFullScreen(false);
                   PRIMARY_STAGE.isResizable();
        }  }
});


}    
/*Initialize the stage to alllow Mouse to
move application using dragging*/
  
private void initMovablePlayer()    {

    Scene scene = PRIMARY_STAGE.getScene();
    
//Captage du point initial
    
   scene.setOnMousePressed(mouseEvent 
           -> anchorPoint = new Point2D(mouseEvent.getScreenX(),
                   mouseEvent.getScreenY())
   );

   //dragging the entire stage
   scene.setOnMouseDragged(mouseEvent->{
   if (anchorPoint != null && previousLocation != null){
    PRIMARY_STAGE.setX(previousLocation.getX() 
                       + mouseEvent.getScreenX()
                       - anchorPoint.getX());
    PRIMARY_STAGE.setY(previousLocation.getY() 
                        + mouseEvent.getY() 
                        - anchorPoint.getY());
                                                        } 
   
                                       });
   
  //set the current location
   scene.setOnMouseReleased( mouseEvent
            ->previousLocation = new Point2D(PRIMARY_STAGE.getX() ,PRIMARY_STAGE.getY())
                      );
   
   //Initialize previousLocation after stage shown
   PRIMARY_STAGE.addEventHandler(WindowEvent.WINDOW_SHOWN,
                    (WindowEvent t)->{
                    previousLocation = new Point2D(PRIMARY_STAGE.getX(),PRIMARY_STAGE.getY());
                    });
   
   

}




public List<String> getFileNames (List<String> fileNames , java.nio.file.Path dir){

try{
    DirectoryStream<java.nio.file.Path> stream = Files.newDirectoryStream(dir,"#.{mp3}");
    for (java.nio.file.Path  path : stream){
    if (path.toFile().isDirectory())getFileNames(fileNames, path);
    else{
        fileNames.add(path.toAbsolutePath().toString());
        System.out.print(path.getFileName());
    }
        
    } 
    stream.close();
}catch(IOException e){

    e.printStackTrace();
}
return fileNames;
}

public class StatusListener implements InvalidationListener {
  @Override
  public void invalidated(Observable observable) {
    Platform.runLater(new Runnable() {
      @Override public void run() {
        updateStatus(mediaPlayer.getStatus());
      }
});
} }
private void updateStatus(Status newStatus) {
  
    Scene scene = PRIMARY_STAGE.getScene();
    
    if (newStatus == Status.UNKNOWN || newStatus == null) {
    
    
    
      
    scene.lookup("#"+ MENU_BAR).setDisable(true);
    scene.lookup("#"+SEEK_POS_SLIDER_ID).setDisable(true);
    
  } else {
    scene.lookup("#"+ MENU_BAR).setDisable(false);
    scene.lookup("#"+SEEK_POS_SLIDER_ID).setDisable(false);
   
    if (newStatus == Status.PLAYING) {
      imageViewPlayPause.setImage(buttonPauseImage);
    } else {
      imageViewPlayPause.setImage(buttonPlayImage);
}
} }






/** @return a MediaPlayer for the given source which will report any errors it encounters */
  public  MediaPlayer createPlayer(String mediaSource) {
    final Media media = new Media(mediaSource);
    final MediaPlayer player = new MediaPlayer(media);
    player.setOnError(new Runnable() {
      @Override public void run() {
        System.out.println("Media error occurred: " + player.getError());
      }
    });
    return player;
  } 

 /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }   

}