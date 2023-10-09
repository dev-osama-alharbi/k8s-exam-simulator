package sa.osama_alharbi.k8s_exam_simulator.constant;

import sa.osama_alharbi.k8s_exam_simulator.scenario.exams.*;

import java.util.ArrayList;
import java.util.List;

public class K8sScenarios {

    public static List<Exam> getAllExam(){
        List<Exam> lst = new ArrayList<>();
        lst.add(new Q1Exam());
        lst.add(new Q2Exam());
        lst.add(new Q3Exam());
        lst.add(new Q4Exam());
        lst.add(new Q5Exam());
        lst.add(new Q6Exam());
        lst.add(new Q7Exam());
        return lst;
    }
}
