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
public class Input implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private List<Request> request;
}

@NoArgsConstructor
class Request implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private String siteType;
	@Getter
	@Setter
	private List<CountryService> countryList;
}

class CountryService implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private String country;
	@Getter
	@Setter
	private Integer count;
	@Getter
	@Setter
	private List<InfoTablePOJO> serviceList;
}