package sa.osama_alharbi.k8s_exam_simulator.scenario.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScenarioModel {
    private QuestionModel questionModel = new QuestionModel();
    private SolutionModel solutionModel = new SolutionModel();
}
