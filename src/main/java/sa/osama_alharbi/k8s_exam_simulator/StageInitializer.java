package sa.osama_alharbi.k8s_exam_simulator;

import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import sa.osama_alharbi.k8s_exam_simulator.gui.IncGUI;

@Component
@RequiredArgsConstructor
public class StageInitializer implements ApplicationListener<JavaFxLauncher.StageReadyEvent> {
    private final IncGUI gui;

    @Override
    public void onApplicationEvent(JavaFxLauncher.StageReadyEvent event) {
        Stage stage = event.getStage();
        gui.start(stage);
    }
}
