import com.github.newswhip.linkstore.model.LinkVO;
import com.github.newswhip.linkstore.repo.LinkVORepository;
import com.github.newswhip.linkstore.repo.impl.InMemoryLinkScoreRepo;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestInMemoryRepo {

    private LinkVORepository linkVORepository;
    private LinkVO DEMO1;
    private LinkVO DEMO2;

    @Before
    public void init() {
        linkVORepository = new InMemoryLinkScoreRepo();
        DEMO1 = new LinkVO("http://google.com", "google.com", 10L);
        DEMO2 = new LinkVO("http://google1.com", "google1.com", 10L);
    }

    @Test
    public void shouldIncreaseInSizeWhenAdding() {
        assertEquals(0, linkVORepository.getLinks().size());
        linkVORepository.addLinkWithScore(DEMO1);
        assertEquals(1, linkVORepository.getLinks().size());
        linkVORepository.addLinkWithScore(DEMO2);
        assertEquals(2, linkVORepository.getLinks().size());
    }

    @Test
    public void shouldDecreaseInSizeWhenRemoving() {
        linkVORepository.addLinkWithScore(DEMO1);
        linkVORepository.addLinkWithScore(DEMO2);
        assertEquals(2, linkVORepository.getLinks().size());
        linkVORepository.removeLink(DEMO2);
        assertEquals(1, linkVORepository.getLinks().size());
        linkVORepository.removeLink(DEMO1);
        assertEquals(0, linkVORepository.getLinks().size());
    }

    @Test
    public void duplicatesShouldBeOverriden() {
        linkVORepository.addLinkWithScore(DEMO1);
        linkVORepository.addLinkWithScore(DEMO1);
        assertThat(linkVORepository.getLinks(), contains(DEMO1));
        assertThat(linkVORepository.getLinks().size(), equalTo(1));
    }
}
