package io.github.stewseo.clients.yelpfusion;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import io.github.stewseo.clients.yelpfusion._types.Category;
import io.github.stewseo.clients.yelpfusion._types.Location;
import io.github.stewseo.clients.yelpfusion._types.SortBy;
import io.github.stewseo.clients.yelpfusion.businesses.search.Hit;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesRequest;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchBusinessesResult;
import io.github.stewseo.clients.yelpfusion.businesses.search.SearchResponse;
import io.github.stewseo.clients.yelpfusion.testcases.FunctionalTestCase;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchBusinessesAndStoreTest extends FunctionalTestCase {

    static int offset = 0;
    static int total = 0;
    static int LIMIT = 50;

    private final Set<String> categoriesGt = new HashSet<>();

    @Test
    public void testSearchBusinessRequest() throws Exception {

        Set<Category> categories = searchCategories();

        for (Category category : categories) {

            offset = 0;

            total = 0;

            do {

                SearchBusinessesRequest searchBusinessRequest = searchBusinessRequest(category, offset);

                SearchResponse<SearchBusinessesResult> searchBusinessResponse = yelpFusionClient.businesses()
                        .searchBusinesses(searchBusinessRequest, SearchBusinessesResult.class);

                assertThat(searchBusinessResponse).isNotNull();

                total = searchBusinessResponse.total();

                assertThat(total).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(1000);

                List<SearchBusinessesResult> results = searchBusinessResponse.hits().stream()
                        .map(hit -> Objects.requireNonNull(hit.source()))
                        .toList();

                for (SearchBusinessesResult searchBusinessResult : results) {

                    ElasticsearchCtx.indexRequest("yelp-fusion-restaurants-sf-search", searchBusinessResult);
                }
                offset += LIMIT;

                if (offset >= 950) {
                    categoriesGt.add(category.alias());
                }

            } while (offset <= 1000 && offset < (total + LIMIT));
        }

        System.out.println(categoriesGt.size() + ", categories gt 1000 results: " + categoriesGt);
    }

    private final Location location = Location.of(l -> l
            .city("san+francisco")
    );

    private SearchBusinessesRequest searchBusinessRequest(Category category, int offset) {

        return SearchBusinessesRequest.of(s -> s
                .term("restaurants")
                .limit(50)
                .offset(offset)
                .location(location)
                .categories(category)
                .sort_by(SortBy.REVIEW_COUNT.name())
        );
    }

    private Set<Category> searchCategories() {

        Set<Category> restCats = new HashSet<>();
        try {
            List<Category> categories = yelpFusionClient
                    .categories().all(c -> c
                            .locale("en_US"))
                    .categories();

            for(Category category: categories) {
                if (category.parent_aliases() != null) {
                    for (String parent : category.parent_aliases()) {
                        if (parent.equals("restaurants")) {
                            restCats.add(category);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return restCats;
    }

}
