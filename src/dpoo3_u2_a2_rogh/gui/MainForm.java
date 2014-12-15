package dpoo3_u2_a2_rogh.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author rodolfo
 */
public class MainForm extends JFrame {

    private JPanel panelFrame = new JPanel();
    private ArrayList<ContenedorDeTareas> listaContenedorTareas = new ArrayList<>();
    private JPanel panelProgressBar = new JPanel();
    JButton boton;
    ContenedorDeTareas contenedorDeTareas;

    public MainForm() {
        super();
        this.setSize(600, 600);
        this.setTitle("Multihilos en Java");

        panelFrame.setLayout(new BorderLayout());

        this.add(panelFrame, BorderLayout.CENTER);

        this.InitUi();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);

        this.pack();
    }

    private void InitUi() {
        
        boton = new JButton("Iniciar hilos");
        boton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                crearPaneles();
                iniciarHilos();
            }
        });
        panelProgressBar.setLayout(new BoxLayout(panelProgressBar, BoxLayout.Y_AXIS));

        panelFrame.add(boton, BorderLayout.NORTH);
        panelFrame.add(panelProgressBar, BorderLayout.CENTER);
        
    }

    private void crearContenedoresTareas() {
        contenedorDeTareas = new ContenedorDeTareas();
        panelProgressBar.add(contenedorDeTareas.getPanel());
        listaContenedorTareas.add(contenedorDeTareas);
    }

    private void crearPaneles() {
        listaContenedorTareas.clear();
        panelProgressBar.removeAll();
        for (int i = 0; i < 10; i++) {
            crearContenedoresTareas();
        }
        this.pack();
    }

    private void iniciarHilos() {
        ArrayList<Thread> hilos = new ArrayList<>();
        int numHilo = 0;
        boton.setEnabled(false);
        for (ContenedorDeTareas contenedorTarea : listaContenedorTareas) {
            Thread t = new Thread(contenedorTarea.getTarea());
            t.setName("Hilo " + ++numHilo);
            hilos.add(t);
            t.start();
        }

        verificarHilos(hilos);

    }

    private void verificarHilos(final ArrayList<Thread> hilos) {
        Thread verHilosVivos = new Thread(new Runnable() {
            
            @Override
            public void run() {
                boolean hilosVivos = false;
                do {
                    hilosVivos = false;
                    for (Thread t : hilos) {
                        if (t.isAlive()) {
                            hilosVivos = true;
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } while (hilosVivos);
                JOptionPane.showMessageDialog(null, "Los hilos han terminado de ejecutarse");
                boton.setEnabled(true);
            }
        });

        verHilosVivos.start();
    }

}
