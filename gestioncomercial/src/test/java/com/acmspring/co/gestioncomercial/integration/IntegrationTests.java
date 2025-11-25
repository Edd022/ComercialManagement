package com.acmspring.co.gestioncomercial.integration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests {

    @LocalServerPort
    private int port;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api";
    }

    @Test
    void contextLoads() {
        assertThat(port).isGreaterThan(0);
    }

    @Test
    void testGetAllAlmacenes() {
        RestClient restClient = RestClient.create();
        String response = restClient.get()
                .uri(getBaseUrl() + "/almacen")
                .retrieve()
                .body(String.class);
        assertThat(response).isNotNull();
    }

    @Test
    void testGetAllCategorias() {
        RestClient restClient = RestClient.create();
        String response = restClient.get()
                .uri(getBaseUrl() + "/categoria")
                .retrieve()
                .body(String.class);
        assertThat(response).isNotNull();
    }

    @Test
    void testGetAllDepartamentos() {
        RestClient restClient = RestClient.create();
        String response = restClient.get()
                .uri(getBaseUrl() + "/departamento")
                .retrieve()
                .body(String.class);
        assertThat(response).isNotNull();
    }

    @Test
    void testGetAllCiudades() {
        RestClient restClient = RestClient.create();
        String response = restClient.get()
                .uri(getBaseUrl() + "/ciudad")
                .retrieve()
                .body(String.class);
        assertThat(response).isNotNull();
    }

    @Test
    void testGetAllProductos() {
        RestClient restClient = RestClient.create();
        String response = restClient.get()
                .uri(getBaseUrl() + "/producto")
                .retrieve()
                .body(String.class);
        assertThat(response).isNotNull();
    }

    @Test
    void testGetAllUsuarios() {
        RestClient restClient = RestClient.create();
        String response = restClient.get()
                .uri(getBaseUrl() + "/usuario")
                .retrieve()
                .body(String.class);
        assertThat(response).isNotNull();
    }

    @Test
    void testGetAllRoles() {
        RestClient restClient = RestClient.create();
        String response = restClient.get()
                .uri(getBaseUrl() + "/rol-usuario")
                .retrieve()
                .body(String.class);
        assertThat(response).isNotNull();
    }

    @Test
    void testGetAllVentas() {
        RestClient restClient = RestClient.create();
        String response = restClient.get()
                .uri(getBaseUrl() + "/venta")
                .retrieve()
                .body(String.class);
        assertThat(response).isNotNull();
    }

    @Test
    void testGetAllVentaProductos() {
        RestClient restClient = RestClient.create();
        String response = restClient.get()
                .uri(getBaseUrl() + "/venta-producto")
                .retrieve()
                .body(String.class);
        assertThat(response).isNotNull();
    }

    @Test
    void testGetAllAlmacenProductos() {
        RestClient restClient = RestClient.create();
        String response = restClient.get()
                .uri(getBaseUrl() + "/almacen-producto")
                .retrieve()
                .body(String.class);
        assertThat(response).isNotNull();
    }
}

