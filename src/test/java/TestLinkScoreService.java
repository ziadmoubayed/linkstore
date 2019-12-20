import com.github.newswhip.linkstore.common.UrlUtils;
import com.github.newswhip.linkstore.service.LinkScoreService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TestLinkScoreService {

    private String[] SAME_DOMAINS;
    private String[] DIFFERENT_DOMAINS;
    private Long RANDOM_SCORE;

    @Before
    public void init() {
        SAME_DOMAINS = new String[]{"http://google.com/23456", "https://google.com/123", "https://google.com/asdascz", "https://google.com/12adszxc"};
        DIFFERENT_DOMAINS = new String[]{"https://google1.com/123", "https://google2.com/123", "https://google3.com/123", "https://google4.com/123"};
        RANDOM_SCORE = (long) Math.floor(Math.random() * 1000);
    }

    @Test
    public void reportsShouldGetUpdatedWhenRemovingALink() {
        LinkScoreService.INSTANCE.flushStore();
        Arrays.stream(SAME_DOMAINS).forEach(domain -> LinkScoreService.INSTANCE.addLink(domain, RANDOM_SCORE));
        LinkScoreService.INSTANCE.removeLink(SAME_DOMAINS[0]);
        var report = LinkScoreService.INSTANCE.getDomainStats();
        assertEquals(Long.valueOf(RANDOM_SCORE * (SAME_DOMAINS.length - 1)), report.get(UrlUtils.getDomain(SAME_DOMAINS[0])).getValue());
    }

    @Test
    public void scoresShouldAccumulateWhenDomainsAreTheSame() {
        LinkScoreService.INSTANCE.flushStore();
        Arrays.stream(SAME_DOMAINS).forEach(domain -> LinkScoreService.INSTANCE.addLink(domain, RANDOM_SCORE));
        var report = LinkScoreService.INSTANCE.getDomainStats();
        assertEquals(Long.valueOf(RANDOM_SCORE * SAME_DOMAINS.length), report.get(UrlUtils.getDomain(SAME_DOMAINS[0])).getValue());
    }

    @Test
    public void reportShouldContainEntryForEveryDifferentDomain() {
        LinkScoreService.INSTANCE.flushStore();
        Arrays.stream(DIFFERENT_DOMAINS).forEach(domain -> LinkScoreService.INSTANCE.addLink(domain, RANDOM_SCORE));
        var report = LinkScoreService.INSTANCE.getDomainStats();
        assertEquals(report.size(), DIFFERENT_DOMAINS.length);
    }
}
