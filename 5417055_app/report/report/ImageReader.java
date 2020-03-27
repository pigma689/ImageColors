package report;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageReader implements Runnable{
   private final static long DOWNLOAD_TIME_MSEC = 10;
   boolean finished = false;
   private Deque<Color> deque = new ArrayDeque<Color>();
   
   private BufferedImage img ;
   public static int width;
   public static int height;
   Color color;
   public ImageReader(BufferedImage img) {
      this.img = img;
      width = img.getWidth();
      height = img.getHeight();
   }

   
   public void run(){
      for(int i = 0;i < height;i++){
         for(int j = 0;j < width;j++){
            try{
               Thread.sleep(DOWNLOAD_TIME_MSEC); //ピクセル読み込み
            System.out.println("Pixel Loaded" + color + "[ " + i + " , " + j + " ]");
            }catch(InterruptedException e){
            e.printStackTrace();
            }
            color = new Color(img.getRGB(j,i)); 
            deque.add(color); 
         }
      }
      finished = true;
      System.out.println("All Pixel Loaded");
      
   }
   public Color getColors() {
      if(deque.isEmpty()) {
         return null;
      } else {
         return deque.removeFirst();
      }
   }
   public boolean isFinished() {
      return finished && deque.isEmpty();
   }
}
