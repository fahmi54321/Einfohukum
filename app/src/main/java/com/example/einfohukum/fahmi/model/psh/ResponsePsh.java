package com.example.einfohukum.fahmi.model.psh;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ResponsePsh{

	@SerializedName("result")
	private List<ResultItemPsh> result;

	@SerializedName("message")
	private String message;

	public List<ResultItemPsh> getResult(){
		return result;
	}

	public String getMessage(){
		return message;
	}
}