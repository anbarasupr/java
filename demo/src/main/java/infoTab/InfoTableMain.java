package infoTab;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InfoTableMain {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		String url = "F:\\git\\java\\java\\demo\\src\\main\\resources\\jsn\\infoTablelarge.json";
		String inputUrl = "F:\\git\\java\\java\\demo\\src\\main\\resources\\jsn\\input.json";

		ObjectMapper mapper = new ObjectMapper();
		InfoTable infoTable = mapper.readValue(new File(url), InfoTable.class);
		System.out.println("infoTable: " + infoTable.getInfoTableId());
		List<String> infoTableRows = infoTable.getData().getInfoTableRecords().getData();
		System.out.println("infoTableRows: " + infoTableRows.size());

		Input input = mapper.readValue(new File(inputUrl), Input.class);

		System.out.println("sitetypes: " + input.getRequest().size());

		transform(infoTableRows, mapper, input);
	}

	public static void transform(List<String> infoTableRows, ObjectMapper mapper, Input input)
			throws JsonProcessingException {
		Map<String, List<InfoTablePOJO>> idsCountryMap = infoTableRows.stream().map(e -> e.split(","))
				.map(arr -> new InfoTablePOJO(arr[0], arr[1], arr[2], Double.parseDouble(arr[3]), null, null))
				.collect(Collectors.groupingBy(p -> p.getCountry()));
		System.out.println("transform idsCountryMap: " + idsCountryMap.size());
		// peek(e -> System.out.println(Arrays.asList(e)))
		// System.out.println("list: " +
		// mapper.writerWithDefaultPrettyPrinter().writeValueAsString(countryMap));

		/*
		 * // inputs
		 * 
		 * String country = "Argentina"; String portType = "Ethernet"; String portSpeed
		 * = "100 Mbps";
		 * 
		 * List<InfoTablePOJO> list = countryMap.get(country); List portSpeedList =
		 * list.stream() .filter(e->e.getPortType().equals(portType))
		 * .filter(e->e.getPortSpeed().equals(portSpeed)).collect(Collectors.toList());
		 * System.out.println("portSpeedList: " +
		 * mapper.writerWithDefaultPrettyPrinter().writeValueAsString(portSpeedList));
		 */

		// input.stream().flatMap(e->e.getCountryList()).filter(one->countryMap.entrySet().stream())
		List<Request> requestList = input.getRequest();
		Map<String, List<InfoTablePOJO>> map = new LinkedHashMap<>();
		for (Request request : requestList) {
			List<CountryService> countryList = request.getCountryList();

			for (CountryService countryService : countryList) {
				String country = countryService.getCountry();
				List<InfoTablePOJO> serviceList = countryService.getServiceList();
				for (InfoTablePOJO service : serviceList) {
					if (service.getType().equals("IDS")) {
						List<InfoTablePOJO> infoTableRowList = idsCountryMap.get(country);
						if (infoTableRowList != null && infoTableRowList.size() > 0) {
							String portType = service.getPortType();
							String portSpeed = service.getPortSpeed();
							List<InfoTablePOJO> filteredList = infoTableRowList.stream()
									.filter(e -> e.getPortType().equals(portType) && e.getPortSpeed().equals(portSpeed))
									.map(e -> {
										e.setSiteType(request.getSiteType());
										return e;
									}).collect(Collectors.toList());
							List<InfoTablePOJO> mappedList = map.putIfAbsent(request.getSiteType(), filteredList);
							if (mappedList != null) {
								mappedList.addAll(filteredList);
							}
							System.out.println("filteredList: " + filteredList);

						} else {

							// send error for that country
						}
					}
				}

			}

		}
		System.out.println("list: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map));

		Map<String, Double> sitePriceMap = new LinkedHashMap<>();
		// group at site type level
		map.entrySet().forEach(entry -> {
			List<InfoTablePOJO> list = entry.getValue();
			Map<String, Double> siteTypePriceMap = list.stream().collect(Collectors
					.groupingBy(item -> item.getSiteType(), Collectors.summingDouble(item -> item.getPrice())));
			sitePriceMap.putAll(siteTypePriceMap);			
		});
		
		System.out.println("sitePriceMap: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sitePriceMap));

	}

}