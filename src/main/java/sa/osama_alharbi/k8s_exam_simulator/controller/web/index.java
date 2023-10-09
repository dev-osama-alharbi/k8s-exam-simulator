package sa.osama_alharbi.k8s_exam_simulator.controller.web;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sa.osama_alharbi.k8s_exam_simulator.constant.K8sConstant;
import sa.osama_alharbi.k8s_exam_simulator.gui.view.IndexView;
import sa.osama_alharbi.k8s_exam_simulator.scenario.exams.IndexExam;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
public class index {

    @GetMapping("/exam/question/{namespace}/{id}")
    public String question(Model model, @PathVariable("id") String id, @PathVariable("namespace") String namespace) throws IOException {
        String data = new String(IndexExam.class.getResourceAsStream("question/"+id+".md").readAllBytes(), StandardCharsets.UTF_8);
        data = data.replaceAll(System.getProperty("line.separator"),"\\\\n")
                .replaceAll("\\n", "\\\\n")
                .replaceAll("\"", "\\\"")
                .replaceAll("::namespace::",namespace);

        model.addAttribute("name", data);
        return "exam";
    }

    @GetMapping("/exam/solution/{namespace}/{id}")
    public String solution(Model model, @PathVariable("id") String id, @PathVariable("namespace") String namespace) throws IOException {
        String data = new String(IndexExam.class.getResourceAsStream("solution/"+id+".md").readAllBytes(), StandardCharsets.UTF_8);
        data = data.replaceAll(System.getProperty("line.separator"),"\\\\n")
                .replaceAll("\\n", "\\\\n")
                .replaceAll("\"", "\\\"")
                .replaceAll("::namespace::", namespace);


        model.addAttribute("name", data);
        return "exam";
    }
}
