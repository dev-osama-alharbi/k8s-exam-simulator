package sa.osama_alharbi.k8s_exam_simulator.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import sa.osama_alharbi.k8s_exam_simulator.scenario.exams.Exam;

import java.net.URL;
import java.util.ResourceBundle;

public class Scenario extends InitView implements Initializable {
    public static boolean HAS_BEAN = false;
    public static String NAME = "Scenario";

    @FXML
    public WebView web;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        web.getEngine().load("http://localhost:8080/index");

    }

    private Exam exam = null;
    public void setExam(Exam exam){
        String url = "";
        if(exam != null){
            url = "http://localhost:8080/exam/question/"+exam.namespace+"/"+exam.scenario_id;
            System.out.println(url);
        }else{
            url = "http://localhost:8080/index";
        }

        web.getEngine().load(url);
    }
}
