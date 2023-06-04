package infoTable;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	private String price;
}