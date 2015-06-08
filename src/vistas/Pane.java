/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.PaneController;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.itver.componentlibrary.component.CodeEditor;

/**
 *
 * @author Adrián
 */
public class Pane extends JPanel {
    
    private JButton jbCompile;
    private JButton jbClear;
    private JButton jbSaveResults;
    private JTextArea jtResultsArea;
    private PaneController paneController;
    private CodeEditor ce;
    
    public Pane() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(getBackground(), 10));
        addComponents();
    }
    
    private void addComponents() {
        jtResultsArea = new JTextArea(10, 20);
        
        JPanel southPane = new JPanel(new BorderLayout());
        
        southPane.setBorder(BorderFactory.createLineBorder(getBackground(), 15));
        
        getJtResultsArea().setEditable(false);
        
        jbCompile = new JButton("Compilar");
        jbClear = new JButton("Limpiar");
        getJbCompile().setEnabled(true);
        getJbCompile().setIcon(new ImageIcon(getClass().getResource("/iconos/compila.png")));
        jbClear.setIcon(new ImageIcon(getClass().getResource("/iconos/limpiar.png")));
        
        JPanel auxSouthPane = new JPanel();
        
        auxSouthPane.add(getJbCompile());
        auxSouthPane.add(new JLabel(""));
        auxSouthPane.add(jbClear);
        
        JPanel centerPane = new JPanel(new BorderLayout());
        ce = new CodeEditor();
        centerPane.add(ce, BorderLayout.CENTER);
        centerPane.add(auxSouthPane, BorderLayout.SOUTH);
        
        JScrollPane resultPane = new JScrollPane(getJtResultsArea());
        resultPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        resultPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        JPanel resultSouthPane = new JPanel();
        jbSaveResults = new JButton("Guardar Resultados");
        getJbSaveResults().setEnabled(false);
        getJbSaveResults().setIcon(new ImageIcon(getClass().getResource("/iconos/guardar.png")));
        resultSouthPane.add(getJbSaveResults());
        
        southPane.add(resultPane, BorderLayout.CENTER);
        southPane.add(resultSouthPane, BorderLayout.SOUTH);
        
        add(centerPane, BorderLayout.CENTER);
        add(southPane, BorderLayout.SOUTH);
    }

    /**
     * @return the controladorP
     */
    public PaneController getPaneController() {
        return paneController;
    }

    /**
     * @param paneController the controladorP to set
     */
    public void setPaneController(PaneController paneController) {
        this.paneController = paneController;
        getJbCompile().addActionListener(paneController);
        getJbSaveResults().addActionListener(paneController);
        jbClear.addActionListener(paneController);
    }

    /**
     * @return the ce
     */
    public CodeEditor getCe() {
        return ce;
    }

    /**
     * @return the jbCompile
     */
    public JButton getJbCompile() {
        return jbCompile;
    }

    /**
     * @return the jtResultsArea
     */
    public JTextArea getJtResultsArea() {
        return jtResultsArea;
    }

    /**
     * @return the jbSaveResults
     */
    public JButton getJbSaveResults() {
        return jbSaveResults;
    }
    
}
