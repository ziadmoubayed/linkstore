import com.github.newswhip.linkstore.cli.LinkStore;
import com.github.newswhip.linkstore.service.LinkScoreService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import picocli.CommandLine;

import static org.junit.Assert.assertEquals;

public class TestCliCommands {

    private String[] SAME_DOMAINS;
    private String[] DIFFERENT_DOMAINS;
    private Long RANDOM_SCORE;

    @Before
    public void init() {
        SAME_DOMAINS = new String[]{"http://google.com/23456", "https://google.com/123", "https://google.com/asdascz", "https://google.com/12adszxc"};
        DIFFERENT_DOMAINS = new String[]{"https://google1.com/123", "https://google2.com/123", "https://google3.com/123", "https://google4.com/123"};
        RANDOM_SCORE = (long) Math.floor(Math.random() * 1000);
    }

    @After
    public void flush() {
        LinkScoreService.INSTANCE.flushStore();
    }

    @Test
    public void shouldAddLinkWhenCallingAdd() {
        for (String SAME_DOMAIN : SAME_DOMAINS) {
            String cmd = "add " + SAME_DOMAIN + " " + RANDOM_SCORE;
            new CommandLine(new LinkStore()).execute(cmd.split(" "));
        }
        assertEquals(1, LinkScoreService.INSTANCE.getDomainStats().size());
    }

    @Test
    public void shouldRemoveLinkWhenCallingRemove() {
        for (String DIFFERENT_DOMAIN : DIFFERENT_DOMAINS) {
            String cmd = "add " + DIFFERENT_DOMAIN + " " + RANDOM_SCORE;
            new CommandLine(new LinkStore()).execute(cmd.split(" "));
        }
        assertEquals(DIFFERENT_DOMAINS.length, LinkScoreService.INSTANCE.getDomainStats().size());
        for (int i = 0; i < DIFFERENT_DOMAINS.length; i++) {
            String cmd = "remove " + DIFFERENT_DOMAINS[i];
            new CommandLine(new LinkStore()).execute(cmd.split(" "));
            assertEquals((DIFFERENT_DOMAINS.length - (i + 1)) , LinkScoreService.INSTANCE.getDomainStats().size());
        }
    }

    @Test
    public void reportShouldContainFieldsEqualToDistinctDomains() {
        for (String DIFFERENT_DOMAIN : DIFFERENT_DOMAINS) {
            String cmd = "add " + DIFFERENT_DOMAIN + " " + RANDOM_SCORE;
            new CommandLine(new LinkStore()).execute(cmd.split(" "));
        }
        assertEquals(DIFFERENT_DOMAINS.length, LinkScoreService.INSTANCE.getDomainStats().size());
    }
}
