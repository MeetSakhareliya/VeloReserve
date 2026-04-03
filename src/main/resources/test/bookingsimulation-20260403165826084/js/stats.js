var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name--1146707516",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "100000",
        "ok": "20119",
        "ko": "79881"
    },
    "minResponseTime": {
        "total": "456",
        "ok": "456",
        "ko": "10000"
    },
    "maxResponseTime": {
        "total": "60054",
        "ok": "59969",
        "ko": "60054"
    },
    "meanResponseTime": {
        "total": "20043",
        "ok": "31527",
        "ko": "17151"
    },
    "standardDeviation": {
        "total": "9593",
        "ok": "15286",
        "ko": "3844"
    },
    "percentiles1": {
        "total": "17798",
        "ok": "32204",
        "ko": "17796"
    },
    "percentiles2": {
        "total": "20002",
        "ok": "44786",
        "ko": "17804"
    },
    "percentiles3": {
        "total": "45518",
        "ok": "53946",
        "ko": "20004"
    },
    "percentiles4": {
        "total": "54267",
        "ok": "55637",
        "ko": "20011"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 47,
    "percentage": 0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 50,
    "percentage": 0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 20022,
    "percentage": 20
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 79881,
    "percentage": 80
},
    "meanNumberOfRequestsPerSecond": {
        "total": "699.301",
        "ok": "140.692",
        "ko": "558.608"
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
        "ok": "20119",
        "ko": "79881"
    },
    "minResponseTime": {
        "total": "456",
        "ok": "456",
        "ko": "10000"
    },
    "maxResponseTime": {
        "total": "60054",
        "ok": "59969",
        "ko": "60054"
    },
    "meanResponseTime": {
        "total": "20043",
        "ok": "31527",
        "ko": "17151"
    },
    "standardDeviation": {
        "total": "9593",
        "ok": "15286",
        "ko": "3844"
    },
    "percentiles1": {
        "total": "17798",
        "ok": "32129",
        "ko": "17796"
    },
    "percentiles2": {
        "total": "20002",
        "ok": "44784",
        "ko": "17804"
    },
    "percentiles3": {
        "total": "45513",
        "ok": "53946",
        "ko": "20004"
    },
    "percentiles4": {
        "total": "54266",
        "ok": "55637",
        "ko": "20011"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 47,
    "percentage": 0
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 50,
    "percentage": 0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 20022,
    "percentage": 20
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 79881,
    "percentage": 80
},
    "meanNumberOfRequestsPerSecond": {
        "total": "699.301",
        "ok": "140.692",
        "ko": "558.608"
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
