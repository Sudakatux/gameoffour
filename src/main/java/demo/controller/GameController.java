package demo.controller;

import java.util.List;
import java.util.Optional;

import org.model.entity.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.actualgame.ConnectFourGame;
import demo.response.WinnerDto;


@Controller
public class GameController {
	
	@Autowired
	MongoOperations mo;
	
	@RequestMapping(value="/games/{player}", method = RequestMethod.GET)
	public String showGamePoardForPlayer(@PathVariable Integer player,Model model){
		model.addAttribute("currentPlayer", player);
		if(player < 0 || player >2 )
			model.addAttribute("currentPlayerString", "Ups only two players "+wichPlayer(player));
		else
			model.addAttribute("currentPlayerString", "Welcome player "+wichPlayer(player));
		
		return "board";
	}
	
	@RequestMapping(value="/games/{player}/play/{column}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public WinnerDto pushDisk(@PathVariable Integer player,@PathVariable Integer column){
		
		if(player < 0 || player >2 )
			return new WinnerDto("No");
		
		ConnectFourGame cf = new ConnectFourGame(instatiateBoard());
		
		int successRow = cf.putColor(column, wichPlayer(player));
		if(successRow!=-1)
			mo.save(new BoardEntity(successRow, column,Character.toString(wichPlayer(player))));
		
		switch (cf.getWinner()) {
			case 'T':
				return new WinnerDto("Tie");
			case 'R':
				return new WinnerDto("Red");
			case 'G':
				return new WinnerDto("Green");
			default:
				return new WinnerDto("No");
		}
		
	}
	
	
	@RequestMapping(value="/games/initboard", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<BoardEntity> initializeBoard(){
		
		return mo.findAll(BoardEntity.class); 

	}
	
	private char wichPlayer(int player){
		return player==1?'R':'G';
	}
	
	
	private char[][]  instatiateBoard(){
		char[][] field = new char[7][7];
		
		for (int i = 0; i < 7; ++i)
	            for (int j = 0; j < 7; ++j){
	            	Query position = new Query(Criteria.where("posX")
	        				.is(i)
	        				.and("posY").is(j));
	            	Optional<BoardEntity> br =Optional.ofNullable(mo.findOne(position, BoardEntity.class));
	            	
	            	if(br.isPresent())
	            		field[i][j] = br.get().getTurn().toCharArray()[0];
	            	else
	            		field[i][j]= ' ';
	            	
	            }
		return field;
	}
	
	
	

}
