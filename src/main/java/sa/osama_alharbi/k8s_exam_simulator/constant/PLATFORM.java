package sa.osama_alharbi.k8s_exam_simulator.constant;

public enum PLATFORM{
    WINDOWS("windows"),LINUX("linux"), MAC("mac");
    public final String name;
    PLATFORM(String name){
        this.name = name;
    }
}
