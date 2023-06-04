package infoTable;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

		//infoTableRows.stream().flatMap(r -> r.getData().stream()).
	}
}