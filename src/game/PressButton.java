package game;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

class PressButton extends JButton {
	
	private boolean pressed = false;
	private State sign;
	private static Icon blankIcon, xIcon, oIcon;
	
	public PressButton() {
		super();
		blankIcon = new ImageIcon("res/blank.gif");
		oIcon = new ImageIcon("res/O.gif");
		xIcon = new ImageIcon("res/X.gif");
		
		this.setIcon(blankIcon);
		this.sign = State.BLANK;
	}
	
	public void setButton(State turn) {
		if (turn == State.BLANK) {
			this.setIcon(blankIcon);
			this.pressed = false;
			this.sign = State.BLANK;
		}
		if (!this.pressed) {
			if (turn == State.XX) {
				this.setIcon(xIcon);
				this.pressed = true;
				this.sign = State.XX;
			}
			else if (turn == State.OO) {
				this.setIcon(oIcon);
				this.pressed = true;
				this.sign = State.OO;
			}
		}
	}
	
	public State getSign() {
		return sign;
	}
	
	public boolean wasPressed() {
		return this.pressed;
	}
}