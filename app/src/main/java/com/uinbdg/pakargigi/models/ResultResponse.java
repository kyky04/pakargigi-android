package com.uinbdg.pakargigi.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResultResponse{

	@SerializedName("data")
	private List<DataItemResult> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(List<DataItemResult> data){
		this.data = data;
	}

	public List<DataItemResult> getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"ResultResponse{" + 
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}