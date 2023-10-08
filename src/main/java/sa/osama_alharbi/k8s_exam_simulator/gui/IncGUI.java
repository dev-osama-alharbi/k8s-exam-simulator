package sa.osama_alharbi.k8s_exam_simulator.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import sa.osama_alharbi.k8s_exam_simulator.JavaFxLauncher;
import sa.osama_alharbi.k8s_exam_simulator.gui.controller.*;
import sa.osama_alharbi.k8s_exam_simulator.gui.view.IndexView;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class IncGUI {
    public Stage stage;
    public Scene scene;

    // TODO pain
    public AnchorPane gui_SplashScreen,gui_InitK8sConfig,gui_Exam,gui_Check,gui_CheckList,gui_CheckMd,gui_Scenario;
    public BorderPane gui_Root;
    private final GUIControllerService controller;


    public void start(Stage stage) {
        this.stage = stage;
        generateGUI();
    }


    private void generateGUI() {
        this.<Root, BorderPane>generateView(Root.NAME,Root.HAS_BEAN).forEach((i, o) -> {
            switch (i){
                case MAP_PAIN: gui_Root = (BorderPane) o;break;
                case MAP_CONTROLLER: controller.root = (Root) o;break;
            }
        });
        this.<SplashScreen, AnchorPane>generateView(SplashScreen.NAME,SplashScreen.HAS_BEAN).forEach((i, o) -> {
            switch (i){
                case MAP_PAIN: gui_SplashScreen = (AnchorPane) o;break;
                case MAP_CONTROLLER: controller.splashScreen = (SplashScreen) o;break;
            }
        });
        this.<InitK8sConfig, AnchorPane>generateView(InitK8sConfig.NAME,InitK8sConfig.HAS_BEAN).forEach((i, o) -> {
            switch (i){
                case MAP_PAIN: gui_InitK8sConfig = (AnchorPane) o;break;
                case MAP_CONTROLLER: controller.initK8sConfig = (InitK8sConfig) o;break;
            }
        });
        this.<CheckList, AnchorPane>generateView(CheckList.NAME,CheckList.HAS_BEAN).forEach((i, o) -> {
            switch (i){
                case MAP_PAIN: gui_CheckList = (AnchorPane) o;break;
                case MAP_CONTROLLER: controller.checkList = (CheckList) o;break;
            }
        });
        this.<CheckMd, AnchorPane>generateView(CheckMd.NAME,CheckMd.HAS_BEAN).forEach((i, o) -> {
            switch (i){
                case MAP_PAIN: gui_CheckMd = (AnchorPane) o;break;
                case MAP_CONTROLLER: controller.checkMd = (CheckMd) o;break;
            }
        });
        this.<Check, AnchorPane>generateView(Check.NAME,Check.HAS_BEAN).forEach((i, o) -> {
            switch (i){
                case MAP_PAIN: gui_Check = (AnchorPane) o;break;
                case MAP_CONTROLLER: controller.check = (Check) o;break;
            }
        });
        this.<Scenario, AnchorPane>generateView(Scenario.NAME,Scenario.HAS_BEAN).forEach((i, o) -> {
            switch (i){
                case MAP_PAIN: gui_Scenario = (AnchorPane) o;break;
                case MAP_CONTROLLER: controller.scenario = (Scenario) o;break;
            }
        });
        this.<ExamGui, AnchorPane>generateView(ExamGui.NAME, ExamGui.HAS_BEAN).forEach((i, o) -> {
            switch (i){
                case MAP_PAIN: gui_Exam = (AnchorPane) o;break;
                case MAP_CONTROLLER: controller.exam = (ExamGui) o;break;
            }
        });

        controller.root.root.setCenter(gui_SplashScreen);
//        controller.root.sendPane.setTop(gui_Send);
//        controller.root.connectPane.setCenter(gui_Connect);

        scene = new Scene(gui_Root);
        stage.setScene(scene);
        scene.getStylesheets().add(IndexView.class.getResource("jfx.css").toExternalForm());
        stage.show();
    }


    public FXMLLoader getLoader(URL url, boolean hasBean) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);
        if (hasBean) {
            loader.setControllerFactory(JavaFxLauncher.springContext::getBean);
        }
        return loader;
    }

    public Pane getPain(FXMLLoader loader) {
        try {
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <C extends InitView,P extends Pane> Map<String,Object> generateView(String name, boolean hasBeen) {
        FXMLLoader loader = getLoader(IndexView.class.getResource(name+".fxml"), hasBeen);
        Map<String,Object> m = new HashMap<>();
        m.put(MAP_PAIN,(P) getPain(loader));
        m.put(MAP_CONTROLLER,loader.<C>getController());
        return m;
    }
    public static final String MAP_PAIN = "PAIN";
    public static final String MAP_CONTROLLER = "CONTROLLER";
}
