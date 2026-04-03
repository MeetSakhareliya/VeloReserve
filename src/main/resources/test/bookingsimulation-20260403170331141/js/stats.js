var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "100000",
        "ok": "2390",
        "ko": "97610"
    },
    "minResponseTime": {
        "total": "0",
        "ok": "370",
        "ko": "0"
    },
    "maxResponseTime": {
        "total": "60041",
        "ok": "59999",
        "ko": "60041"
    },
    "meanResponseTime": {
        "total": "25642",
        "ok": "49920",
        "ko": "25048"
    },
    "standardDeviation": {
        "total": "19145",
        "ok": "15595",
        "ko": "18836"
    },
    "percentiles1": {
        "total": "28419",
        "ok": "55332",
        "ko": "28083"
    },
    "percentiles2": {
        "total": "39372",
        "ok": "57667",
        "ko": "38860"
    },
    "percentiles3": {
        "total": "60000",
        "ok": "59436",
        "ko": "60000"
    },
    "percentiles4": {
        "total": "60001",
        "ok": "59890",
        "ko": "60001"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 31,
    "percentage": 0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 26,
    "percentage": 0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 2333,
    "percentage": 2
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 97610,
    "percentage": 98
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1190.476",
        "ok": "28.452",
        "ko": "1162.024"
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
        "ok": "2390",
        "ko": "97610"
    },
    "minResponseTime": {
        "total": "0",
        "ok": "370",
        "ko": "0"
    },
    "maxResponseTime": {
        "total": "60041",
        "ok": "59999",
        "ko": "60041"
    },
    "meanResponseTime": {
        "total": "25642",
        "ok": "49920",
        "ko": "25048"
    },
    "standardDeviation": {
        "total": "19145",
        "ok": "15595",
        "ko": "18836"
    },
    "percentiles1": {
        "total": "28475",
        "ok": "55332",
        "ko": "28084"
    },
    "percentiles2": {
        "total": "39372",
        "ok": "57667",
        "ko": "38862"
    },
    "percentiles3": {
        "total": "60000",
        "ok": "59436",
        "ko": "60000"
    },
    "percentiles4": {
        "total": "60001",
        "ok": "59890",
        "ko": "60001"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 31,
    "percentage": 0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 26,
    "percentage": 0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 2333,
    "percentage": 2
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 97610,
    "percentage": 98
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1190.476",
        "ok": "28.452",
        "ko": "1162.024"
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
