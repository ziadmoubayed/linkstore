import com.github.newswhip.linkstore.common.Mapper;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestMapper {

    @Test
    public void mapperShouldNotBeNull() {
        assertNotNull(Mapper.INSTANCE.getObjectMapper());
    }
}
