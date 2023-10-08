package sa.osama_alharbi.k8s_exam_simulator.gui.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import sa.osama_alharbi.k8s_exam_simulator.gui.GUIControllerService;
import sa.osama_alharbi.k8s_exam_simulator.gui.IncGUI;
import sa.osama_alharbi.k8s_exam_simulator.scenario.exams.Exam;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@RequiredArgsConstructor
public class Check extends InitView implements Initializable {
    public static boolean HAS_BEAN = true;
    public static String NAME = "Check";
    public final GUIControllerService controller;
    public final IncGUI gui;

    @FXML
    public BorderPane borderPane;
    @FXML
    public AnchorPane root;
    private FontAwesomeIconView fontAwesomeIconView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fontAwesomeIconView = new FontAwesomeIconView();
        fontAwesomeIconView.getStyleClass().addAll("icon-md","icon-main");

        Button btn = new Button("");
        root.getChildren().clear();
        root.getChildren().add(borderPane);
        borderPane.setCenter(gui.gui_CheckList);
        root.getChildren().add(btn);
        btn.setMinHeight(50.0);
        btn.setPrefHeight(50.0);
        btn.setMaxHeight(50.0);
        btn.setMinWidth(50.0);
        btn.setPrefWidth(50.0);
        btn.setMaxWidth(50.0);
        btn.getStyleClass().addAll("btn-main","btn-radius-all");
        btn.setGraphic(fontAwesomeIconView);
        AnchorPane.setBottomAnchor(btn,20.0);
        AnchorPane.setLeftAnchor(btn,20.0);
        btn.setOnAction(event -> {
            if(borderPane.getCenter().equals(gui.gui_CheckMd)){
                borderPane.setCenter(gui.gui_CheckList);
                fontAwesomeIconView.getStyleClass().clear();
                fontAwesomeIconView.getStyleClass().addAll("icon-md","icon-main");
            }else{
                borderPane.setCenter(gui.gui_CheckMd);
                fontAwesomeIconView.getStyleClass().clear();
                fontAwesomeIconView.getStyleClass().addAll("icon-list","icon-main");
            }
        });
        controller.checkMd.setExam(null);
    }

    private Exam exam = null;
    public void setExam(Exam exam){
        this.exam = exam;
        controller.checkMd.setExam(exam);
        controller.checkList.setExam(exam);
    }
}
