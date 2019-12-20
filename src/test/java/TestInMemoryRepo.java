import com.github.newswhip.linkstore.model.LinkVO;
import com.github.newswhip.linkstore.repo.LinkVORepository;
import com.github.newswhip.linkstore.repo.impl.InMemoryLinkScoreRepo;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestInMemoryRepo {

    private LinkVORepository linkVORepository;
    private LinkVO DEMO1;
    private LinkVO DEMO2;

    @Before
    public void init() {
        linkVORepository = new InMemoryLinkScoreRepo();
        DEMO1 = new LinkVO("http://google.com", 10L);
        DEMO2 = new LinkVO("http://google1.com", 10L);
    }

    @Test
    public void shouldIncreaseInSizeWhenAdding() {
        assertEquals(0, linkVORepository.getLinks().count());
        linkVORepository.addLink(DEMO1);
        assertEquals(1, linkVORepository.getLinks().count());
        linkVORepository.addLink(DEMO2);
        assertEquals(2, linkVORepository.getLinks().count());
    }

    @Test
    public void shouldDecreaseInSizeWhenRemoving() {
        linkVORepository.addLink(DEMO1);
        linkVORepository.addLink(DEMO2);
        assertEquals(2, linkVORepository.getLinks().count());
        linkVORepository.removeLink(DEMO2);
        assertEquals(1, linkVORepository.getLinks().count());
        linkVORepository.removeLink(DEMO1);
        assertEquals(0, linkVORepository.getLinks().count());
    }

    @Test
    public void duplicatesShouldBeOverriden() {
        linkVORepository.addLink(DEMO1);
        linkVORepository.addLink(DEMO1);
        assertThat(linkVORepository.getLinks().collect(Collectors.toSet()), contains(DEMO1));
        assertThat(linkVORepository.getLinks().count(), equalTo(1L));
    }
}
