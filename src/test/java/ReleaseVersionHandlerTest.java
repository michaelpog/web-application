import handlers.ReleaseVersionHandler;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ReleaseVersionHandlerTest {
    private static final String PATH_TO_CONFIG = "src/test/resources/config-example.cfg";

    @Test
    public void testVersionAndEnvironment() {
        ReleaseVersionHandler releaseVersionHandler
                = new ReleaseVersionHandler(PATH_TO_CONFIG);
        Assert.assertEquals("Environment: QA, Version: 1.4",
                releaseVersionHandler.getEnvironmentAndVersion());
    }
}
