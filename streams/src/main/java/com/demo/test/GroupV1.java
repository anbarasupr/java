package com.demo.test;
import java.io.File;
import java.io.IOException;
import java.lang.runtime.ObjectMethods;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GroupV1 {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		String url = "F:\\git\\java\\java\\demo\\src\\main\\resources\\json\\input.json";
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> listMap = mapper.readValue(new File(url), List.class);
		System.out.println("listMap: " + listMap);

		Map<Object, List<Map<String, Object>>> appGrouping = listMap.stream().collect(
				Collectors.groupingBy(m -> m.get("project"), Collectors.mapping(m2 -> m2, Collectors.toList())));

		System.out.println("appGrouping: " + mapper.writeValueAsString(appGrouping));

		test(listMap, mapper);

		/*
		 * List<Map<String, Object>> finalData = new ArrayList<>();
		 * 
		 * appGrouping.forEach((k, v) -> { Map<String, Object> data = new HashMap<>();
		 * data.put("project", k); data.put("types", new ArrayList<>());
		 * v.stream().collect(Collectors.groupingBy(m -> m.get("workType"),
		 * Collectors.summingDouble(m -> getDoubleVal(m.get("effort"))))).forEach((k2,
		 * v2) -> { Map<String, Object> data2 = new HashMap<>(); data2.put("workType",
		 * k2); data2.put("effort", v2); getFromMap(data, "types",
		 * List.class).add(data2); // this is a util method I wrote to get a // value
		 * from a map Double totalEffort = Objects.requireNonNull(getFromMap(data,
		 * "effort", Double.class)) + v2; data.put("effort", totalEffort); });
		 * finalData.add(data); });
		 */

	}

	public static void test(List<Map<String, Object>> transformedData, ObjectMapper mapper)
			throws JsonProcessingException {
 		Map<String, Map<String, Object>> tempMap = new HashMap<String, Map<String, Object>>();

		transformedData.forEach(i -> {
			Double currentEffort = (Double) i.get("effort");

			Map<String, Object> subMap = tempMap.computeIfAbsent((String) i.get("project"),
					(k) -> new HashMap<String, Object>() {
						{
							put("project", (String) i.get("project"));
							put("effort", 0.0D);
							put("types", new HashMap<String, Double>());
						}
					});

			subMap.merge("effort", currentEffort, (o, n) -> (Double) o + (Double) n);

			((Map<String, Double>) subMap.get("types")).merge((String) i.get("workType"), currentEffort,
					(o, n) -> (Double) o + (Double) n);
		});
		// List<Map<String, Object>> finalData = (List<Map<String, Object>>)
		// tempMap.values();

		System.out.println("finalData: " + mapper.writeValueAsString(tempMap));
		
		test2();

	}

	public static void test1(List<Map<String, Object>> transformedData, ObjectMapper mapper)
			throws JsonProcessingException {

		Map<String, Map<String, Object>> tempMap = new HashMap<String, Map<String, Object>>();

		transformedData.forEach(i -> {
			Double currentEffort = (Double) i.get("effort");

			Map<String, Object> subMap = tempMap.computeIfAbsent((String) i.get("project"),
					(k) -> new HashMap<String, Object>() {
						{
							put("project", (String) i.get("project"));
							put("effort", 0.0D);
							put("types", new HashMap<String, Double>());
							//put("typeList", new ArrayList<Map<String, Double>>());

						}
					});

			subMap.merge("effort", currentEffort, (o, n) -> (Double) o + (Double) n);

			((Map<String, Double>) subMap.get("types")).merge((String) i.get("workType"), currentEffort,
					(o, n) -> (Double) o + (Double) n);
			
			//List<Map<String, Double>> typeList = (List<Map<String, Double>>) subMap.get("typeList");
			//dtypeList.stream().filter(null)
			

		});
		// List<Map<String, Object>> finalData = (List<Map<String, Object>>)
		// tempMap.values();

		System.out.println("finalDataTest1: " + mapper.writeValueAsString(tempMap));

	}

	public static void test2() {
		// create an HashMap
		HashMap<String, Integer> prices = new HashMap<>();

		// insert entries to the HashMap
		prices.put("Shoes", 200);
		prices.put("Bag", 300);
		prices.put("Pant", 150);
		prices.put("Shirt", 500);

		System.out.println("HashMap: " + prices);

		int returnedValue = prices.merge("Shirt", 501, (oldValue, newValue) -> oldValue + newValue);
		System.out.println("Price of Shirt: " + returnedValue);

		// print updated HashMap
		System.out.println("Updated HashMap: " + prices);
	}
}
