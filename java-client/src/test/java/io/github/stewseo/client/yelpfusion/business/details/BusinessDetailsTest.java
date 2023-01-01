package io.github.stewseo.client.yelpfusion.business.details;

import io.github.stewseo.client.yelpfusion.YelpFusionTestCase;
import io.github.stewseo.client.yelpfusion.business.Business;
import io.github.stewseo.client.yelpfusion.business.Hours;
import io.github.stewseo.client.yelpfusion.business.search.SearchBusiness;
import io.github.stewseo.client.yelpfusion.business.search.SearchBusinessResponse;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BusinessDetailsTest extends YelpFusionTestCase {

    private static final Logger logger = LoggerFactory.getLogger(BusinessDetailsTest.class);

    @Test
    void indexBusinessDetailsTest() throws Exception {

        CompletableFuture<List<SearchBusiness>> future = yelpFusionServiceCtx.getYelpFusionAsyncClient().businesses().search(s -> s
                                .location("sf")
                                .term("restaurants")
                                .sort_by("review_count")
                                .limit(3),
                        SearchBusiness.class)
                .whenComplete((response, exception) -> {
                            if (exception != null) {
                                logger.error("no businesses found: ", exception);
                            } else {
                                logger.info("number of businesses found: " + response.total());
                            }
                        }
                ).thenApply(SearchBusinessResponse::businesses);

        future.get().stream().map(SearchBusiness::id).forEach(this::businessDetails);

    }

    private void businessDetails(String id) {
        CompletableFuture<Business> future;
        try {
            future = yelpFusionServiceCtx.getYelpFusionAsyncClient().businesses().businessDetails(b -> b.id(id))
                    .whenComplete((response, exception) -> {

                        if (exception != null) {

                            logger.error("id: " + id + " did not return a business from the business details endpoint: ", exception);

                        } else {
                            logger.info("Business Details returned successfully : ");

                        }
                    }).thenApply(BusinessDetailsResponse::result);

            testBusinessDetails(future.get());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // By review count desc:
    private void testBusinessDetails(Business business) {
        System.out.println(business);

        assertThat(business.id()).isNotNull();

        assertThat(business.review_count()).isGreaterThanOrEqualTo(4000);

        assertThat(business.rating()).isGreaterThanOrEqualTo(3.5);

        assertThat(business.categories()).isNotNull();

        Hours hours = Objects.requireNonNull(business.hours()).get(0);

        assertThat(hours.open().size()).isGreaterThanOrEqualTo(5);
    }

    //17:09:12.944 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"lJAGnYzku5zSaLnQ_T6_GQ","alias":"brendas-french-soul-food-san-francisco-6","name":"Brenda's French Soul Food","image_url":"https://s3-media4.fl.yelpcdn.com/bphoto/VJ865E7ULQWSNjKhNG57VQ/o.jpg","url":"https://www.yelp.com/biz/brendas-french-soul-food-san-francisco-6?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14153458100","price":"$$","display_phone":"(415) 345-8100","is_closed":false,"rating":4.0,"review_count":11874,"location":{"address1":"652 Polk St","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94102","display_address":["652 Polk St","San Francisco, CA 94102"]},"coordinates":{"latitude":37.78291531984934,"longitude":-122.41889950001861},"transactions":["https://s3-media4.fl.yelpcdn.com/bphoto/VJ865E7ULQWSNjKhNG57VQ/o.jpg","https://s3-media3.fl.yelpcdn.com/bphoto/sTjgTEXukJKTw2NACCZWnw/o.jpg","https://s3-media2.fl.yelpcdn.com/bphoto/LY6X6ZCbPXXXl3bNggF_NQ/o.jpg","delivery"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"0800","end":"2000"},{"is_overnight":false,"day":1,"start":"0800","end":"1500"},{"is_overnight":false,"day":2,"start":"0800","end":"2000"},{"is_overnight":false,"day":3,"start":"0800","end":"2000"},{"is_overnight":false,"day":4,"start":"0800","end":"2000"},{"is_overnight":false,"day":5,"start":"0800","end":"1500"}],"hours_type":"REGULAR","is_open_now":true}],"categories":[{"alias":"breakfast_brunch","title":"Breakfast & Brunch"},{"alias":"southern","title":"Southern"},{"alias":"cajun","title":"Cajun/Creole"}]}
    //17:09:13.856 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"ri7UUYmx21AgSpRsf4-9QA","alias":"tartine-bakery-san-francisco-3","name":"Tartine Bakery","image_url":"https://s3-media4.fl.yelpcdn.com/bphoto/QRbC0TQ2zxTKXHt5NfpFCw/o.jpg","url":"https://www.yelp.com/biz/tartine-bakery-san-francisco-3?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14154872600","price":"$$","display_phone":"(415) 487-2600","is_closed":false,"rating":4.0,"review_count":8652,"location":{"address1":"600 Guerrero St","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94110","display_address":["600 Guerrero St","San Francisco, CA 94110"]},"coordinates":{"latitude":37.76131,"longitude":-122.42431},"transactions":["https://s3-media4.fl.yelpcdn.com/bphoto/QRbC0TQ2zxTKXHt5NfpFCw/o.jpg","https://s3-media1.fl.yelpcdn.com/bphoto/KAjrsNsqPj-Ox3mrOCNcvA/o.jpg","https://s3-media2.fl.yelpcdn.com/bphoto/AYdwR0wc8ZSTEZvhLscrhg/o.jpg","delivery"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"0800","end":"1700"},{"is_overnight":false,"day":1,"start":"0800","end":"1700"},{"is_overnight":false,"day":2,"start":"0800","end":"1700"},{"is_overnight":false,"day":3,"start":"0800","end":"1700"},{"is_overnight":false,"day":4,"start":"0800","end":"1700"},{"is_overnight":false,"day":5,"start":"0800","end":"1700"},{"is_overnight":false,"day":6,"start":"0800","end":"1700"}],"hours_type":"REGULAR","is_open_now":false}],"categories":[{"alias":"bakeries","title":"Bakeries"},{"alias":"cafes","title":"Cafes"},{"alias":"desserts","title":"Desserts"}]}
    //17:09:14.951 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"f-m7-hyFzkf0HSEeQ2s-9A","alias":"fog-harbor-fish-house-san-francisco-2","name":"Fog Harbor Fish House","image_url":"https://s3-media2.fl.yelpcdn.com/bphoto/by8Hh63BLPv_HUqRUdsp_w/o.jpg","url":"https://www.yelp.com/biz/fog-harbor-fish-house-san-francisco-2?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14159692010","price":"$$","display_phone":"(415) 969-2010","is_closed":false,"rating":4.5,"review_count":8645,"location":{"address1":"39 Pier","address2":"Ste a - 202","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94133","display_address":["39 Pier","Ste a - 202","San Francisco, CA 94133"]},"coordinates":{"latitude":37.80898821475503,"longitude":-122.41029651587517},"transactions":["https://s3-media2.fl.yelpcdn.com/bphoto/by8Hh63BLPv_HUqRUdsp_w/o.jpg","https://s3-media2.fl.yelpcdn.com/bphoto/cc5tnzyd03couTo7ReDGgQ/o.jpg","https://s3-media3.fl.yelpcdn.com/bphoto/J5NJ8-gclvTMzVmp3OrckA/o.jpg","restaurant_reservation"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"1100","end":"2100"},{"is_overnight":false,"day":1,"start":"1100","end":"2100"},{"is_overnight":false,"day":2,"start":"1100","end":"2100"},{"is_overnight":false,"day":3,"start":"1100","end":"2100"},{"is_overnight":false,"day":4,"start":"1100","end":"2200"},{"is_overnight":false,"day":5,"start":"1100","end":"2200"},{"is_overnight":false,"day":6,"start":"1100","end":"2100"}],"hours_type":"REGULAR","is_open_now":true}],"categories":[{"alias":"seafood","title":"Seafood"},{"alias":"wine_bars","title":"Wine Bars"},{"alias":"cocktailbars","title":"Cocktail Bars"}]}
    //17:09:16.219 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"oT08T3Vpn1I7jDmrBBRMTw","alias":"house-of-prime-rib-san-francisco","name":"House of Prime Rib","image_url":"https://s3-media4.fl.yelpcdn.com/bphoto/HLrjaMoAgYSac0vx71YpCA/o.jpg","url":"https://www.yelp.com/biz/house-of-prime-rib-san-francisco?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14158854605","price":"$$$","display_phone":"(415) 885-4605","is_closed":false,"rating":4.0,"review_count":8277,"location":{"address1":"1906 Van Ness Ave","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94109","display_address":["1906 Van Ness Ave","San Francisco, CA 94109"]},"coordinates":{"latitude":37.79338,"longitude":-122.4225},"transactions":["https://s3-media2.fl.yelpcdn.com/bphoto/3S2Kl9ZOS0icGjiwcHRWMw/o.jpg","https://s3-media2.fl.yelpcdn.com/bphoto/AQHmE5L6Cm7W6IZW0MyMag/o.jpg","https://s3-media2.fl.yelpcdn.com/bphoto/iKSpDzqFU5O3nxp8WNQl1A/o.jpg"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"1700","end":"2200"},{"is_overnight":false,"day":1,"start":"1700","end":"2200"},{"is_overnight":false,"day":2,"start":"1700","end":"2200"},{"is_overnight":false,"day":3,"start":"1700","end":"2200"},{"is_overnight":false,"day":4,"start":"1700","end":"2200"},{"is_overnight":false,"day":5,"start":"1600","end":"2200"},{"is_overnight":false,"day":6,"start":"1600","end":"2200"}],"hours_type":"REGULAR","is_open_now":true}],"categories":[{"alias":"tradamerican","title":"American (Traditional)"},{"alias":"steak","title":"Steakhouses"},{"alias":"wine_bars","title":"Wine Bars"}]}
    //17:09:16.947 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"M0JTO3oyu6gxh1mfFjU-dA","alias":"san-tung-san-francisco-2","name":"San Tung","image_url":"https://s3-media3.fl.yelpcdn.com/bphoto/rYZbin0NWrbrs0TYzI8rYA/o.jpg","url":"https://www.yelp.com/biz/san-tung-san-francisco-2?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14152420828","price":"$$","display_phone":"(415) 242-0828","is_closed":false,"rating":4.0,"review_count":7794,"location":{"address1":"1031 Irving St","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94122","display_address":["1031 Irving St","San Francisco, CA 94122"]},"coordinates":{"latitude":37.76378306790313,"longitude":-122.46900735179602},"transactions":["https://s3-media3.fl.yelpcdn.com/bphoto/rYZbin0NWrbrs0TYzI8rYA/o.jpg","https://s3-media1.fl.yelpcdn.com/bphoto/36vpZ6_gwQdrKUT86uNBzw/o.jpg","https://s3-media3.fl.yelpcdn.com/bphoto/GNpK6AivZ8IjlYXztqLWhg/o.jpg","delivery"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"1100","end":"1500"},{"is_overnight":false,"day":0,"start":"1630","end":"2030"},{"is_overnight":false,"day":3,"start":"1100","end":"1500"},{"is_overnight":false,"day":3,"start":"1630","end":"2030"},{"is_overnight":false,"day":4,"start":"1100","end":"1500"},{"is_overnight":false,"day":4,"start":"1630","end":"2030"},{"is_overnight":false,"day":5,"start":"1100","end":"1500"},{"is_overnight":false,"day":5,"start":"1630","end":"2030"},{"is_overnight":false,"day":6,"start":"1100","end":"1500"},{"is_overnight":false,"day":6,"start":"1630","end":"2030"}],"hours_type":"REGULAR","is_open_now":false}],"categories":[{"alias":"chinese","title":"Chinese"},{"alias":"chicken_wings","title":"Chicken Wings"},{"alias":"noodles","title":"Noodles"}]}
    //17:09:17.446 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"eYXwVR4mMAjzkJnm5wneHQ","alias":"burma-superstar-san-francisco-2","name":"Burma Superstar","image_url":"https://s3-media4.fl.yelpcdn.com/bphoto/Rt-zOS-uNY0cafsq1UeoDw/o.jpg","url":"https://www.yelp.com/biz/burma-superstar-san-francisco-2?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14153872147","price":"$$","display_phone":"(415) 387-2147","is_closed":false,"rating":4.0,"review_count":7357,"location":{"address1":"309 Clement St","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94118","display_address":["309 Clement St","San Francisco, CA 94118"]},"coordinates":{"latitude":37.782787322998,"longitude":-122.462539672852},"transactions":["https://s3-media4.fl.yelpcdn.com/bphoto/Rt-zOS-uNY0cafsq1UeoDw/o.jpg","https://s3-media2.fl.yelpcdn.com/bphoto/XPaJIVDiCW2V6Ofjf0CjSg/o.jpg","https://s3-media4.fl.yelpcdn.com/bphoto/DYKu9PS3i6g-rQLG1DNXqw/o.jpg","pickup","delivery"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"1130","end":"1430"},{"is_overnight":false,"day":0,"start":"1700","end":"2100"},{"is_overnight":false,"day":1,"start":"1130","end":"1430"},{"is_overnight":false,"day":1,"start":"1700","end":"2100"},{"is_overnight":false,"day":2,"start":"1130","end":"1430"},{"is_overnight":false,"day":2,"start":"1700","end":"2100"},{"is_overnight":false,"day":3,"start":"1130","end":"1430"},{"is_overnight":false,"day":3,"start":"1700","end":"2100"},{"is_overnight":false,"day":4,"start":"1130","end":"1430"},{"is_overnight":false,"day":4,"start":"1700","end":"2100"},{"is_overnight":false,"day":5,"start":"1130","end":"1430"},{"is_overnight":false,"day":5,"start":"1700","end":"2100"},{"is_overnight":false,"day":6,"start":"1130","end":"1430"},{"is_overnight":false,"day":6,"start":"1700","end":"2100"}],"hours_type":"REGULAR","is_open_now":true}],"categories":[{"alias":"burmese","title":"Burmese"}]}
    //17:09:19.010 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"Xg-FyjVKAN70LO4u4Z1ozg","alias":"hog-island-oyster-san-francisco-2","name":"Hog Island Oyster","image_url":"https://s3-media4.fl.yelpcdn.com/bphoto/Kozd3NJMSaT6S3J2kYAc1g/o.jpg","url":"https://www.yelp.com/biz/hog-island-oyster-san-francisco-2?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14153917117","price":"$$","display_phone":"(415) 391-7117","is_closed":false,"rating":4.5,"review_count":6769,"location":{"address1":"1 Ferry Bldg","address2":"","address3":"Shop 11","city":"San Francisco","country":"US","state":"CA","zip_code":"94111","display_address":["1 Ferry Bldg","Shop 11","San Francisco, CA 94111"]},"coordinates":{"latitude":37.795831,"longitude":-122.393303},"transactions":["https://s3-media4.fl.yelpcdn.com/bphoto/Kozd3NJMSaT6S3J2kYAc1g/o.jpg","https://s3-media4.fl.yelpcdn.com/bphoto/ahmWdH7P0UIc4lM1c8zhcQ/o.jpg","https://s3-media4.fl.yelpcdn.com/bphoto/sgLYE9xOtDpevDY3boRx7A/o.jpg"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"1100","end":"2000"},{"is_overnight":false,"day":1,"start":"1100","end":"2000"},{"is_overnight":false,"day":2,"start":"1100","end":"2000"},{"is_overnight":false,"day":3,"start":"1100","end":"2000"},{"is_overnight":false,"day":4,"start":"1100","end":"2000"},{"is_overnight":false,"day":5,"start":"1100","end":"2000"},{"is_overnight":false,"day":6,"start":"1100","end":"2000"}],"hours_type":"REGULAR","is_open_now":true}],"categories":[{"alias":"seafood","title":"Seafood"},{"alias":"seafoodmarkets","title":"Seafood Markets"},{"alias":"raw_food","title":"Live/Raw Food"}]}
    //17:09:19.743 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"mSMZJj2pFvttWLpcDmgrEA","alias":"tonys-pizza-napoletana-san-francisco","name":"Tony's Pizza Napoletana","image_url":"https://s3-media3.fl.yelpcdn.com/bphoto/rrBlePEDLrbD27VVE0Ze2A/o.jpg","url":"https://www.yelp.com/biz/tonys-pizza-napoletana-san-francisco?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14158359888","price":"$$","display_phone":"(415) 835-9888","is_closed":false,"rating":4.0,"review_count":6204,"location":{"address1":"1570 Stockton St","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94133","display_address":["1570 Stockton St","San Francisco, CA 94133"]},"coordinates":{"latitude":37.8003315377662,"longitude":-122.409053979377},"transactions":["https://s3-media3.fl.yelpcdn.com/bphoto/rrBlePEDLrbD27VVE0Ze2A/o.jpg","https://s3-media4.fl.yelpcdn.com/bphoto/o0Qjp8vsB_OGpLH2x9MRdg/o.jpg","https://s3-media4.fl.yelpcdn.com/bphoto/fyeHU9Usy5uBXj8HKoaclA/o.jpg","pickup","delivery"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"1200","end":"2100"},{"is_overnight":false,"day":1,"start":"1200","end":"2100"},{"is_overnight":false,"day":2,"start":"1200","end":"2200"},{"is_overnight":false,"day":3,"start":"1200","end":"2200"},{"is_overnight":false,"day":4,"start":"1200","end":"2300"},{"is_overnight":false,"day":5,"start":"1200","end":"2300"},{"is_overnight":false,"day":6,"start":"1200","end":"2200"}],"hours_type":"REGULAR","is_open_now":true}],"categories":[{"alias":"pizza","title":"Pizza"},{"alias":"italian","title":"Italian"},{"alias":"cocktailbars","title":"Cocktail Bars"}]}
    //17:09:20.877 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"WavvLdfdP6g8aZTtbBQHTw","alias":"gary-danko-san-francisco","name":"Gary Danko","image_url":"https://s3-media3.fl.yelpcdn.com/bphoto/eyYUz3Xl7NtcJeN7x7SQwg/o.jpg","url":"https://www.yelp.com/biz/gary-danko-san-francisco?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14157492060","price":"$$$$","display_phone":"(415) 749-2060","is_closed":false,"rating":4.5,"review_count":5793,"location":{"address1":"800 N Point St","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94109","display_address":["800 N Point St","San Francisco, CA 94109"]},"coordinates":{"latitude":37.80587,"longitude":-122.42058},"transactions":["https://s3-media3.fl.yelpcdn.com/bphoto/eyYUz3Xl7NtcJeN7x7SQwg/o.jpg","https://s3-media4.fl.yelpcdn.com/bphoto/1qgI44xDsgZyXxtcFgMeRQ/o.jpg","https://s3-media4.fl.yelpcdn.com/bphoto/5dbjkeTld05HumeToIAhRQ/o.jpg"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"1700","end":"2200"},{"is_overnight":false,"day":3,"start":"1700","end":"2200"},{"is_overnight":false,"day":4,"start":"1700","end":"2200"},{"is_overnight":false,"day":5,"start":"1700","end":"2200"},{"is_overnight":false,"day":6,"start":"1700","end":"2200"}],"hours_type":"REGULAR","is_open_now":false}],"categories":[{"alias":"newamerican","title":"American (New)"},{"alias":"french","title":"French"},{"alias":"wine_bars","title":"Wine Bars"}]}
    //17:09:21.636 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"ttarnopezxmp2ROB1N2PaA","alias":"nopa-san-francisco","name":"Nopa","image_url":"https://s3-media4.fl.yelpcdn.com/bphoto/QOc_docjeCrDHlckTaDE2A/o.jpg","url":"https://www.yelp.com/biz/nopa-san-francisco?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14158648643","price":"$$$","display_phone":"(415) 864-8643","is_closed":false,"rating":4.0,"review_count":5338,"location":{"address1":"560 Divisadero St","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94117","display_address":["560 Divisadero St","San Francisco, CA 94117"]},"coordinates":{"latitude":37.77483,"longitude":-122.43746},"transactions":["https://s3-media4.fl.yelpcdn.com/bphoto/QOc_docjeCrDHlckTaDE2A/o.jpg","https://s3-media1.fl.yelpcdn.com/bphoto/VF5PAA7ALdcjv0K1kx7ocg/o.jpg","https://s3-media1.fl.yelpcdn.com/bphoto/yrB1ghsnmlISCIgIP44jGQ/o.jpg","delivery"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"1730","end":"2200"},{"is_overnight":false,"day":1,"start":"1730","end":"2200"},{"is_overnight":false,"day":2,"start":"1730","end":"2200"},{"is_overnight":false,"day":3,"start":"1730","end":"2200"},{"is_overnight":false,"day":4,"start":"1730","end":"2300"}],"hours_type":"REGULAR","is_open_now":false}],"categories":[{"alias":"newamerican","title":"American (New)"},{"alias":"desserts","title":"Desserts"},{"alias":"cocktailbars","title":"Cocktail Bars"}]}
    //17:09:21.991 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"SGRmnarrNuVEsAjYdEoA0w","alias":"el-farolito-san-francisco-2","name":"El Farolito","image_url":"https://s3-media3.fl.yelpcdn.com/bphoto/LgAqjxM7WTeQgllDANDi7A/o.jpg","url":"https://www.yelp.com/biz/el-farolito-san-francisco-2?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14158247877","price":"$","display_phone":"(415) 824-7877","is_closed":false,"rating":4.0,"review_count":5320,"location":{"address1":"2779 Mission St","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94110","display_address":["2779 Mission St","San Francisco, CA 94110"]},"coordinates":{"latitude":37.75265,"longitude":-122.41812},"transactions":["https://s3-media3.fl.yelpcdn.com/bphoto/LgAqjxM7WTeQgllDANDi7A/o.jpg","https://s3-media4.fl.yelpcdn.com/bphoto/MGMSLkZ5eDQBcsYtbJ6GIQ/o.jpg","https://s3-media2.fl.yelpcdn.com/bphoto/jusgtdXIfMwUMZ1klPm2TA/o.jpg","delivery"],"hours":[{"open":[{"is_overnight":true,"day":0,"start":"1000","end":"0145"},{"is_overnight":true,"day":1,"start":"1000","end":"0145"},{"is_overnight":true,"day":2,"start":"1000","end":"0145"},{"is_overnight":true,"day":3,"start":"1000","end":"0145"},{"is_overnight":true,"day":4,"start":"1000","end":"0245"},{"is_overnight":true,"day":5,"start":"1000","end":"0245"},{"is_overnight":true,"day":6,"start":"1000","end":"0145"}],"hours_type":"REGULAR","is_open_now":true}],"categories":[{"alias":"mexican","title":"Mexican"}]}
    //17:09:22.543 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"n6L5VIGunR51-D55C-eYeQ","alias":"foreign-cinema-san-francisco","name":"Foreign Cinema","image_url":"https://s3-media3.fl.yelpcdn.com/bphoto/cw5y2LSOIE-EVNjKK_d7SQ/o.jpg","url":"https://www.yelp.com/biz/foreign-cinema-san-francisco?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14156487600","price":"$$$","display_phone":"(415) 648-7600","is_closed":false,"rating":4.0,"review_count":5283,"location":{"address1":"2534 Mission St","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94110","display_address":["2534 Mission St","San Francisco, CA 94110"]},"coordinates":{"latitude":37.75637,"longitude":-122.41925},"transactions":["https://s3-media3.fl.yelpcdn.com/bphoto/cw5y2LSOIE-EVNjKK_d7SQ/o.jpg","https://s3-media2.fl.yelpcdn.com/bphoto/MBHXUoMKUtzyiZqjZZzt0g/o.jpg","https://s3-media2.fl.yelpcdn.com/bphoto/jJucPns3xuTw9aUAFbbvqQ/o.jpg","delivery"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"1700","end":"2130"},{"is_overnight":false,"day":1,"start":"1700","end":"2200"},{"is_overnight":false,"day":2,"start":"1700","end":"2200"},{"is_overnight":false,"day":3,"start":"1700","end":"2200"},{"is_overnight":false,"day":4,"start":"1700","end":"2200"},{"is_overnight":false,"day":5,"start":"1100","end":"1400"},{"is_overnight":false,"day":5,"start":"1700","end":"2200"},{"is_overnight":false,"day":6,"start":"1100","end":"1400"},{"is_overnight":false,"day":6,"start":"1700","end":"2100"}],"hours_type":"REGULAR","is_open_now":true}],"categories":[{"alias":"breakfast_brunch","title":"Breakfast & Brunch"},{"alias":"mediterranean","title":"Mediterranean"},{"alias":"cocktailbars","title":"Cocktail Bars"}]}
    //17:09:23.030 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"PsY5DMHxa5iNX_nX0T-qPA","alias":"kokkari-estiatorio-san-francisco","name":"Kokkari Estiatorio","image_url":"https://s3-media2.fl.yelpcdn.com/bphoto/FTQfPJubJEtYeyHqwAsVKw/o.jpg","url":"https://www.yelp.com/biz/kokkari-estiatorio-san-francisco?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14159810983","price":"$$$","display_phone":"(415) 981-0983","is_closed":false,"rating":4.5,"review_count":4995,"location":{"address1":"200 Jackson St","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94111","display_address":["200 Jackson St","San Francisco, CA 94111"]},"coordinates":{"latitude":37.796996,"longitude":-122.399661},"transactions":["https://s3-media2.fl.yelpcdn.com/bphoto/FTQfPJubJEtYeyHqwAsVKw/o.jpg","https://s3-media2.fl.yelpcdn.com/bphoto/9lneWjyZG5BnQYZP9JNnlw/o.jpg","https://s3-media1.fl.yelpcdn.com/bphoto/qYjEM7ZmOtGFDY1uwFY4ZQ/o.jpg","pickup","delivery"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"1130","end":"1430"},{"is_overnight":false,"day":0,"start":"1700","end":"2200"},{"is_overnight":false,"day":1,"start":"1130","end":"1430"},{"is_overnight":false,"day":1,"start":"1700","end":"2200"},{"is_overnight":false,"day":2,"start":"1130","end":"1430"},{"is_overnight":false,"day":2,"start":"1700","end":"2200"},{"is_overnight":false,"day":3,"start":"1130","end":"1430"},{"is_overnight":false,"day":3,"start":"1700","end":"2200"},{"is_overnight":false,"day":4,"start":"1130","end":"1430"},{"is_overnight":false,"day":4,"start":"1700","end":"2300"},{"is_overnight":false,"day":5,"start":"1700","end":"2300"},{"is_overnight":false,"day":6,"start":"1700","end":"2200"}],"hours_type":"REGULAR","is_open_now":true}],"categories":[{"alias":"greek","title":"Greek"},{"alias":"mediterranean","title":"Mediterranean"}]}
    //17:09:23.648 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"gqVl3RprESEqkIPeJH0yOg","alias":"zazie-san-francisco","name":"Zazie","image_url":"https://s3-media2.fl.yelpcdn.com/bphoto/p-wLlWtS6hcPY5vXWkd92Q/o.jpg","url":"https://www.yelp.com/biz/zazie-san-francisco?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14155645332","price":"$$","display_phone":"(415) 564-5332","is_closed":false,"rating":4.0,"review_count":4984,"location":{"address1":"941 Cole St","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94117","display_address":["941 Cole St","San Francisco, CA 94117"]},"coordinates":{"latitude":37.76529,"longitude":-122.45015},"transactions":["https://s3-media2.fl.yelpcdn.com/bphoto/p-wLlWtS6hcPY5vXWkd92Q/o.jpg","https://s3-media4.fl.yelpcdn.com/bphoto/90GyOCSWPc_5e81ErFWaOw/o.jpg","https://s3-media2.fl.yelpcdn.com/bphoto/w_LlfXszmVNzKNylprtv_g/o.jpg","restaurant_reservation","delivery"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"0800","end":"1400"},{"is_overnight":false,"day":0,"start":"1700","end":"2130"},{"is_overnight":false,"day":1,"start":"0800","end":"1400"},{"is_overnight":false,"day":1,"start":"1700","end":"2130"},{"is_overnight":false,"day":2,"start":"0800","end":"1400"},{"is_overnight":false,"day":2,"start":"1700","end":"2130"},{"is_overnight":false,"day":3,"start":"0800","end":"1400"},{"is_overnight":false,"day":3,"start":"1700","end":"2130"},{"is_overnight":false,"day":4,"start":"0800","end":"1400"},{"is_overnight":false,"day":4,"start":"1700","end":"2200"},{"is_overnight":false,"day":5,"start":"0900","end":"1500"},{"is_overnight":false,"day":5,"start":"1700","end":"2200"},{"is_overnight":false,"day":6,"start":"0900","end":"1500"},{"is_overnight":false,"day":6,"start":"1700","end":"2130"}],"hours_type":"REGULAR","is_open_now":true}],"categories":[{"alias":"breakfast_brunch","title":"Breakfast & Brunch"},{"alias":"french","title":"French"},{"alias":"wine_bars","title":"Wine Bars"}]}
    //17:09:24.271 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"ka1_lat2boQwLMsOCiwGiA","alias":"r-and-g-lounge-san-francisco","name":"R&G Lounge","image_url":"https://s3-media4.fl.yelpcdn.com/bphoto/8b96FLWu65B9Zztwn_QpgQ/o.jpg","url":"https://www.yelp.com/biz/r-and-g-lounge-san-francisco?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14159827877","price":"$$","display_phone":"(415) 982-7877","is_closed":false,"rating":3.5,"review_count":4795,"location":{"address1":"631 Kearny St","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94108","display_address":["631 Kearny St","San Francisco, CA 94108"]},"coordinates":{"latitude":37.7941240989441,"longitude":-122.404937406342},"transactions":["https://s3-media4.fl.yelpcdn.com/bphoto/8b96FLWu65B9Zztwn_QpgQ/o.jpg","https://s3-media2.fl.yelpcdn.com/bphoto/vNaTnPsU6PKovT-5lEiUfA/o.jpg","https://s3-media1.fl.yelpcdn.com/bphoto/j8vSxF2kCKk6b39DAV9sTA/o.jpg","delivery"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"1100","end":"2100"},{"is_overnight":false,"day":1,"start":"1100","end":"2100"},{"is_overnight":false,"day":2,"start":"1100","end":"2100"},{"is_overnight":false,"day":3,"start":"1100","end":"2100"},{"is_overnight":false,"day":4,"start":"1100","end":"2130"},{"is_overnight":false,"day":5,"start":"1100","end":"2130"},{"is_overnight":false,"day":6,"start":"1100","end":"2100"}],"hours_type":"REGULAR","is_open_now":true}],"categories":[{"alias":"seafood","title":"Seafood"},{"alias":"cantonese","title":"Cantonese"}]}
    //17:09:24.784 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"_EncdQezAzcShATMFXL0dA","alias":"tropisueño-san-francisco-10","name":"Tropisueño","image_url":"https://s3-media3.fl.yelpcdn.com/bphoto/Eghg_03Dm7jDk1ogDvIJRg/o.jpg","url":"https://www.yelp.com/biz/tropisue%C3%B1o-san-francisco-10?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14159852264","price":"$$","display_phone":"(415) 985-2264","is_closed":false,"rating":4.0,"review_count":4734,"location":{"address1":"75 Yerba Buena Ln","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94103","display_address":["75 Yerba Buena Ln","San Francisco, CA 94103"]},"coordinates":{"latitude":37.7853008468227,"longitude":-122.403918653727},"transactions":["https://s3-media3.fl.yelpcdn.com/bphoto/Eghg_03Dm7jDk1ogDvIJRg/o.jpg","https://s3-media2.fl.yelpcdn.com/bphoto/hUZgDmue1VRfvIXai6I0wg/o.jpg","https://s3-media4.fl.yelpcdn.com/bphoto/JUlbGb4SDZjIg_Kfc3CwXw/o.jpg","delivery"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"1100","end":"2100"},{"is_overnight":false,"day":1,"start":"1100","end":"2100"},{"is_overnight":false,"day":2,"start":"1100","end":"2100"},{"is_overnight":false,"day":3,"start":"1100","end":"2200"},{"is_overnight":false,"day":4,"start":"1100","end":"2200"},{"is_overnight":false,"day":5,"start":"1100","end":"2200"},{"is_overnight":false,"day":6,"start":"1100","end":"2100"}],"hours_type":"REGULAR","is_open_now":true}],"categories":[{"alias":"cocktailbars","title":"Cocktail Bars"},{"alias":"tacos","title":"Tacos"}]}
    //17:09:25.579 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"8dUaybEPHsZMgr1iKgqgMQ","alias":"sotto-mare-san-francisco-4","name":"Sotto Mare","image_url":"https://s3-media3.fl.yelpcdn.com/bphoto/o3hIcGLMxV_5ynxEjGWGrw/o.jpg","url":"https://www.yelp.com/biz/sotto-mare-san-francisco-4?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14153983181","price":"$$","display_phone":"(415) 398-3181","is_closed":false,"rating":4.5,"review_count":4705,"location":{"address1":"552 Green St","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94133","display_address":["552 Green St","San Francisco, CA 94133"]},"coordinates":{"latitude":37.79979,"longitude":-122.40834},"transactions":["https://s3-media3.fl.yelpcdn.com/bphoto/o3hIcGLMxV_5ynxEjGWGrw/o.jpg","https://s3-media4.fl.yelpcdn.com/bphoto/qsKW1g6duc76aAatbv7OJA/o.jpg","https://s3-media4.fl.yelpcdn.com/bphoto/u_kkGKarL_H5SX0JB1pQ0w/o.jpg","pickup","delivery"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"1130","end":"2100"},{"is_overnight":false,"day":1,"start":"1130","end":"2100"},{"is_overnight":false,"day":2,"start":"1130","end":"2100"},{"is_overnight":false,"day":3,"start":"1130","end":"2100"},{"is_overnight":false,"day":4,"start":"1130","end":"2200"},{"is_overnight":false,"day":5,"start":"1130","end":"2200"},{"is_overnight":false,"day":6,"start":"1130","end":"2100"}],"hours_type":"REGULAR","is_open_now":true}],"categories":[{"alias":"seafood","title":"Seafood"},{"alias":"italian","title":"Italian"},{"alias":"bars","title":"Bars"}]}
    //17:09:26.050 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"MvAo0hj9FXOeNL5PwBZ0fQ","alias":"mamas-on-washington-square-san-francisco","name":"Mama's On Washington Square","image_url":"https://s3-media3.fl.yelpcdn.com/bphoto/ZR7nOhvDYma2yy8m0dUjEQ/o.jpg","url":"https://www.yelp.com/biz/mamas-on-washington-square-san-francisco?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14153626421","price":"$$","display_phone":"(415) 362-6421","is_closed":false,"rating":4.0,"review_count":4629,"location":{"address1":"1701 Stockton St","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94133","display_address":["1701 Stockton St","San Francisco, CA 94133"]},"coordinates":{"latitude":37.80154764480075,"longitude":-122.40958283157882},"transactions":["https://s3-media3.fl.yelpcdn.com/bphoto/ZR7nOhvDYma2yy8m0dUjEQ/o.jpg","https://s3-media2.fl.yelpcdn.com/bphoto/Dt7GugGSdO1hrcDBuUy6OA/o.jpg","https://s3-media1.fl.yelpcdn.com/bphoto/3SA-7pSLzh392vapjqDk0w/o.jpg","pickup"],"hours":[{"open":[{"is_overnight":false,"day":1,"start":"0800","end":"1400"},{"is_overnight":false,"day":2,"start":"0800","end":"1400"},{"is_overnight":false,"day":3,"start":"0800","end":"1400"},{"is_overnight":false,"day":4,"start":"0800","end":"1400"},{"is_overnight":false,"day":5,"start":"0800","end":"1500"},{"is_overnight":false,"day":6,"start":"0800","end":"1500"}],"hours_type":"REGULAR","is_open_now":false}],"categories":[{"alias":"breakfast_brunch","title":"Breakfast & Brunch"}]}
    //17:09:26.744 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"-DrR38H1Abk0wCyu9XOLug","alias":"sweet-maple-san-francisco","name":"Sweet Maple","image_url":"https://s3-media2.fl.yelpcdn.com/bphoto/etyS0Af-PS74CTwxly2uoA/o.jpg","url":"https://www.yelp.com/biz/sweet-maple-san-francisco?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14156559169","price":"$$","display_phone":"(415) 655-9169","is_closed":false,"rating":4.5,"review_count":4501,"location":{"address1":"2101 Sutter St","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94115","display_address":["2101 Sutter St","San Francisco, CA 94115"]},"coordinates":{"latitude":37.785735681426,"longitude":-122.43507770852},"transactions":["https://s3-media2.fl.yelpcdn.com/bphoto/etyS0Af-PS74CTwxly2uoA/o.jpg","https://s3-media3.fl.yelpcdn.com/bphoto/T1tZZwm2PwFoUKKjQ1HCeA/o.jpg","https://s3-media1.fl.yelpcdn.com/bphoto/vtaxO-dhXjo-s6GLjiRUVw/o.jpg","pickup","delivery"],"hours":[{"open":[{"is_overnight":false,"day":0,"start":"0800","end":"1430"},{"is_overnight":false,"day":1,"start":"0800","end":"1430"},{"is_overnight":false,"day":2,"start":"0800","end":"1430"},{"is_overnight":false,"day":3,"start":"0800","end":"1430"},{"is_overnight":false,"day":4,"start":"0800","end":"1430"},{"is_overnight":false,"day":5,"start":"0800","end":"1500"},{"is_overnight":false,"day":6,"start":"0800","end":"1500"}],"hours_type":"REGULAR","is_open_now":false}],"categories":[{"alias":"tradamerican","title":"American (Traditional)"},{"alias":"breakfast_brunch","title":"Breakfast & Brunch"},{"alias":"burgers","title":"Burgers"}]}
    //17:09:27.129 [I/O dispatcher 1] INFO  i.g.s.y.f.c.y.business.details.BusinessDetailsTest - Business Details returned successfully :
    //{"id":"JARsJVKLPgs_yC3cwDnp7g","alias":"la-taqueria-san-francisco-2","name":"La Taqueria","image_url":"https://s3-media1.fl.yelpcdn.com/bphoto/7LqVKYVg2GdEFKI2CFL4cA/o.jpg","url":"https://www.yelp.com/biz/la-taqueria-san-francisco-2?adjust_creative=ccj3y1UCH-4gsdWSMdEDOw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_lookup&utm_source=ccj3y1UCH-4gsdWSMdEDOw","phone":"+14152857117","price":"$$","display_phone":"(415) 285-7117","is_closed":false,"rating":4.0,"review_count":4457,"location":{"address1":"2889 Mission St","address2":"","address3":"","city":"San Francisco","country":"US","state":"CA","zip_code":"94110","display_address":["2889 Mission St","San Francisco, CA 94110"]},"coordinates":{"latitude":37.750876871204916,"longitude":-122.4181822457381},"transactions":["https://s3-media1.fl.yelpcdn.com/bphoto/7LqVKYVg2GdEFKI2CFL4cA/o.jpg","https://s3-media2.fl.yelpcdn.com/bphoto/lRRj9_qQ_m9yK_My5Wrmig/o.jpg","https://s3-media2.fl.yelpcdn.com/bphoto/quJVkgOw_pf70_enWelldw/o.jpg","delivery"],"hours":[{"open":[{"is_overnight":false,"day":2,"start":"1100","end":"2045"},{"is_overnight":false,"day":3,"start":"1100","end":"2045"},{"is_overnight":false,"day":4,"start":"1100","end":"2045"},{"is_overnight":false,"day":5,"start":"1100","end":"2045"},{"is_overnight":false,"day":6,"start":"1100","end":"1945"}],"hours_type":"REGULAR","is_open_now":true}],"categories":[{"alias":"mexican","title":"Mexican"}]}
}
