package com.example.einfohukum.fahmi.model.peraturan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsePeraturan{

	@SerializedName("result")
	private List<ResultItemPeraturan> result;

	@SerializedName("message")
	private String message;

	public List<ResultItemPeraturan> getResult(){
		return result;
	}

	public String getMessage(){
		return message;
	}
}