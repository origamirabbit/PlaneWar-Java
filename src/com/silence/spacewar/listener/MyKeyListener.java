package com.silence.spacewar.listener;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.silence.spacewar.SpaceWar;
import com.silence.spacewar.MyPanel;
import com.silence.spacewar.domain.Bomb;
import com.silence.spacewar.domain.Boss;
import com.silence.spacewar.domain.Enemy;
import com.silence.spacewar.domain.Explosion;
import com.silence.spacewar.domain.MyPlane;

public class MyKeyListener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent event) {
		if (MyPanel.myplane != null && !MyPanel.isPause) {
			switch (event.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				int x = MyPanel.myplane.getPoint().x - MyPanel.DEFAULT_SPEED;
				if (x < 0)
					x = 0;
				MyPanel.myplane.setPoint(new Point(x, MyPanel.myplane
						.getPoint().y));
				break;
			case KeyEvent.VK_RIGHT:
				int x1 = MyPanel.myplane.getPoint().x + MyPanel.DEFAULT_SPEED;
				if (x1 > SpaceWar.WINDOWS_WIDTH - Bomb.BOMB_WIDTH)
					x1 = SpaceWar.WINDOWS_WIDTH - Bomb.BOMB_WIDTH;
				MyPanel.myplane.setPoint(new Point(x1, MyPanel.myplane
						.getPoint().y));
				break;
			case KeyEvent.VK_UP:
				int y = MyPanel.myplane.getPoint().y - MyPanel.DEFAULT_SPEED;
				if (y < 0)
					y = 0;
				MyPanel.myplane.setPoint(new Point(
						MyPanel.myplane.getPoint().x, y));
				break;
			case KeyEvent.VK_DOWN:
				int y1 = MyPanel.myplane.getPoint().y + MyPanel.DEFAULT_SPEED;
				if (y1 > SpaceWar.WINDOWS_HEIGHT)
					y1 = SpaceWar.WINDOWS_HEIGHT;
				MyPanel.myplane.setPoint(new Point(
						MyPanel.myplane.getPoint().x, y1));
				break;
			case KeyEvent.VK_SPACE:
				Bomb bomb1 = new Bomb(MyPanel.myplane.getPoint().x + 10,
						MyPanel.myplane.getPoint().y, 1, MyPanel.isUpdate);
				MyPanel.bombList.add(bomb1);
				Bomb bomb2 = new Bomb(MyPanel.myplane.getPoint().x
						+ MyPlane.PLANE_WIDTH - 40,
						MyPanel.myplane.getPoint().y, 1, MyPanel.isUpdate);
				MyPanel.bombList.add(bomb2);
				break;
			case KeyEvent.VK_C:
				// ??????????
				MyPanel.isProtect = true;
				break;
			case KeyEvent.VK_V:
				// ????????
				MyPanel.isUpdate = true;
				MyPanel.myplane.isUpdate = true;
				break;
			case KeyEvent.VK_Y:
				// ????????
				if (MyPanel.isStop == 0) {
					// ????????????
					if (MyPanel.test == false)
						MyPanel.test = true;
					else
						MyPanel.test = false;
				} else {
					MyPanel.isStop = 0;
					// Restart();
				}
				break;
			case KeyEvent.VK_X:// ????
				if (MyPanel.bossLoaded) {
					// ??????????
					if (MyPanel.magicCount >= 10) {
						MyPanel.magicCount -= 10;
						// ????????
						for (int i = 0; i < MyPanel.enemyList.size(); i++) {
							Enemy enemy = MyPanel.enemyList.get(i);
							// ??????????????????????????
							Explosion explosion = new Explosion(
									(enemy.getPoint().x + Enemy.ENEMY_WIDTH / 2),
									(enemy.getPoint().y + Enemy.ENEMY_HEIGHT / 2));
							MyPanel.explosionList.add(explosion);

							// ????????
							MyPanel.enemyList.remove(i);
							// ????????
							MyPanel.passScore++;
						}// for
						if (MyPanel.isBoss) {
							// ??????????????????????????
							Explosion explosion = new Explosion(
									MyPanel.boss.getPoint().x + Boss.BOSS_WIDTH
											/ 2, MyPanel.boss.getPoint().y
											+ Boss.BOSS_HEIGHT / 2);
							MyPanel.explosionList.add(explosion);
							MyPanel.bossBlood -= 50;
							if (MyPanel.bossBlood <= 0) {
								// boss????????
								// ??????????????
								MyPanel.boss = null;
								// ??????????????
								MyPanel.isPause = true;
								MyPanel.myplane = new MyPlane(false);
								MyPanel.isPass = true;
								MyPanel.isBoss = false;
							}
						}
						// ????????????
						for (int i = 0; i < MyPanel.ballList.size(); i++) {
							MyPanel.ballList.remove(i);
						}
					}
				}
				break;
			default:
				break;
			}
		}
		if (event.getKeyCode() == KeyEvent.VK_Z) {
			if (MyPanel.isPause)
				MyPanel.isPause = false;
			else
				MyPanel.isPause = true;
		}
	}

}
