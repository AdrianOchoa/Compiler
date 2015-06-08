/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import compilador.SemanticAnalyzer;
import compilador.LPBE;
import compilador.ParseException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import util.FileUtility;
import util.Message;
import vistas.Pane;

/**
 *
 * @author Adrián
 */
public class PaneController implements ActionListener {

    private final Pane pane;

    public PaneController(Pane pane) {
        this.pane = pane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();
        switch (label) {
            case "Compilar": {
                try {
                    compile();
                } catch (FileNotFoundException | ParseException | UnsupportedEncodingException ex) {
                    Message.showErrorMessage("Error al compilar\n" + ex.getMessage());
                }
            }
            break;
            case "Limpiar":
                clearTexts();
                break;
            case "Guardar Resultados":
                saveResults();
                break;
        }
    }

    private void compile() throws FileNotFoundException, ParseException,
            UnsupportedEncodingException {
        clearResults();
        String url = "C:\\Users\\Public\\Documents\\prueba.txt";
        String code = pane.getCe().getCodeArea().getText();
        FileUtility.saveToFile(code, url);
        FileInputStream fis = new FileInputStream(url);
        LPBE lpbe = new LPBE(fis);
        if (lpbe.ejecutarAnalisisSintactico()) {
            Message.showInfoMessage("Compilación exitosa");
            showResults();
            SemanticAnalyzer sa = new SemanticAnalyzer(code);
            sa.runSemanticAnalysis(pane);
            pane.getJbSaveResults().setEnabled(true);
        } else {
            Message.showErrorMessage(LPBE.getErrores().toString());
            LPBE.getErrores().delete(0, LPBE.getErrores().length());
            pane.getJbSaveResults().setEnabled(false);
        }
    }

    private void clearResults() {
        pane.getJtResultsArea().setText("");
    }

    private void showResults() {
        StringBuilder sb = new StringBuilder();
        sb.append("Resultado del analisis lexico:\n");
        for (int i = 0; i < LPBE.getLista().size(); i++) {
            sb.append(LPBE.getLista().get(i)).append("\n");
        }
        LPBE.getLista().clear();
        pane.getJtResultsArea().setText(sb.toString());
    }

    private void saveResults() {
        try {
            FileUtility.saveToFile(pane.getJtResultsArea().getText());
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Message.showErrorMessage("Error al guardar resultados\n" + ex.getMessage());
        }
    }

    private void clearTexts() {
        pane.getCe().getCodeArea().setText("");
        pane.getCe().getNumberLineArea().setText("");
        clearResults();
        pane.getJbCompile().setEnabled(false);
        pane.getJbSaveResults().setEnabled(false);
    }

}
