import com.github.newswhip.linkstore.common.UrlUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUrlUtils {

    private final String GOOD_URL = "http://www.google.com";
    private final String CORRUPTED_URL = "http://google&123123%~`131123```";

    @Test
    public void shouldReturnDomainOfUrl() {
        assertEquals("google.com", UrlUtils.getDomain(GOOD_URL));
    }

    @Test
    public void shouldFindDomainName() {
        assertEquals("example.com", UrlUtils.getDomain("https://example.com/path/"));
        assertEquals("subpart.example.com", UrlUtils.getDomain("http://subpart.example.com/path/"));
        assertEquals("example.com", UrlUtils.getDomain("http://example.com"));
        assertEquals("example.com", UrlUtils.getDomain("http://example.com:18445/path/"));
        assertEquals("example.com", UrlUtils.getDomain("example.com/path/"));
        assertEquals("example.com", UrlUtils.getDomain("example.com"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailForCorruptedURL() {
        UrlUtils.getDomain(CORRUPTED_URL);
    }
}
