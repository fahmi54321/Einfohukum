package com.example.einfohukum.fahmi.model.kke;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseKke{

	@SerializedName("result")
	private List<ResultItemKke> result;

	@SerializedName("message")
	private String message;

	public List<ResultItemKke> getResult(){
		return result;
	}

	public String getMessage(){
		return message;
	}
}