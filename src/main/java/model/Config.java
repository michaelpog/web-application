package model;

public class Config {
    private String environment;
    private String version;

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getVersion() {
        return version;
    }


}
