package com.challenge.gamerps.bean;

import org.springframework.stereotype.Component;

@Component 
public class GameDataBean {

	private static String EMPTY_GR = "";
	
	private String optionPlayer1;
	private String optionPlayer2;
	private String winner;
	private Long roundNumber;
	
	public String getOptionPlayer1() {
		return optionPlayer1;
	}
	public void setOptionPlayer1(String optionPlayer1) {
		this.optionPlayer1 = optionPlayer1;
	}
	public String getOptionPlayer2() {
		return optionPlayer2;
	}
	public void setOptionPlayer2(String optionPlayer2) {
		this.optionPlayer2 = optionPlayer2;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public Long getRoundNumber() {
		return roundNumber;
	}
	public void setRoundNumber(Long roundNumber) {
		this.roundNumber = roundNumber;
	}
	
	@Override
	public String toString() {
		return "GameDataBean [optionPlayer1=" + optionPlayer1 + ", optionPlayer2=" + optionPlayer2 + ", winner="
				+ winner + ", roundNumber=" + roundNumber + "]";
	}
	
	public boolean isEmpty() {
		return ((optionPlayer1 == null || optionPlayer1.equals(EMPTY_GR)) 
			    || (optionPlayer2 == null || optionPlayer2.equals(EMPTY_GR))
			    || (winner == null || winner.equals(EMPTY_GR))
			    || (roundNumber == null)) ? true : false;
	}
}
