package com.quiz.api.exceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

	private Integer status;
	private LocalDateTime timestamp;
	private String type;
	private String title;
	private String detail;
	private String userMensage;
	private List<Field> Fields;
	
	
	@Getter
	@Builder
	public static class Field {
		
		private String name;
		private String userMensage;
	}
;
}
