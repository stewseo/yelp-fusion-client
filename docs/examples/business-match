---
layout: page
title: "Example Business Match Request"
permalink: /examples/business-match
---

        BusinessMatchRequest request = BusinessMatchRequest.of(a -> a
                .city("sf")
                .name("Brenda's+French+Soul+Food")
                .address1("625+polk+st")
                .state("ca")
                .country("US")
                .match_threshold("none")
        );

        BusinessMatchResponse response = client.businesses().businessMatch(request);

        BusinessMatch businessMatch = response.businesses().get(0);

        assertThat(businessMatch.coordinates().toString()).isEqualTo("Coordinates: {\"latitude\":37.7829016035273,\"longitude\":-122.419043442957}");
        assertThat(businessMatch.location().toString()).isEqualTo("Location: {\"address1\":\"652 Polk St\",\"address2\":\"\",\"address3\":\"\",\"city\":\"San Francisco\",\"zip_code\":\"94102\",\"country\":\"US\",\"display_address\":[\"652 Polk St\",\"San Francisco, CA 94102\"]}");

        assertThat(businessMatch.name()).isEqualTo("Brenda's French Soul Food");
        assertThat(businessMatch.alias()).isEqualTo("brendas-french-soul-food-san-francisco-6");
