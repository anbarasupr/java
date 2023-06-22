package com.demo.infoTableNew;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoTable implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private String infoTableId;
	@Getter
	@Setter
	private String versionId;
	@Getter
	@Setter
	private String infoModelCode;
	@Getter
	@Setter
	private RowCount rowCount;
	@Getter
	@Setter
	private ModelData model;
	@Getter
	@Setter
	private Data data;
	@Getter
	@Setter
	private List<InfoTableRow> infoTableRows;
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

class Data implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private InfoTableRecords infoTableRecords;
}

class InfoTableRecords implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private List<String> data;
}

class ModelData implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private String code;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private Integer modelCount;
	@Getter
	@Setter
	private List<Model> data;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Model implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private String columnName;
	@Getter
	@Setter
	private String columnCode;
}

class RowCount implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private Integer expected;
	@Getter
	@Setter
	private Integer actual;
}
