import com.github.newswhip.linkstore.common.CommonUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUrlUtils {

    private final String GOOD_URL = "http://www.google.com";
    private final String CORRUPTED_URL = "http://google&123123%~`131123```";

    @Test
    public void shouldReturnDomainOfUrl() {
        assertEquals("google.com", CommonUtils.getDomain(GOOD_URL));
    }

    @Test
    public void shouldFindDomainName() {
        assertEquals("example.com", CommonUtils.getDomain("https://example.com/path/"));
        assertEquals("subpart.example.com", CommonUtils.getDomain("http://subpart.example.com/path/"));
        assertEquals("example.com", CommonUtils.getDomain("http://example.com"));
        assertEquals("example.com", CommonUtils.getDomain("http://example.com:18445/path/"));
        assertEquals("example.com", CommonUtils.getDomain("example.com/path/"));
        assertEquals("example.com", CommonUtils.getDomain("example.com"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailForCorruptedURL() {
        CommonUtils.getDomain(CORRUPTED_URL);
    }
}
