package com.challenge.gamerps.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.gamerps.bean.GameDataBean;

@Service
public class GameDataService {
	
	private static final String FIRST_WINS = "1st PLAYER WINS";
	
	private static final String SECONDS_WINS = "2nd PLAYER WINS";
	
	@Autowired
	GameDataBean gameDataBean;

	public GameDataBean getGameDataBean() {
		return gameDataBean;
	}

	public void setGameDataBean(GameDataBean gameDataBean) {
		this.gameDataBean = gameDataBean;
	}
	
	public List<List<Object>> getTotalData(GameDataBean[] results) {
		Integer firstVitories = 0;
		Integer secondVitories = 0;
		Integer draws = 0;
		for (GameDataBean result : results) {
			if (result.getWinner().equals(FIRST_WINS)) firstVitories++;
			else if(result.getWinner().equals(SECONDS_WINS)) secondVitories++;
			else draws++;
		}
		return Arrays.asList(
				Arrays.asList("TOTAL WINS FOR 1st PLAYER", firstVitories),
				Arrays.asList("TOTAL WINS FOR 2nd PLAYER", secondVitories),
				Arrays.asList("TOTAL DRAWS", draws)
        );
	}
	
}
