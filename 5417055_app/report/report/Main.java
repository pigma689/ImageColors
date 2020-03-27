package report;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.nio.file.Paths;
import java.io.*;

public class Main{
   private static  BufferedImage img;
   private final static int TH_COUNT = 3;
   public static void main(String[] args) throws InterruptedException {
      long startTimeMsec = System.currentTimeMillis();
      
      try{                                                    
      img = ImageIO.read(new File("./report/s.jpeg"));
      }catch(IOException e){
           System.out.println("image file not found. [sample]");
      }
      
      ImageReader imagereader = new ImageReader(img);
      Thread imgThread = new Thread(imagereader);
      imgThread.start();

      Result result = new Result();
      Thread[] prgThreads = new Thread[TH_COUNT];
      Progress prg = new Progress(imagereader, result); 
      for(int i = 0;i < prgThreads.length;i++){
         prgThreads[i] = new Thread(prg);
         prgThreads[i].start();
      }
      imgThread.join();
      for(Thread t : prgThreads){
         t.join();
      }
      
      System.out.println("赤系統: " + result.getRCount());
      System.out.println("青系統: " + result.getBCount());
      System.out.println("緑系統: " + result.getGCount());

      long endTimeMsec = System.currentTimeMillis();
      System.out.println("Processing time: "+ (endTimeMsec - startTimeMsec) + " msec");
}
}
