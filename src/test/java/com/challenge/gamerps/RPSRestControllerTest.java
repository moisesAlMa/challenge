package com.challenge.gamerps;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.challenge.gamerps.bean.GameDataBean;
import com.challenge.gamerps.controllers.RPSRestController;
import com.challenge.gamerps.services.GameDataService;

@WebMvcTest(RPSRestController.class)
public class RPSRestControllerTest {
	
	@MockBean
	private GameDataService service;
	
	@Test
	  public void unitTestForServiceLayer()
	      throws Exception {
		
		List<List<Object>> totalResults = createResults();
		GameDataBean gameDataBean = new GameDataBean();
		gameDataBean.setOptionPlayer1("ROCK");
		gameDataBean.setOptionPlayer2("PAPER");
		gameDataBean.setWinner("2nd PLAYER WINS");
		gameDataBean.setRoundNumber(1L);
		GameDataBean[] listOfResults = new GameDataBean[]{gameDataBean};
		
		when(service.getTotalData(listOfResults)).thenReturn(totalResults);
		
		assert
		service.getTotalData(listOfResults).get(0).get(0).equals("TOTAL WINS FOR 1st PLAYER");
	}
	
	private List<List<Object>> createResults() {
		return Arrays.asList(
				Arrays.asList("TOTAL WINS FOR 1st PLAYER", 0),
				Arrays.asList("TOTAL WINS FOR 2nd PLAYER", 1),
				Arrays.asList("TOTAL DRAWS", 0)
        );
	  }
}
