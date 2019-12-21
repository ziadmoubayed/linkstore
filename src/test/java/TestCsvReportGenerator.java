import com.github.newswhip.linkstore.common.Constants;
import com.github.newswhip.linkstore.service.LinkScoreService;
import com.github.newswhip.linkstore.service.reports.ReportGenerator;
import com.github.newswhip.linkstore.service.reports.impl.CSVDomainReportGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestCsvReportGenerator {

    private ReportGenerator reportGenerator;

    @Before
    public void init() {
        reportGenerator = new CSVDomainReportGenerator();
    }

    @After
    public void after() {
        LinkScoreService.INSTANCE.flushStore();
    }

    @Test
    public void reportsShouldContainHeaders() {
        var report = reportGenerator.getReport();
        assertNotNull(report);
        assertTrue(report.size() > 0);
        assertTrue("Should include default headers", Constants.CSV_HEADERS.equals(report.stream().findFirst().get()));
    }
}
