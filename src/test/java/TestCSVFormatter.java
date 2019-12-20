import com.github.newswhip.linkstore.common.Constants;
import com.github.newswhip.linkstore.service.LinkScoreService;
import com.github.newswhip.linkstore.service.impl.CSVReportFormatter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCSVFormatter {

    @Test
    public void resultShouldContainHeaders() {
        var report = LinkScoreService.INSTANCE.getDomainStats();
        var formattedLines = new CSVReportFormatter().formatReport(report);
        assertTrue(formattedLines.size() > 0);
        assertEquals(Constants.CSV_HEADERS, formattedLines.get(0));
    }
}
