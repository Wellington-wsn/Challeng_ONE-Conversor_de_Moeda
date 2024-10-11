package service;

import java.util.Map;

public record ConverterMoedaLivreApi(String base_code, String target_code, Double conversion_rate, Double conversion_result) {
}
