package sa.osama_alharbi.k8s_exam_simulator.constant;

import sa.osama_alharbi.k8s_exam_simulator.scenario.exams.Exam;
import sa.osama_alharbi.k8s_exam_simulator.scenario.exams.Q1Exam;
import sa.osama_alharbi.k8s_exam_simulator.scenario.exams.Q2Exam;

import java.util.ArrayList;
import java.util.List;

public class K8sScenarios {

    public static List<Exam> getAllExam(){
        List<Exam> lst = new ArrayList<>();
        lst.add(new Q1Exam());
        lst.add(new Q2Exam());
        return lst;
    }
}
