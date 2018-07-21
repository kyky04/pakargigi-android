package com.uinbdg.pakargigi.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GejalaResponse{

	@SerializedName("data")
	private List<DataItemGejala> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(List<DataItemGejala> data){
		this.data = data;
	}

	public List<DataItemGejala> getData(){
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
			"GejalaResponse{" + 
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}