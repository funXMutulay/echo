/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echo;


import filesmodel.SoundModel;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author macbookpro
 */
public class EchoController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @FXML
     private String MENU_BAR ="menuBar";
    @FXML
    private   Pane screenPane;
    @FXML
    private   GridPane customVariable;
    @FXML
    private   StackPane defaultLibrary;
    @FXML
    private Stage PRIMARY_STAGE;
    
    private String LIST_AUDIO_ID;
    private ChangeListener<Duration> progressListener;
    private InvalidationListener StatusListener;
    private String SEEK_POS_SLIDER_ID;
    private String VIS_CONTAINER_ID;
    private String MEDIA_VIEW_ID;
    private String LIBRARY_PANEL_ID;
    private String TAB_VIDEO_ID;
    private String TAB_AUDIO_ID;
    private String TAB_PANE;
    private String LIST_VIDEO_ID;
    private ImageView imageViewPlayPause;
    private final Image buttonPlayImage = new Image(Echo.class.getResourceAsStream("play.png"));
   private final Image buttonPauseImage = new Image(Echo.class.getResourceAsStream("pause.png"));
    private String TOOL_BAR;
    private Node imageViewLectureAleatoire;
    private Node imageViewLectureContinue;
    private MediaPlayer mediaPlayer;
    private Node imageViewForward;
  
    private String ALBUM_AUDIO_COLUMN_NAME;
    private String ARTISTE_AUDIO_COLUMN_NAME;
    private String GENRE_AUDIO_COLUMN_NAME;
    private String TITRE_AUDIO_COLUMN_NAME;
     @FXML
    private MediaPlayer[] dataAudio;
    
     @FXML
    private TableColumn titreAudioColumn;
     @FXML
      private TableColumn artisteAudioColumn;
     @FXML
      private TableColumn albumAudioColumn;
     @FXML
     private TableColumn genreAudioColumn;
   
    @FXML
    private  TableView <MediaPlayer > listAudio ;
   
 
    
    
    
    
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
Create the node containing the audioplayer's
* stop , pause & play ,foreward ,backward 
* lecture Continue , Lecture aleatoire ... buttons
* 
* 
* @return Node A button panel having play ,
* pause and stop buttons
**/
@FXML
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
        private MediaPlayer mediaPlayer;
    @Override
    public void handle(ActionEvent arg0) {
     
      if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
        mediaPlayer.pause();
        imageViewPlayPause.setImage(buttonPlayImage);
      } else {
        mediaPlayer.play();
        imageViewPlayPause.setImage(buttonPauseImage);
      }
}   });



controls.getItems().add(playPauseButton);

Button backward = new Button();
        ImageView imageViewBackward = null;
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
        private MediaPlayer mediaPlayer;
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
    
/* create Side Bar
*@return sideBar sliding in & out
*/

@FXML
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
class customVariable extends GridPane {
//@return a control button to hide and show the sidebar
    
    public Button getControlButton(){return controlButton ;}
    private final Button controlButton ;
    customVariable (final double expandedwidth,Node...nodes){
    
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

    
    
    @FXML
    /**
*Building the library for available
* files both audio and video
*/
private Node createLibraryPanel(){
    Scene scene = PRIMARY_STAGE.getScene();

    
    

    
//resolve audio files
      
   
    
    
    List<MediaPlayer> filesAudio = (List<MediaPlayer>) new SoundModel(); 
   
   
   
   

  ObservableList <MediaPlayer>    dataAudio = FXCollections.observableArrayList(filesAudio);
   TableView<MediaPlayer > listAudio = new TableView(dataAudio);
  


    
     // Transférez dans la vue de liste et costumiser
    TableColumn<MediaPlayer, String> titreAudioColumn = new TableColumn<>("Titre");   
    TableColumn<MediaPlayer, String>  artisteAudioColumn = new TableColumn<>("Artiste");
    TableColumn<MediaPlayer, String>  albumAudioColumn = new TableColumn<>("Album");    
    TableColumn<MediaPlayer, String>  genreAudioColumn = new TableColumn<>("Genre");


       
       
       // add a metadataTable for meta data display
    listAudio.setStyle("-fx-font-size: 13px;"+"-fx-background-color:green;");
    
   
  
   
   titreAudioColumn.setCellValueFactory(new PropertyValueFactory<>(TITRE_AUDIO_COLUMN_NAME));
   artisteAudioColumn.setCellValueFactory(new PropertyValueFactory<>(ARTISTE_AUDIO_COLUMN_NAME));
   albumAudioColumn.setCellValueFactory(new PropertyValueFactory<>(ALBUM_AUDIO_COLUMN_NAME));
   genreAudioColumn.setCellValueFactory(new PropertyValueFactory<>(GENRE_AUDIO_COLUMN_NAME));
       
    
     
    titreAudioColumn.setCellValueFactory((f)-> new SimpleStringProperty ((String) f.getValue().getMedia().getMetadata().get("title")));
    artisteAudioColumn.setCellValueFactory((f)-> new SimpleStringProperty ((String) f.getValue().getMedia().getMetadata().get("artist")));
    albumAudioColumn.setCellValueFactory((f)-> new SimpleStringProperty ((String) f.getValue().getMedia().getMetadata().get("album")));
    genreAudioColumn.setCellValueFactory((f)-> new SimpleStringProperty ((String) f.getValue().getMedia().getMetadata().get("genre")));
    
    listAudio.setPrefWidth(600);
   listAudio.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    listAudio.getColumns().get(0).prefWidthProperty().bind(listAudio.widthProperty().multiply(0.25));    // 25% for titre column size
    listAudio.getColumns().get(1).prefWidthProperty().bind(listAudio.widthProperty().multiply(0.25));   // 25% for Artiste column size
    listAudio.getColumns().get(2).prefWidthProperty().bind(listAudio.widthProperty().multiply(0.25));    // 25% for Album  column size
    listAudio.getColumns().get(3).prefWidthProperty().bind(listAudio.widthProperty().multiply(0.25));   // 25% for Artiste column size
    
       
       
    
    listAudio.setItems(dataAudio);
    listAudio.getColumns().addAll(titreAudioColumn,artisteAudioColumn,albumAudioColumn,genreAudioColumn);
    
    listAudio.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends MediaPlayer> ov, MediaPlayer old_val, MediaPlayer new_val) -> {
        MediaPlayer mediaPlayer1 = new_val;
    });
   
     
    
    listAudio.setOnMouseClicked((MouseEvent event) -> {
        if (event.getClickCount() == 2) {
            MediaPlayer mediaPlayer1;
            mediaPlayer1 = listAudio.getSelectionModel().getSelectedItem();
            playMedia(mediaPlayer1);
            Scene scene1 = PRIMARY_STAGE.getScene();
            scene1.lookup("#"+VIS_CONTAINER_ID).setVisible(true);
        }
    });
   
 
   return listAudio;
  
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

// as the media is playing move the slider in progress
Slider progressSlider = (Slider) scene.lookup("#"+SEEK_POS_SLIDER_ID);
           progressSlider.setValue(0);
           progressSlider.setMax(mediaPlayer.getMedia()
                                        .getDuration()
                                        .toSeconds());
    

progressSlider.valueProperty()
       .addListener((observable)-> {

        
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

progressListener = (observable , oldValue , newValue) ->
         progressSlider.setValue(newValue.toSeconds());



mediaPlayer.currentTimeProperty()
           .addListener(progressListener);


mediaPlayer.statusProperty().addListener(StatusListener);




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


@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
    /**
 * create node generating menu bar  
 * @return menuBar
 */

    
    }    
 
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
}
