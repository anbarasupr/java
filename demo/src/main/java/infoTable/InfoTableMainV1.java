package infoTable;

import java.io.File;
import java.io.IOException;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import infoTable.InfoTableRowV1.CountryPortGroup;
import infoTable.InfoTableRowV1.CountryPortSpeedGroup;
import infoTable.InfoTableRowV1.MrcNrcAggregation;

public class InfoTableMainV1 {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		String url = "F:\\git\\java\\java\\demo\\src\\main\\resources\\json\\infoTableV1.json";
		ObjectMapper mapper = new ObjectMapper();
		InfoTable infoTable = mapper.readValue(new File(url), InfoTable.class);
		System.out.println("infoTable: " + infoTable.getVersionId());
		List<InfoTableRowV1> infoTableRows = infoTable.getInfoTableRowsV1();
		System.out.println("infoTableRows: " + infoTableRows.size());

		// countryGrouping(infoTableRows, mapper);
		// countryPortGroupingV2(infoTableRows, mapper);
		// countryPortGroupingV3(infoTableRows, mapper);
		// countryPortSumGrouping(infoTableRows, mapper);
		// countryPortSumrGrouping(infoTableRows, mapper);

		// mrcNrcAggregation(infoTableRows, mapper);
		countryPortSpeedPriceGrouping(infoTableRows, mapper);
		// countryPortSumrGrouping(infoTableRows, mapper);

		// countryPortSumrGrouping(infoTableRows, mapper);
		// countryPortSumrGrouping(infoTableRows, mapper);

		// countryPortSumrGrouping(infoTableRows, mapper);

		// countryPortSumrGrouping(infoTableRows, mapper);
		// countryPortSumrGrouping(infoTableRows, mapper);

		//
	}

	public static void countryGrouping(List<InfoTableRowV1> infoTableRows, ObjectMapper mapper)
			throws JsonProcessingException {
		Map<String, List<InfoTableRowV1>> countryGrouping = infoTableRows.stream()
				.collect(Collectors.groupingBy(InfoTableRowV1::getCountry));
		System.out.println(
				"countryGrouping: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(countryGrouping));
	}

	// Group By Multiple Fields
	public static void countryPortGroupingV1(List<InfoTableRowV1> infoTableRows, ObjectMapper mapper)
			throws JsonProcessingException {
		Map<String, Map<String, Long>> countryPortGrouping = infoTableRows.stream().collect(Collectors.groupingBy(
				InfoTableRowV1::getCountry, Collectors.groupingBy(InfoTableRowV1::getPortType, Collectors.counting())));
		System.out.println("countryPortGroupingV1: "
				+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(countryPortGrouping));
	}

	// Group By Multiple Fields and Collect Aggregated Result into List
	public static void countryPortGroupingV2(List<InfoTableRowV1> infoTableRows, ObjectMapper mapper)
			throws JsonProcessingException {
		Map<String, Map<String, List<InfoTableRowV1>>> countryPortGrouping = infoTableRows.stream().collect(
				Collectors.groupingBy(InfoTableRowV1::getCountry, Collectors.groupingBy(InfoTableRowV1::getPortType)));
		System.out.println("countryPortGroupingV2: "
				+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(countryPortGrouping));
	}

	// Group By Multiple Fields – Avoid Collectors.groupingBy Chaining
	public static void countryPortGroupingV3(List<InfoTableRowV1> infoTableRows, ObjectMapper mapper)
			throws JsonProcessingException {

		Map<CountryPortGroup, Long> countryPortGrouping = infoTableRows.stream().collect(Collectors.groupingBy(
				r -> new InfoTableRowV1.CountryPortGroup(r.getCountry(), r.getPortType()), Collectors.counting()));

		System.out.println("countryPortGroupingV3: "
				+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(countryPortGrouping));
	}

	// Summing

	// group by sum
	public static void countryPortSumGrouping(List<InfoTableRowV1> infoTableRows, ObjectMapper mapper)
			throws JsonProcessingException {

		Map<CountryPortGroup, Double> countryPortSumGrouping = infoTableRows.stream().collect(
				Collectors.groupingBy(r -> new InfoTableRowV1.CountryPortGroup(r.getCountry(), r.getPortType()),
						Collectors.summingDouble(r -> r.getPrice())));

		Map<CountryPortGroup, List<InfoTableRowV1>> countryPortSumGrouping1 = infoTableRows.stream().collect(
				Collectors.groupingBy(r -> new InfoTableRowV1.CountryPortGroup(r.getCountry(), r.getPortType()),
						Collectors.toList()));

		// countryPortSumGrouping2 and countryPortSumGrouping1 are same
		Map<CountryPortGroup, List<InfoTableRowV1>> countryPortSumGrouping2 = infoTableRows.stream().collect(
				Collectors.groupingBy(r -> new InfoTableRowV1.CountryPortGroup(r.getCountry(), r.getPortType())));

		System.out.println("countryPortSumGrouping: "
				+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(countryPortSumGrouping));

		System.out.println("countryPortSumGrouping1: "
				+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(countryPortSumGrouping1));
	}

	// group by summarizing
	public static void countryPortSumrGrouping(List<InfoTableRowV1> infoTableRows, ObjectMapper mapper)
			throws JsonProcessingException {

		Map<CountryPortGroup, DoubleSummaryStatistics> countryPortSumrGrouping = infoTableRows.stream().collect(
				Collectors.groupingBy(r -> new InfoTableRowV1.CountryPortGroup(r.getCountry(), r.getPortType()),
						Collectors.summarizingDouble(r -> r.getPrice())));

		System.out.println("countryPortSumrGrouping: "
				+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(countryPortSumrGrouping));
	}

	// aggregation simultaneously for the three fields
	public static void mrcNrcAggregation(List<InfoTableRowV1> infoTableRows, ObjectMapper mapper)
			throws JsonProcessingException {

		Map<CountryPortGroup, MrcNrcAggregation> mrcNrcAggregation = infoTableRows.stream().collect(
				Collectors.groupingBy(r -> new InfoTableRowV1.CountryPortGroup(r.getCountry(), r.getPortType()),
						Collectors.collectingAndThen(Collectors.toList(), list -> {
							double mrc = list.stream().collect(Collectors.summingDouble(InfoTableRowV1::getMrc));
							double nrc = list.stream().collect(Collectors.summingDouble(InfoTableRowV1::getNrc));
							double price = list.stream().collect(Collectors.summingDouble(InfoTableRowV1::getPrice));
							return new MrcNrcAggregation(mrc, nrc, price);
						})));

		System.out.println(
				"mrcNrcAggregation: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mrcNrcAggregation));
	}

	// group by all
	public static void countryPortSpeedPriceGrouping(List<InfoTableRowV1> infoTableRows, ObjectMapper mapper)
			throws JsonProcessingException {

		Map<CountryPortSpeedGroup, Double> countryPortSpeedPriceGrouping = infoTableRows.stream()
				.collect(Collectors.groupingBy(r -> new InfoTableRowV1.CountryPortSpeedGroup(r.getCountry(),
						r.getPortType(), r.getPortSpeed()), Collectors.summingDouble(r -> r.getPrice())));

		Map<CountryPortSpeedGroup, Double> countryPortSpeedPriceGrouping1 = infoTableRows.stream()
				.collect(Collectors.groupingBy(r -> new InfoTableRowV1.CountryPortSpeedGroup(r.getCountry(),
						r.getPortType(), r.getPortSpeed()), Collectors.summingDouble(r -> r.getPrice())));

		System.out.println("countryPortSpeedPriceGrouping: "
				+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(countryPortSpeedPriceGrouping));

	}
}
