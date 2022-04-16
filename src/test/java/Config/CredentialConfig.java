package Config;

import org.aeonbits.owner.Config;

@org.aeonbits.owner.Config.Sources("classpath:Config/credential.properties")
public interface CredentialConfig extends Config {
    String remoteURL();

    String user();

    String password();


}
