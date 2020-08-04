package com.example.einfohukum.fahmi.model.naskah;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseNaskahKerma{

	@SerializedName("result")
	private List<ResultItemNaskahKerma> result;

	@SerializedName("message")
	private String message;

	public List<ResultItemNaskahKerma> getResult(){
		return result;
	}

	public String getMessage(){
		return message;
	}
}