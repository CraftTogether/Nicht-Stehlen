package xyz.crafttogether.nichtstehlen.configuration;

public class Config {
    private int configVersion;

    public Config(int configVersion) {
        this.configVersion = configVersion;
    }

    public int getConfigVersion() {
        return configVersion;
    }
}
