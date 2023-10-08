package sa.osama_alharbi.k8s_exam_simulator.gui.controller;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.k8s_exam_simulator.gui.IncGUI;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@RequiredArgsConstructor
public class SplashScreen extends InitView implements Initializable {
    public static boolean HAS_BEAN = true;
    public static String NAME = "SplashScreen";
    private final IncGUI incGUI;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(() -> {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            Platform.runLater(() -> {
                incGUI.gui_Root.setCenter(incGUI.gui_InitK8sConfig);
            });
        }).start();
    }
}
