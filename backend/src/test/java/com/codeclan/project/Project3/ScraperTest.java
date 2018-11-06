package com.codeclan.project.Project3;

import com.codeclan.project.Project3.models.Scraper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class ScraperTest {

    @Test
    public void testGetsASIN() throws IOException {
        Scraper scraper = new Scraper("PS4 Controller");
        scraper.getProductASIN();
        assertEquals("B01GVQUX3U", scraper.getASIN());
    }
}
