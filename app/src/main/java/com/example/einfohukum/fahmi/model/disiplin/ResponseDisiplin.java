package com.example.einfohukum.fahmi.model.disiplin;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDisiplin{

	@SerializedName("result")
	private List<ResultItemDisiplin> result;

	@SerializedName("message")
	private String message;

	public List<ResultItemDisiplin> getResult(){
		return result;
	}

	public String getMessage(){
		return message;
	}
}