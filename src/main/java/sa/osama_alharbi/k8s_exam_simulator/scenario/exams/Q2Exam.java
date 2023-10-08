package sa.osama_alharbi.k8s_exam_simulator.scenario.exams;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.BatchV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Job;
import io.kubernetes.client.openapi.models.V1Namespace;
import sa.osama_alharbi.k8s_exam_simulator.scenario.interfaces.CheckSolutionIntr;
import sa.osama_alharbi.k8s_exam_simulator.scenario.model.ScenarioModel;
import sa.osama_alharbi.k8s_exam_simulator.constant.K8sConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Q2Exam extends Exam {
    public Q2Exam() {
        super(new ScenarioModel());
        scenario_id = "job";

        title = "job";

        namespace = K8sConstant.NAMESPACE_PREFIX+"job";
        String jobName = "new-job";
        String imageName = "busybox:1.36.1";
        String containerName = "new-job-container";
        List<String> commands = new ArrayList<>();
        commands.add("sh");
        commands.add("-c");
        commands.add("sleep 2 && echo done");
        int completeCount = 3;
        int parallelCount = 2;
        HashMap<String,String> labels = new HashMap<>();
        labels.put("id","awesome-job");

        scenario.getQuestionModel().setTitle("job");

        //namespace
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(1,"namespace") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Namespace v1namespace = api.readNamespace(namespace,null);
                    return v1namespace != null;
                } catch (ApiException e) {
                    return false;
                }
            }
        });

        //image
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(2,"image") {
            @Override
            public boolean run() {
                BatchV1Api api = new BatchV1Api();
                try {
                    V1Job v1Job = api.readNamespacedJob(jobName,namespace,null);
                    return v1Job.getSpec().getTemplate().getSpec().getContainers().get(0).getImage().equals(imageName);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //execute
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(3,"command") {
            @Override
            public boolean run() {
                BatchV1Api api = new BatchV1Api();
                //TODO check command
//                try {
//                    V1Job v1Job = api.readNamespacedJob(jobName,namespace,null);
//                    System.out.println("v1Job command => "+v1Job.getSpec().getTemplate().getSpec().getContainers().get(0).getCommand());
//                    return v1Job.getSpec().getTemplate().getSpec().getContainers().get(0).getCommand().get(0).equals(imageName);
//                } catch (Exception e) {
//                    return false;
//                }
                return false;
            }
        });

        //completeCount
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(4,"completeCount") {
            @Override
            public boolean run() {
                BatchV1Api api = new BatchV1Api();
                try {
                    V1Job v1Job = api.readNamespacedJob(jobName,namespace,null);
                    return v1Job.getSpec().getCompletions() == completeCount;
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //parallelCount
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(5,"parallelCount") {
            @Override
            public boolean run() {
                BatchV1Api api = new BatchV1Api();
                try {
                    V1Job v1Job = api.readNamespacedJob(jobName,namespace,null);
                    return v1Job.getSpec().getParallelism() == parallelCount;
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //label
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(6,"labels") {
            @Override
            public boolean run() {
                BatchV1Api api = new BatchV1Api();
                try {
                    V1Job v1Job = api.readNamespacedJob(jobName,namespace,null);
                    boolean flag = true;
                    Map<String, String> jobLabels = v1Job.getSpec().getTemplate().getMetadata().getLabels();
                    for (Map.Entry<String, String> ent:labels.entrySet())
                        if(!jobLabels.containsKey(ent.getKey()))
                            flag = false;
                        else
                        if(!jobLabels.get(ent.getKey()).equals(ent.getValue()))
                            flag = false;

                    return flag;
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //jobName
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(7,"jobName") {
            @Override
            public boolean run() {
                BatchV1Api api = new BatchV1Api();
                try {
                    V1Job v1Job = api.readNamespacedJob(jobName,namespace,null);
                    // no need to "v1Job.getMetadata().getName()" if v1Job not null
                    // because I pass jobName on method "api.readNamespacedJob"
                    return v1Job.getMetadata().getName().equals(jobName);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //containerName
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(8,"containerName") {
            @Override
            public boolean run() {
                BatchV1Api api = new BatchV1Api();
                try {
                    V1Job v1Job = api.readNamespacedJob(jobName,namespace,null);
                    // no need to "v1Job.getMetadata().getName()" if v1Job not null
                    // because I pass jobName on method "api.readNamespacedJob"
                    return v1Job.getSpec().getTemplate().getSpec().getContainers().get(0).getName().equals(containerName);
                } catch (Exception e) {
                    return false;
                }
            }
        });

    }
}
