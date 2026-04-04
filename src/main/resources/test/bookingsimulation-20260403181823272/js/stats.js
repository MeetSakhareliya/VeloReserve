var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "100000",
        "ok": "37245",
        "ko": "62755"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "2",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "1629",
        "ok": "1323",
        "ko": "1629"
    },
    "meanResponseTime": {
        "total": "10",
        "ok": "11",
        "ko": "10"
    },
    "standardDeviation": {
        "total": "76",
        "ok": "66",
        "ko": "82"
    },
    "percentiles1": {
        "total": "4",
        "ok": "4",
        "ko": "4"
    },
    "percentiles2": {
        "total": "4",
        "ok": "5",
        "ko": "4"
    },
    "percentiles3": {
        "total": "6",
        "ok": "6",
        "ko": "6"
    },
    "percentiles4": {
        "total": "51",
        "ok": "322",
        "ko": "12"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 37197,
    "percentage": 37
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 33,
    "percentage": 0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 15,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 62755,
    "percentage": 63
},
    "meanNumberOfRequestsPerSecond": {
        "total": "166.667",
        "ok": "62.075",
        "ko": "104.592"
    }
},
contents: {
"req_book-ticket-req-347150194": {
        type: "REQUEST",
        name: "Book Ticket Request",
path: "Book Ticket Request",
pathFormatted: "req_book-ticket-req-347150194",
stats: {
    "name": "Book Ticket Request",
    "numberOfRequests": {
        "total": "100000",
        "ok": "37245",
        "ko": "62755"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "2",
        "ko": "1"
    },
    "maxResponseTime": {
        "total": "1629",
        "ok": "1323",
        "ko": "1629"
    },
    "meanResponseTime": {
        "total": "10",
        "ok": "11",
        "ko": "10"
    },
    "standardDeviation": {
        "total": "76",
        "ok": "66",
        "ko": "82"
    },
    "percentiles1": {
        "total": "4",
        "ok": "4",
        "ko": "4"
    },
    "percentiles2": {
        "total": "4",
        "ok": "5",
        "ko": "4"
    },
    "percentiles3": {
        "total": "6",
        "ok": "6",
        "ko": "6"
    },
    "percentiles4": {
        "total": "51",
        "ok": "322",
        "ko": "12"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 37197,
    "percentage": 37
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 33,
    "percentage": 0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 15,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 62755,
    "percentage": 63
},
    "meanNumberOfRequestsPerSecond": {
        "total": "166.667",
        "ok": "62.075",
        "ko": "104.592"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
