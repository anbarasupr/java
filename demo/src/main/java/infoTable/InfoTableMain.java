package infoTable;

import java.io.File;
import java.io.IOException;
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
		String url = "F:\\git\\java\\java\\demo\\src\\main\\resources\\json\\infoTable.json";
		ObjectMapper mapper = new ObjectMapper();
		InfoTable infoTable = mapper.readValue(new File(url), InfoTable.class);
		System.out.println("infoTable: " + infoTable.getVersionId());
		List<InfoTableRow> infoTableRows = infoTable.getInfoTableRows();
		System.out.println("infoTableRows: " + infoTableRows.size());

		countryGrouping(infoTableRows, mapper);
		// infoTableRows.stream().flatMap(r -> r.getData().stream()).

	}

	public static void countryGrouping(List<InfoTableRow> infoTableRows, ObjectMapper mapper)
			throws JsonProcessingException {
		// Map<String, Long> result2 =
		// list.stream().collect(Collectors.toMap(Hosting::getName,
		// Hosting::getWebsites));
		/*
		 * List<Map<String, String>> convertInfoTableRowDataToMap =
		 * infoTableRows.stream() .map(r -> r.getData().stream()
		 * .collect(Collectors.toMap(InfoTableRowData::getColumnName,
		 * InfoTableRowData::getColumnValue))) .collect(Collectors.toList());
		 * 
		 * System.out.println("convertInfoTableRowDataToMap: " +
		 * mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
		 * convertInfoTableRowDataToMap));
		 */

		List<Map<String, String>> listMap = infoTableRows.stream()
				.map(r -> r.getData().stream()
						.collect(Collectors.toMap(InfoTableRowData::getColumnName, InfoTableRowData::getColumnValue)))
				.collect(Collectors.toList());

		// Map<String, List<Map<String, String>>> countryGroup =
		// listMap.stream().collect(Collectors.groupingBy(m->m.get("COUNTRY")));

		// Map<String, Map<String,List<Map<String, String>>>> countryPortTypeGroup =
		// listMap.stream().collect(Collectors.groupingBy(m->m.get("COUNTRY"),
		// Collectors.groupingBy(m->m.get("PORT_TYPE"))));

		Map<Pair<String, String>, List<Map<String, String>>> countryPortTypeGroup = listMap.stream()
				.collect(Collectors.groupingBy(e -> Pair.of(e.get("COUNTRY"), e.get("PORT_TYPE"))));

		Map<CustomPair, List<Map<String, String>>> countryPortTypeGroupV1 = listMap.stream().collect(
				Collectors.groupingBy(e -> new CustomPair(e.get("COUNTRY"), e.get("PORT_TYPE"), e.get("PORT_SPEED"))));

		Map<CustomPair, Double> countryPortTypeGroupV2 = listMap.stream()
				.collect(Collectors.groupingBy(
						e -> new CustomPair(e.get("COUNTRY"), e.get("PORT_TYPE"), e.get("PORT_SPEED")),
						Collectors.summingDouble(e -> Double.parseDouble(e.get("PRICE")))));

		System.out.println("countryPortTypeGroupV2: "
				+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(countryPortTypeGroupV2));

	}

}