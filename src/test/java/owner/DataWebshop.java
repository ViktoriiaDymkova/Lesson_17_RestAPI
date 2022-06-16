package owner;

import org.aeonbits.owner.Config;

@Config.Sources("config/demowebshop.properties")

public interface DataWebshop extends Config {
    String login();
    String password();
}
