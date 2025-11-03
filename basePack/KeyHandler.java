package basePack;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyHandler implements KeyListener{

ArrayList<String> k = new ArrayList<String>();

	public boolean checkKey(String key) {
		for(String i: k) {
			try {
				if(i!=null)
					if(i.equals(key))
						return true;
				}catch(Exception e) {
					System.out.println(e);
				}
			}
		return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int kc = e.getKeyCode();
		String key = KeyEvent.getKeyText(kc).toLowerCase();
		if(!checkKey(key))
			k.add(key);
		}

	@Override
	public void keyReleased(KeyEvent e) {
		int kc = e.getKeyCode();
		String key = KeyEvent.getKeyText(kc).toLowerCase();
		if(checkKey(key)) {
			for(int i = 0; i < k.size(); i++){
				if(k.get(i).equals(key)){
					k.remove(i);
					break;
				}
			}
		}
	}
}
