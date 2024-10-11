package service;

import java.util.Map;

public record ConverterMoedaApi(String base_code, Map<String, Double> conversion_rates) {
}
