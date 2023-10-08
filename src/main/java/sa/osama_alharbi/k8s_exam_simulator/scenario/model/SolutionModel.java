package sa.osama_alharbi.k8s_exam_simulator.scenario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sa.osama_alharbi.k8s_exam_simulator.scenario.interfaces.CheckSolutionIntr;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionModel {
    private List<CheckSolutionIntr> checkSolutionList = new ArrayList<>();
}
