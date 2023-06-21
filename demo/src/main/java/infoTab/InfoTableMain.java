package infoTab;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
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
		String url = "F:\\git\\java\\java\\demo\\src\\main\\resources\\jsn\\infoTable.json";
		String inputUrl = "F:\\git\\java\\java\\demo\\src\\main\\resources\\jsn\\input.json";

		ObjectMapper mapper = new ObjectMapper();
		InfoTable infoTable = mapper.readValue(new File(url), InfoTable.class);
		System.out.println("infoTable: " + infoTable.getInfoTableId());
		List<String> infoTableRows = infoTable.getData().getInfoTableRecords().getData();
		System.out.println("infoTableRows: " + infoTableRows.size());
		
		List<Input> input = mapper.readValue(new File(inputUrl), List.class);

		System.out.println("sitetypes: " + input.size());

		transform(infoTableRows, mapper, input);
	}

	public static void transform(List<String> infoTableRows, ObjectMapper mapper, List<Input> input) throws JsonProcessingException {
		Map<String, List<InfoTablePOJO>> countryMap = infoTableRows.stream().map(e -> e.split(","))
				.map(arr -> new InfoTablePOJO(arr[0], arr[1], arr[2], Double.parseDouble(arr[3]), null))
				.collect(Collectors.groupingBy(p -> p.getCountry()));
		System.out.println("transform countryMap: " + countryMap.size());
		// peek(e -> System.out.println(Arrays.asList(e)))
		// System.out.println("list: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(countryMap));
		
		// inputs
		
		String country = "Argentina";
		String portType = "Ethernet";
		String portSpeed = "100 Mbps";
		
		List<InfoTablePOJO> list = countryMap.get(country);
		List portSpeedList = list.stream()
				.filter(e->e.getPortType().equals(portType))
				.filter(e->e.getPortSpeed().equals(portSpeed)).collect(Collectors.toList());
		System.out.println("portSpeedList: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(portSpeedList));
		
		

	}

}