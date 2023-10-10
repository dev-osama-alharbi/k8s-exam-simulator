package sa.osama_alharbi.k8s_exam_simulator.scenario.exams;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.proto.V1;
import sa.osama_alharbi.k8s_exam_simulator.constant.K8sConstant;
import sa.osama_alharbi.k8s_exam_simulator.scenario.interfaces.CheckSolutionIntr;
import sa.osama_alharbi.k8s_exam_simulator.scenario.model.ScenarioModel;

import java.util.HashMap;
import java.util.Map;


public class Q5Exam extends Exam {
    public Q5Exam() {
        super(new ScenarioModel());
        scenario_id = "secret";

        title = "Secret";

        //TODO
        namespace = K8sConstant.NAMESPACE_PREFIX+"secret";
        String podName = "secret-handler";
        String imageName = "nginx:1.25.2";
        String secret1Name = "secret1";
        String secret2Name = "secret2";


        scenario.getQuestionModel().setTitle("secret");

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

        //podName
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(2,"podName") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Pod v1Pod = api.readNamespacedPod(podName,namespace,null);
                    return v1Pod.getMetadata().getName().equals(podName);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //image
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(3,"image") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Pod v1Pod = api.readNamespacedPod(podName,namespace,null);
                    return v1Pod.getSpec().getContainers().get(0).getImage().equals(imageName);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //secret1Name
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(4,"secret1Name") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Secret v1Secret = api.readNamespacedSecret(secret1Name,namespace,null);
                    return v1Secret.getMetadata().getName().equals(secret1Name);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //secret1ValueUser
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(5,"secret1ValueUser") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Secret v1Secret = api.readNamespacedSecret(secret1Name,namespace,null);
                    return v1Secret.getData().containsKey("user") || v1Secret.getStringData().containsKey("user");
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //secret1ValuePass
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(6,"secret1ValuePass") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Secret v1Secret = api.readNamespacedSecret(secret1Name,namespace,null);
                    return v1Secret.getData().containsKey("pass") || v1Secret.getStringData().containsKey("pass");
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //podEnvSECRET1_USER
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(7,"podEnvSECRET1_USER") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Pod v1Pod = api.readNamespacedPod(podName,namespace,null);
                    boolean isEnvOk = false;

                    for (V1EnvVar v1EnvVar:v1Pod.getSpec().getContainers().get(0).getEnv()) {
                        if(v1EnvVar.getValueFrom().getSecretKeyRef().getName().equals(secret1Name)){
                            isEnvOk = true;
                        }
                    }

                    for (V1EnvFromSource v1EnvFromSource:v1Pod.getSpec().getContainers().get(0).getEnvFrom()) {
                        if(v1EnvFromSource.getSecretRef().getName().equals(secret1Name)){
                            isEnvOk = true;
                        }
                    }
                    for (V1EnvVar v1EnvVar:v1Pod.getSpec().getContainers().get(0).getEnv()) {
                        if(v1EnvVar.getValueFrom().getSecretKeyRef().getName().equals(secret1Name)
                                && v1EnvVar.getValueFrom().getSecretKeyRef().getKey().equals("user")
                                && v1EnvVar.getName().equals("SECRET1_USER")){
                            isEnvOk = true;
                        }
                    }
                    return isEnvOk;
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //podEnvSECRET1_PASS
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(8,"podEnvSECRET1_PASS") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Pod v1Pod = api.readNamespacedPod(podName,namespace,null);
                    boolean isEnvOk = false;
                    for (V1EnvFromSource v1EnvFromSource:v1Pod.getSpec().getContainers().get(0).getEnvFrom()) {
                        if(v1EnvFromSource.getSecretRef().getName().equals(secret1Name)){
                            isEnvOk = true;
                        }
                    }
                    for (V1EnvVar v1EnvVar:v1Pod.getSpec().getContainers().get(0).getEnv()) {
                        if(v1EnvVar.getValueFrom().getSecretKeyRef().getName().equals(secret1Name)
                                && v1EnvVar.getValueFrom().getSecretKeyRef().getKey().equals("pass")
                                && v1EnvVar.getName().equals("SECRET1_PASS")){
                            isEnvOk = true;
                        }
                    }
                    return isEnvOk;
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //secret2Name
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(9,"secret2Name") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Secret v1Secret = api.readNamespacedSecret(secret2Name,namespace,null);
                    return v1Secret.getMetadata().getName().equals(secret2Name);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //secret2ValueFile_secret2.conf
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(10,"secret2ValueFile_secret2.conf") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Secret v1Secret = api.readNamespacedSecret(secret2Name,namespace,null);
                    return v1Secret.getData().containsKey("secret2.conf");
                } catch (Exception e) {
                    return false;
                }
            }
        });

        //podSecretVolume/tmp/secret2/secret2.conf
        scenario.getSolutionModel().getCheckSolutionList().add(new CheckSolutionIntr(11,"podSecretVolume/tmp/secret2/secret2.conf") {
            @Override
            public boolean run() {
                CoreV1Api api = new CoreV1Api();
                try {
                    V1Pod v1Pod = api.readNamespacedPod(podName,namespace,null);
                    boolean isVolumesMount = false;
                    boolean isVolumesMountToPod = false;
                    boolean isVolumesMountToContainer = false;
                    String volumeName = "nul";
                    for(V1Volume v1Volume : v1Pod.getSpec().getVolumes()){
                        if(v1Volume.getSecret() != null){
                            if(v1Volume.getSecret().getSecretName().equals(secret2Name)){
                                isVolumesMountToPod = true;
                                volumeName = v1Volume.getName();
                            }
                        }
                    }
                    for(V1VolumeMount v1VolumeMount : v1Pod.getSpec().getContainers().get(0).getVolumeMounts()){
                        if(v1VolumeMount.getName().equals(volumeName)){
                            if(v1VolumeMount.getMountPath().equals("/tmp/secret2")){
                                isVolumesMountToContainer = true;
                            }
                        }
                    }
                    isVolumesMount = isVolumesMountToPod && isVolumesMountToContainer;
                    return isVolumesMount;
                } catch (Exception e) {
                    return false;
                }
            }
        });

    }
}