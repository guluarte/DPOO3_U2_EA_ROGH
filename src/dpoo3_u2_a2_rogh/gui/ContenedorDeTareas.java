
package dpoo3_u2_a2_rogh.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author rodolfo
 */
public class ContenedorDeTareas {
    
    JPanel panelContenedor;
    private JProgressBar progressBar;
    private JButton stopButton;
    
    private Tarea tarea;
    private String nombre;
    private enum EstadoBotones {
        PARAR, CONTINUAR, REINICIAR, COMPLETADO
    }
    public ContenedorDeTareas() {
        this.panelContenedor = new JPanel();
        this.panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.X_AXIS));
        this.progressBar = new JProgressBar(0, 100);
        this.progressBar.setStringPainted(true);
        this.stopButton = new JButton(EstadoBotones.PARAR.toString());
        
        this.tarea = new Tarea(this.progressBar);
        
        this.progressBar.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
               JProgressBar progressBar = (JProgressBar)e.getSource();
               if(progressBar.getValue() == 100) {
                   stopButton.setEnabled(false);
                   stopButton.setText(EstadoBotones.COMPLETADO.toString());
               }
            }
        });
        
        this.stopButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (stopButton.getText().equals(EstadoBotones.PARAR.toString())) {
                    tarea.stop();
                    stopButton.setText(EstadoBotones.CONTINUAR.toString());
                } else {
                    tarea.restart();
                    stopButton.setText(EstadoBotones.PARAR.toString());
                }
            }
            
        });
        
        
        this.panelContenedor.add(progressBar);
        this.panelContenedor.add(stopButton);
    }
    
    public JPanel getPanel() {
        return this.panelContenedor;
    }

    public Runnable getTarea() {
        return this.tarea;
    }

    public String getNombre() {
        return nombre;
    }
}
