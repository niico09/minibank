package minibank.bbva.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import minibank.bbva.impl.inter.ResultChange;
import minibank.bbva.impl.inter.ServiceChange;
import minibank.bbva.model.entitys.enums.TypeMoney;

@Component("ServiceChange")
public class ServiceImpl implements ServiceChange {

	HttpEntity<Void> httpEntity;
	RestTemplate template = new RestTemplate();
	HttpHeaders headers = new HttpHeaders();
	Map<String, String> vars = new HashMap<>();

	String baseUri = "https://api.apilayer.com/exchangerates_data/";
	String queryCambiar = "convert?to={to}&from={from}&amount={amount}";

	@Override
	public ResultChange change(TypeMoney de, TypeMoney a, Double monto) {

		vars.put("from", de.toString());
		vars.put("to", "ARS");
		vars.put("amount", monto.toString());

		headers.set("apikey", "3Byam4MM8LP1xvY3hMUK0JB0e0wcisC8");// 3Byam4MM8LP1xvY3hMUK0JB0e0wcisC8 - ${rest.apikey}
		httpEntity = new HttpEntity<>(headers);

		ResponseEntity<ResultChange> respuesta = template.exchange(baseUri + queryCambiar, HttpMethod.GET, httpEntity,
				ResultChange.class, vars);
		ResultChange rs = respuesta.getBody();

		return rs;
	}

}
