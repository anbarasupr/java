package infoTab;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoTablePOJO {
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
	private double price;
	@Getter
	@Setter
	private String type;

}
