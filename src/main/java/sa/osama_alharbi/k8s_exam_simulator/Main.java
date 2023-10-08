package sa.osama_alharbi.k8s_exam_simulator;


import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("sa.osama_alharbi.k8s_exam_simulator")
public class Main {
    public static void main(String[] args) {
        System.setProperty("javafx.version","18");
        Application.launch(JavaFxLauncher.class,args);
    }
}
