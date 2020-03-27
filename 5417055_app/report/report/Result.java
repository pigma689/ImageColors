package report;
public class Result {

   private final static long ADD_TIME_MSEC = 50;
   private int red;
   private int blue;
   private int green;
   public Result() {
      red = 0;
      blue = 0;
      green = 0;
   }
   public void addRCount() throws InterruptedException {
      Thread.sleep(ADD_TIME_MSEC);
      synchronized(this) {
      red++;
      }
   }
   public void addBCount() throws InterruptedException {              
      Thread.sleep(ADD_TIME_MSEC);
      synchronized(this) {
      blue++;
      }
   }
   public void addGCount() throws InterruptedException {              
      Thread.sleep(ADD_TIME_MSEC);                                             
      synchronized(this) {
      green++;
      }
   } 
   public int getRCount() {
      return red;
   }
   public int getBCount() {                                                 
      return blue;
   }
   public int getGCount() {                                                 
      return green;                                                        
   } 
}
