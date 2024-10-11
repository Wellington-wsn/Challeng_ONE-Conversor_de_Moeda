import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import execption.CustomApiException;
import http.ApiCliente;
import model.ConverterMoeda;
import service.ConverterMoedaApi;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApiCliente api = new ApiCliente();
        Gson gson = new Gson();

        System.out.println("Digite a moeda para busca.");
        String pesquisarMoeda = sc.next();
        sc.close();

        try {
            String jsonResposta = api.busca(pesquisarMoeda);

            ConverterMoedaApi moedaApi = gson.fromJson(jsonResposta, ConverterMoedaApi.class);
            ConverterMoeda convertido = new ConverterMoeda(moedaApi);
            System.out.println(convertido);

        } catch (CustomApiException | JsonSyntaxException | JsonIOException e) {
            System.out.println("Erro ao converter JSON: " + e.getMessage());
        }

    }
}