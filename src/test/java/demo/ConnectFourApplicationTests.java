package demo;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.model.entity.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConnectFourApplication.class)
@WebAppConfiguration
public class ConnectFourApplicationTests {
	@Autowired
	MongoOperations mo;
	//PlaysRepo playRepo;
	
	
	@Test
	public void testEmptyBoard(){
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
		
		for (int i = 0; i < 7; ++i)
            for (int j = 0; j < 7; ++j)
            	Assert.assertTrue(field[i][j]==' ');
	}



}
