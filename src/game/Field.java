package game;

import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

enum State {
	BLANK, XX, OO
}

public class Field extends JFrame {
	
	private PressButton[][] button;	
	private State turn;
		
	public Field() throws Exception{
		turn = State.XX;
		button = new PressButton[3][3];
	}
	
	public void showField() throws Exception{
				
		setLayout(new GridLayout(3,3));
		
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				button[i][j] = new PressButton();
				add(button[i][j]);
			}
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				button[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean wasPressed = ((PressButton)e.getSource()).wasPressed();
						((PressButton)e.getSource()).setButton(turn);
						if (checkWinner()) {
							if(turn == State.XX)
								JOptionPane.showMessageDialog(null, "Player X wins!");
							else JOptionPane.showMessageDialog(null, "Player O wins!");
							end();
						}
						else if (checkEnd()) {
							JOptionPane.showMessageDialog(null, "Draw!");	
							end();
						}
						if (!wasPressed)
							switchTurn(turn);
						
					}
				});
				
			}
		
		setTitle("TicTacToe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(200, 200);
	    setVisible(true);		
		
	}
	
	public boolean checkWinner() {
		State s = button[1][1].getSign();
		if ((s == button[0][0].getSign()) && (s == button[2][2].getSign()) && (s != State.BLANK)) return true; //1 диагональ
		if ((s == button[0][2].getSign()) && (s == button[2][0].getSign()) && (s != State.BLANK)) return true; //2 диагональ
		for(int i = 0; i < 3; i++)
			if ((button[i][0].getSign() == button[i][1].getSign()) && (button[i][0].getSign() == button[i][2].getSign()) && (button[i][0].getSign() != State.BLANK)) return true; //строки
		for(int j = 0; j < 3; j++)
			if ((button[0][j].getSign() == button[1][j].getSign()) && (button[0][j].getSign() == button[2][j].getSign()) && (button[0][j].getSign() != State.BLANK)) return true; //столбцы
		return false;
	}
	
	public boolean checkEnd() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) 
				if (button[i][j].getSign() == State.BLANK)
					return false;
		return true;
	}
	
	public void end() {
		int reply = JOptionPane.showConfirmDialog(null, "Play again?", "TicTacToe", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					button[i][j].setButton(State.BLANK);
	    } else this.dispose();
	}
	
	public void switchTurn(State state) {
		if (state == State.XX) 	
			turn = State.OO; 	
		else if (state == State.OO) 	
			turn = State.XX; 	

	}
}

