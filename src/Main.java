import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import execption.CustomApiException;
import http.ApiCliente;
import model.ConverterMoeda;
import service.CalcularTaxas;
import service.ConverterMoedaApi;
import service.FiltroDeMoedas;

import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApiCliente api = new ApiCliente();
        FiltroDeMoedas filtro = new FiltroDeMoedas();
        Gson gson = new Gson();

        System.out.println("Digite a moeda para busca.");
        String pesquisarMoeda = sc.next();

        try {
            String jsonResposta = api.busca(pesquisarMoeda);

            ConverterMoedaApi moedaApi = gson.fromJson(jsonResposta, ConverterMoedaApi.class);
            CalcularTaxas calcularTaxas = new CalcularTaxas(moedaApi);
            ConverterMoeda convertido = new ConverterMoeda(moedaApi);
            System.out.println(convertido);

            Map<String, Double> moedasFiltradas = filtro.filtrarMoedas(convertido.getTaxaConversao());


            System.out.println("Escolha moeda para conversão:");
            System.out.println(moedasFiltradas);
            String moedaSelecionada = filtro.selecionarMoeda(moedasFiltradas, sc.next().toUpperCase());

            System.out.println("Digite o valor para calcular a taxa da moeda:");
            double valorMonetario = sc.nextDouble();
            sc.close();
            double valorConvertido = calcularTaxas.calcularConversao(moedaSelecionada, valorMonetario);
            System.out.println("Valor convertido: " + valorConvertido);

        } catch (CustomApiException | JsonSyntaxException | JsonIOException e) {
            System.out.println("Erro ao converter JSON: " + e.getMessage());
        }

    }
}