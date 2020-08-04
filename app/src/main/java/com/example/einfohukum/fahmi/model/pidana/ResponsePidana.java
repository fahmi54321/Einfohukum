package com.example.einfohukum.fahmi.model.pidana;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePidana {

	@SerializedName("result")
	private List<ResultItemPidana> result;

	@SerializedName("message")
	private String message;

	public List<ResultItemPidana> getResult(){
		return result;
	}

	public String getMessage(){
		return message;
	}
}