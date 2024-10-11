package http;

import execption.CustomApiException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiCliente {
    private final String chaveApi = "b6a4139af95249d702d8eb1f";

    public String getChaveApi() {
        return chaveApi;
    }

    HttpClient client = HttpClient.newHttpClient();

    public String busca(String moeda) throws CustomApiException {
        String link = "https://v6.exchangerate-api.com/v6/" + getChaveApi() + "/latest/" + moeda;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(link)).build();

        try {
            HttpResponse<String> resposta = client.send(request, HttpResponse.BodyHandlers.ofString());
            return resposta.body();
        } catch (InterruptedException | IOException e) {
            throw new CustomApiException("Erro ao realizar a requisição", e);
        }
    }

    public String buscaLivre(String moeda, String moeda2, Double montante) throws CustomApiException {
        String link = "https://v6.exchangerate-api.com/v6/" + getChaveApi() + "/pair/" +moeda+"/"+moeda2+"/"+ montante;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(link)).build();

        try {
            HttpResponse<String> resposta = client.send(request, HttpResponse.BodyHandlers.ofString());
            return resposta.body();
        } catch (InterruptedException | IOException e) {
            throw new CustomApiException("Erro ao realizar a requisição", e);
        }
    }
}