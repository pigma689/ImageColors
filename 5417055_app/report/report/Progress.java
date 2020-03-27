package report;

import java.awt.Color;

public class Progress implements Runnable{
   private final static long WAIT_TIME_MSEC = 10;
   private final static long ANALYSIS_TIME_MSEC = 10;

   private ImageReader imagereader;
   private Result result;
   private int total;
   private int count = 0;
   public Progress(ImageReader imagereader, Result result){
      this.imagereader = imagereader;
      this.result = result;
      total = imagereader.width*imagereader.height;
      
   }

   public void run(){
      while(!imagereader.isFinished()){
         System.out.println("Loading :: " +(count*100/total) + "%" );
         Color color = imagereader.getColors();
         /*int red = color.getRed();
         int blue = color.getBlue();
         int green = color.getGreen();*/
         if(color == null ){
            try {
               Thread.sleep(WAIT_TIME_MSEC);
               
            } catch(InterruptedException e) {
               e.printStackTrace();
            }
         }else{
            try{
               Thread.sleep(ANALYSIS_TIME_MSEC);
               // Color color = imagereader.getColors();
               int red = color.getRed();
               int blue = color.getBlue();
               int green = color.getGreen();
               
            if(Math.max(red, Math.max(blue, green)) == red){
               result.addRCount();
            }else if(Math.max(red, Math.max(blue, green)) == blue){
               result.addBCount();
            }else if(Math.max(red, Math.max(blue, green)) == green){
               result.addGCount();
            }
            }catch(InterruptedException e) {
               e.printStackTrace();
            }
         }
         synchronized(this) {
         count++;
         }
      }
      System.out.println("\\Finished");
   }
}
