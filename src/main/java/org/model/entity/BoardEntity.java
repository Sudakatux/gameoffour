package org.model.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="plays")
public class BoardEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1166241008138447694L;

	@Id
	private String id;
	
	private int posX; //fila
	
	private int posY;// columna
	
	private String turn;
	
	public BoardEntity(int posx,int posy,String turn){
		this.posX=posx;
		this.posY=posy;
		this.turn=turn;
	}
	
	public BoardEntity(){
		
	}
	
	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public String getTurn() {
		return turn;
	}
	
	public void setTurn(String turn) {
		this.turn = turn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
	
	

}
