package com.demo.infoTableOld;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CustomPair implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private String I;
	@Getter
	@Setter
	private String J;
	@Getter
	@Setter
	private String K;

}