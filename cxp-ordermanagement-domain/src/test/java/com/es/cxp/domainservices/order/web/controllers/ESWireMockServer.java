package com.es.cxp.domainservices.order.web.controllers;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import com.github.tomakehurst.wiremock.WireMockServer;

public class ESWireMockServer {
    private static WireMockServer wireMockServer;

    public static void start() {
        wireMockServer =
                new WireMockServer(wireMockConfig().bindAddress("localhost").dynamicPort());
        wireMockServer.start();
    }

    public static int httpPort() {
        return wireMockServer.port();
    }
}
