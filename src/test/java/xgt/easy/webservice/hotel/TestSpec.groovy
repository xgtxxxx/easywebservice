package xgt.easy.webservice.hotel

import spock.lang.Shared
import spock.lang.Specification
import xgt.easy.webservice.Client
import xgt.easy.webservice.adapters.SimpleStringAdapter
import xgt.easy.webservice.handler.RequestHandlerChainFactory
import xgt.easy.webservice.hotel.request.AutoSuggestRequest
import xgt.easy.webservice.hotel.request.BookPolicyRequest
import xgt.easy.webservice.hotel.request.HotelSearchRequest
import xgt.easy.webservice.hotel.request.PreBookRequest
import xgt.easy.webservice.httpclient.EntityAdapter.HttpSimpleEntityAdapter
import xgt.easy.webservice.httpclient.client.HttpSimpleClient
import xgt.easy.webservice.httpclient.handler.JsonEntityHandler
import xgt.easy.webservice.model.ParameterPair
import xgt.easy.webservice.model.PostContentType

class TestSpec extends Specification {

    @Shared
    Client client;

    void setupSpec(){
        client = new HttpSimpleClient(
            handlerFactory: new RequestHandlerChainFactory(),
            headers: [new ParameterPair("X-Api-Key","8bd7f1db-f492-4545-e4ef-cbc04b7b48e8")],
            globalHost: "http://data.zumata.com",
            entityAdapter:new HttpSimpleEntityAdapter(
                    handlers: ["application/json" : new JsonEntityHandler()]
            )
        )
    }

    void "autosuggest"(){
        given:
        def request = new AutoSuggestRequest(
                term: "atl",
                types: "city"
        )
        when:
        def json = client.doRequest(request,new SimpleStringAdapter());
        then:
        println(json)
    }

    void "search"(){
        given:
        def request = new HotelSearchRequest(
                host:"https://test-api-v3.zumata.com",
                ctx: "/search",
                destId: "UHjy",
                checkInDate:"2016-03-26",
                checkOutDate: "2016-03-29",
                roomCount: 3,
                adultCount: 3,
                childCount: 1,
                currency: "USD"
        )
        when:
        def start = new Date();
        def json = client.doRequest(request,new SimpleStringAdapter());
        while (json.indexOf("\"status\":\"complete\"")==-1){
            json = client.doRequest(request,new SimpleStringAdapter());
        }
        def end = new Date()
        then:
        println(json)
        println(end.getTime()-start.getTime())
    }

    void "search detail"(){
        given:
        def request = new HotelSearchRequest(
                host:"https://test-api-v3.zumata.com",
                ctx: "/search",
                method: "dy4Y",
                destId: "UHjy",
                checkInDate:"2016-03-26",
                checkOutDate: "2016-03-29",
                roomCount: 3,
                adultCount: 3,
                childCount: 1,
                currency: "USD"
        )
        when:
        def start = new Date();
        def json = client.doRequest(request,new SimpleStringAdapter());
        def end = new Date()
        then:
        println(json)
        println(end.getTime()-start.getTime())
    }
    void "booking_policy"(){
        given:
        def request = new BookPolicyRequest(
                host:"https://test-api-v3.zumata.com",
                method: "booking_policy",
                applicationType: PostContentType.JSON,
                        search:[
                                 destination_id:"UHjy",
                                 check_in_date : "2016-03-26",
                                 check_out_date: "2016-03-29",
                                 room_count : 3,
                                 adult_count:3,
                                 child_count:1,
                                 currency: "USD"
                        ],
                        packages: [
                                hotel_id: "dy4Y",
                                room_details: [
                                    description: "Studio Room Queen Bed",
                                    food: 1,
                                    non_refundable: false,
                                    room_type: "Studio",
                                    room_view: "",
                                    beds: [
                                        "queen": 1
                                    ]
                                ],
                                "booking_key": "1ff00960",
                                "room_rate": 494.56,
                                "room_rate_currency": "USD",
                                "chargeable_rate": 507.23,
                                "chargeable_rate_currency": "USD",
                                "taxes_and_fees": [
                                    "tax_and_service_fee": [
                                        "currency": "USD",
                                        "value": 94.68
                                    ],
                                    "total": [
                                        "currency": "USD",
                                        "value": 94.68
                                    ]
                                ],
                                "bundled_rate": false
                        ],
                        config:[non_blocking:false]
        )
        when:
        def json = client.doRequest(request,new SimpleStringAdapter())
        then:
        println(json)
    }

    void "pre book"(){
        given:
        def request = new PreBookRequest(
                host:"https://test-api-v3.zumata.com",
                method: "pre_book",
                applicationType: PostContentType.JSON,
                        guest:[
                                first_name:"xgt",
                                last_name : "Gavin",
                                contact_no: "1234567890"
                        ],
                        policyId:"28405cf2-4df5-556b-5235-281458641610"
        )
        when:
        def json = client.doRequest(request,new SimpleStringAdapter())
        then:
        println(json)

    }

    void "book"(){
        given:
        def request = new PreBookRequest(
                host:"https://test-api-v3.zumata.com",
                method: "93f6cefa-5d40-4b2c-5d1c-25739e440226",
                ctx: "/book"
        )
        when:
        def json = client.doRequest(request,new SimpleStringAdapter())
        then:
        println(json)
    }
}
