package sa.osama_alharbi.k8s_exam_simulator.service;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.k8s_exam_simulator.constant.PLATFORM;
import sa.osama_alharbi.k8s_exam_simulator.exceptions.NotSelectPlatformException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
public class K8sConfigService {

    public static final int PLATFORM_WIN = 1;
    public static final int PLATFORM_LINUX = 2;

    private KubeConfig kubeConfig = null;
    private ApiClient client = null;
    public boolean useDefaultConfig(String currentContext){
        try {
            kubeConfig.setContext(currentContext);
            client = ClientBuilder.kubeconfig(kubeConfig).build();
            Configuration.setDefaultApiClient(client);
            return true;
        } catch (IOException e) {
        }
        return false;
    }

    public HashMap<String,Object> defaultConfig(PLATFORM platform){
        if(platform == null) {
//            Platform.runLater(() -> {
//            });
            try {
                //TODO add msg
                throw new NotSelectPlatformException();
            } catch (NotSelectPlatformException e) {
            }
            return new HashMap<>();
        }
        HashMap<String,Object> map = new HashMap<>();
        ArrayList<String> contextsNames = new ArrayList<>();
        String configFile = "";
        switch (platform){
            case WINDOWS -> configFile = System.getProperty("user.home") + "\\.kube\\config";
            case LINUX,MAC -> configFile = System.getenv("home") + "/.kube/config";
        }
        try {
            kubeConfig = KubeConfig.loadKubeConfig(new FileReader(configFile));
            String currentContext = kubeConfig.getCurrentContext();
            kubeConfig.getContexts().forEach(o -> {
                contextsNames.add((String)((LinkedHashMap<String, Object>) o).get("name"));
            });
            map.put("currentContext",currentContext);
            map.put("contextsNames",contextsNames);
        } catch (FileNotFoundException e) {
        }

        return map;
    }

    public HashMap<String,Object> fileConfig(File file){
        HashMap<String,Object> map = new HashMap<>();
        ArrayList<String> contextsNames = new ArrayList<>();
        try {
            kubeConfig = KubeConfig.loadKubeConfig(new FileReader(file.getAbsoluteFile()));
            String currentContext = kubeConfig.getCurrentContext();
            kubeConfig.getContexts().forEach(o -> {
                contextsNames.add((String)((LinkedHashMap<String, Object>) o).get("name"));
            });
            map.put("currentContext",currentContext);
            map.put("contextsNames",contextsNames);
        } catch (FileNotFoundException e) {
        }

        return map;
    }


}
