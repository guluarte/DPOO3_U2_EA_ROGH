
package dpoo3_u2_a2_rogh.gui;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

/**
 *
 * @author rodolfo
 */
public class Tarea implements Runnable {
    JProgressBar progressbar;
    Random random = new Random();
    boolean stopped = false;
    private int cont = 0;
    
    public Tarea(JProgressBar progressBar) {
        this.progressbar = progressBar;
        this.progressbar.setValue(0);
        
    }

    public void restart() {
        this.stopped = false;
        
    }
    public void stop() {
        this.stopped = true;
    }
    @Override
    
    public void run() {
        for (restart(); isRunning(); cont++) {
            try {
                this.progressbar.setValue(cont);
                this.progressbar.setString(Thread.currentThread().getName() + ": " +  cont + " %");
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException ex) {
                Logger.getLogger(Tarea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

    }
    
    private boolean isRunning() {
        dormir();
        return cont <= 100;
    }
    /*
    Dormimos el hilo si no se ha terminado la tarea y se preciono el boton de
    parar
    */
    private void dormir() {
        while(this.stopped && cont < 100 ) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tarea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
    
    
    
}
