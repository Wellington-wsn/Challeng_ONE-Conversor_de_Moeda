package model;

import service.ConverterMoedaApi;

import java.util.Map;

public class ConverterMoeda {
    private String codigoBase;
    private Map<String, Double> taxaConversao;

    public ConverterMoeda(ConverterMoedaApi moedaApi) {
        this.codigoBase = moedaApi.base_code();
        this.taxaConversao = moedaApi.conversion_rates();
    }

    public String getCodigoBase() {
        return codigoBase;
    }

    public void setCodigoBase(String codigoBase) {
        this.codigoBase = codigoBase;
    }

    public Map<String, Double> getTaxaConversao() {
        return taxaConversao;
    }

    public void setTaxaConversao(Map<String, Double> taxaConversao) {
        this.taxaConversao = taxaConversao;
    }

    @Override
    public String toString() {
        return "ConverterMoeda{" +
                "codigoBase='" + codigoBase + '\'' +
                ", taxaConversao=" + taxaConversao +
                '}';
    }
}
