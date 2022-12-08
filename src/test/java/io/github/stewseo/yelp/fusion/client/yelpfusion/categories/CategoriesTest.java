package io.github.stewseo.yelp.fusion.client.yelpfusion.categories;

import io.github.stewseo.lowlevel.restclient.PrintUtils;
import io.github.stewseo.yelp.fusion.client.Elasticsearch;
import io.github.stewseo.yelp.fusion.client.YelpRequestTestCase;
import io.github.stewseo.yelp.fusion.client.yelpfusion.YelpFusionClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoriesTest {

    private final static Logger logger = LoggerFactory.getLogger(CategoriesTest.class);

    @Test
    public void autoCompleteTest() throws Exception {

        YelpRequestTestCase.initYelpFusionClient();

        YelpFusionClient client = YelpRequestTestCase.getYelpClient();

        GetCategoriesResponse response = client.categories(c -> c.locale("en_US"));

        for(Categories cat : response.categories()) {
            if (cat != null) {
                logger.info(" " + cat);
            }
        }

    }

}
