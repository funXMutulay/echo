/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesmodel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author macbookpro
 */
public class SoundModel {
     private  List<MediaPlayer > soundModel = new ArrayList<>();

 public  List<  MediaPlayer >  getSoundModel(){
			
return soundModel ;

}

 public  void  setSoundModel( List<MediaPlayer > soundModel ){
			
this.soundModel= soundModel ;

}
 
 
public   void main(String[] args) throws IOException{
			
File dir = new File("/Users/macbookpro/Documents/fichiers");
		String[] extensions = new String[] { "mp3", "flv" };
		
		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
                files.stream().map((file) -> file.toPath().toString()).forEach((biir) -> {
                    soundModel.add(createPlayer("file:///" + ( biir).replace("\\", "/").replaceAll(" ", "%20")));
        });


 
 

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

    return player ;
    
  } 
    
}
