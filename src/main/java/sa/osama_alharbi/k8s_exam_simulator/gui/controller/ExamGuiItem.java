package sa.osama_alharbi.k8s_exam_simulator.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import sa.osama_alharbi.k8s_exam_simulator.scenario.exams.Exam;
import sa.osama_alharbi.k8s_exam_simulator.scenario.interfaces.CheckSolutionIntr;

import java.net.URL;
import java.util.ResourceBundle;

public class ExamGuiItem extends InitView implements Initializable {
    public static boolean HAS_BEAN = false;
    public static String NAME = "ExamGuiItem";

    @FXML public VBox root;
    @FXML public Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private Exam exam = null;
    public void setExam(Exam exam){
        this.exam = exam;
        title.setText(this.exam.title);
    }
}
