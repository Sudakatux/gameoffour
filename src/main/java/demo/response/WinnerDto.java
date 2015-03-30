package demo.response;

import java.io.Serializable;

public class WinnerDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7063821003418638593L;
	
	String message;
	
	public WinnerDto(String msg){
		
		this.message=msg;
	}

	public String getMessage() {
	
		return message;
	
	}

	
	public void setMessage(String message) {
	
		this.message = message;
	
	}
	
	
	

}
