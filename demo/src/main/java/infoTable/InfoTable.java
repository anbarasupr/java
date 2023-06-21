package infoTable;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoTable implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private String versionId;
	@Getter
	@Setter
	private List<InfoTableRow> infoTableRows;
	@Getter
	@Setter
	private List<InfoTableRowV1> infoTableRowsV1;
	@Getter
	@Setter
	private String data;

}

class InfoTableRow implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private List<InfoTableRowData> data;
}

class InfoTableRowData implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private String columnName;
	@Getter
	@Setter
	private String columnValue;
}

@ToString
class InfoTableRowV1 implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private String country;
	@Getter
	@Setter
	private String portType;
	@Getter
	@Setter
	private String portSpeed;
	@Getter
	@Setter
	private Double price;
	@Getter
	@Setter
	private Double mrc;
	@Getter
	@Setter
	private Double nrc;

	record CountryPortGroup(String country, String portType) {
	}

	record MrcNrcAggregation(Double mrc, Double nrc, Double price) {
	}

	record CountryPortSpeedGroup(String country, String portType, String speed) {
	}

}