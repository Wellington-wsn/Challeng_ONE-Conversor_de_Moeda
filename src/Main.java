import execption.CustomApiException;
import http.ApiCliente;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ApiCliente api = new ApiCliente();

        System.out.println("Digite a moeda para busca.");
        String pesquisarMoeda = sc.next();
        sc.close();

        try {
            System.out.println(api.busca(pesquisarMoeda));
        } catch (CustomApiException e) {
            throw new RuntimeException(e);
        }

    }
}