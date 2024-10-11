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

        // Exibe o menu
        System.out.println("*********************************************************");
        System.out.println(" Seja bem-vindo/a ao Conversor de Moeda =]");
        System.out.println("*********************************************************");
        System.out.println("1) Dólar => Peso argentino");
        System.out.println("2) Peso argentino => Dólar");
        System.out.println("3) Dólar => Real brasileiro");
        System.out.println("4) Real brasileiro => Dólar");
        System.out.println("5) Dólar => Peso colombiano");
        System.out.println("6) Peso colombiano => Dólar");
        System.out.println("7) Sair");
        System.out.println("Escolha uma opção válida:");

        int opcao = sc.nextInt();

        if (opcao == 7) {
            System.out.println("Programa encerrado.");
            sc.close();
            return;
        }

        // Associa a escolha do usuário a uma moeda para busca
        String pesquisarMoeda = null;
        String moedaDeConversao = null;

        switch (opcao) {
            case 1: pesquisarMoeda = "USD"; moedaDeConversao = "ARS"; break;
            case 2: pesquisarMoeda = "ARS"; moedaDeConversao = "USD"; break;
            case 3: pesquisarMoeda = "USD"; moedaDeConversao = "BRL"; break;
            case 4: pesquisarMoeda = "BRL"; moedaDeConversao = "USD"; break;
            case 5: pesquisarMoeda = "USD"; moedaDeConversao = "COP"; break;
            case 6: pesquisarMoeda = "COP"; moedaDeConversao = "USD"; break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                sc.close();
                return;
        }

        // Realiza a busca na API
        try {
            String jsonResposta = api.busca(pesquisarMoeda);

            ConverterMoedaApi moedaApi = gson.fromJson(jsonResposta, ConverterMoedaApi.class);

            CalcularTaxas calcularTaxas = new CalcularTaxas(moedaApi);
            ConverterMoeda convertido = new ConverterMoeda(moedaApi);

            Map<String, Double> moedasFiltradas = filtro.filtrarMoedas(convertido.getTaxaConversao());
            String moedaSelecionada = filtro.selecionarMoeda(moedasFiltradas, moedaDeConversao);

            // Pede ao usuário o valor monetário para conversão
            System.out.println("Digite o valor para calcular a taxa da moeda:");
            double valorMonetario = sc.nextDouble();
            sc.close();

            // Calcula e exibe o valor convertido
            double valorConvertido = calcularTaxas.calcularConversao(moedaSelecionada, valorMonetario);
            System.out.printf("O Valor de %.2f [%s] corresponde ao valor final de =>>> %.2f [%s]\n", valorMonetario, pesquisarMoeda, valorConvertido, moedaSelecionada);

        } catch (CustomApiException | JsonSyntaxException | JsonIOException e) {
            System.out.println("Erro ao converter JSON: " + e.getMessage());
        }
    }
}
