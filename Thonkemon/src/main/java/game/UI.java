package main.java.game;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.java.game.fight.Fight;
import main.java.game.fight.PlayerPvE;
import main.java.game.fight.PlayerPvP;
import main.java.game.fight.RandomEncounter;
import main.java.items.Item;
import main.java.items.balls.Ball;
import main.java.items.potions.Potion;
import main.java.monsters.Monster;
import main.java.moves.Move;

public class UI {

	private JFrame frame;
	private Selection select;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new UI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1250, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		frame.getContentPane().repaint();

		setKeyStrokes();

		select = new Selection(new Player("P"));

		actionSelection();

		// storeBallSelection(new Store());
	}

	private void setKeyStrokes() {

		Set<KeyStroke> setFW = new HashSet<KeyStroke>();
		setFW.add(KeyStroke.getKeyStroke("RIGHT"));
		frame.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, setFW);

		Set<KeyStroke> setBW = new HashSet<KeyStroke>();
		setBW.add(KeyStroke.getKeyStroke("LEFT"));
		frame.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, setBW);

	}

	public void fightSelection(Fight fight) {
		frame.getContentPane().removeAll();

		JButton btnNewButton_1 = new JButton("Attack");
		btnNewButton_1.setBounds(22, 406, 570, 125);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				attackSelection(fight);
			}
		});
		System.out.println(btnNewButton_1.isFocusable());
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Item");
		btnNewButton_2.setBounds(22, 549, 570, 125);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemSelection(fight);
			}
		});
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Switch");
		btnNewButton_3.setBounds(614, 406, 570, 125);
		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				swapMonsters(fight, select.p1, select.p1.getActiveMonster());
			}
		});
		frame.getContentPane().add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Flee");
		btnNewButton_4.setBounds(614, 549, 570, 125);
		btnNewButton_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actionSelection();
			}
		});
		frame.getContentPane().add(btnNewButton_4);

		frame.getContentPane().repaint();

		btnNewButton_1.grabFocus();

		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					frame.transferFocus();
				}
				System.out.println(e.getKeyCode());
			}
		});

	}

	public void attackSelection(Fight fight) {
		frame.getContentPane().removeAll();

		JLabel console = new JLabel();
		console.setBounds(12, 13, 1183, 370);
		frame.getContentPane().add(console);

		JButton btn1 = new JButton();
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn1.setBounds(12, 396, 580, 100);
		frame.getContentPane().add(btn1);

		JButton btn2 = new JButton();
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn2.setBounds(615, 396, 580, 100);
		frame.getContentPane().add(btn2);

		JButton btn3 = new JButton();
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn3.setBounds(12, 509, 580, 96);
		frame.getContentPane().add(btn3);

		JButton btn4 = new JButton();
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn4.setBounds(615, 509, 580, 96);
		frame.getContentPane().add(btn4);

		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fightSelection(fight);
			}
		});
		backBtn.setBounds(12, 618, 1183, 56);
		frame.getContentPane().add(backBtn);

		List<JButton> buttons = new ArrayList<JButton>();
		buttons.add(btn1);
		buttons.add(btn2);
		buttons.add(btn3);
		buttons.add(btn4);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(49, 75, 301, 30);
		console.add(progressBar);

		JLabel lblName = new JLabel(select.p1.team.get(0).name);
		lblName.setBounds(49, 21, 301, 30);
		console.add(lblName);

		progressBar.setMaximum(select.p1.team.get(0).stats.maxHP);
		progressBar.setValue(select.p1.team.get(0).stats.hp);

		for (int i = 0; i < select.p1.getActiveMonster().fightMoves.size(); i++) {
			buttons.get(i).setText(select.p1.getActiveMonster().fightMoves.get(i).name);
		}

		frame.getContentPane().repaint();

	}

	public void actionSelection() {
		frame.getContentPane().removeAll();

		JButton btnNewButton_1 = new JButton("Fight");
		btnNewButton_1.setBounds(41, 406, 571, 125);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fightChoice();
			}
		});
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Team");
		btnNewButton_2.setBounds(41, 549, 571, 125);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				teamOrBox(select.p1);
			}
		});
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Store");
		btnNewButton_3.setBounds(626, 406, 571, 125);
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				storeSelection();
			}
		});
		frame.getContentPane().add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Save");
		btnNewButton_4.setBounds(626, 549, 571, 125);
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				select.save();
			}
		});
		frame.getContentPane().add(btnNewButton_4);

		frame.getContentPane().repaint();

	}

	public void fightChoice() {
		frame.getContentPane().removeAll();

		JButton button = new JButton("Player Fight");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerSelection();
			}
		});
		button.setBounds(12, 498, 591, 130);
		frame.getContentPane().add(button);

		JButton btnNewButton = new JButton("Random Encounter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fightSelection(new RandomEncounter(select.p1));
			}
		});
		btnNewButton.setBounds(629, 498, 591, 130);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_7 = new JButton("Back");
		btnNewButton_7.setBounds(12, 641, 1208, 49);
		btnNewButton_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actionSelection();
			}
		});
		frame.getContentPane().add(btnNewButton_7);

		frame.getContentPane().repaint();

	}

	public void playerSelection() {
		frame.getContentPane().removeAll();

		JButton button = new JButton("1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fightSelection(new PlayerPvE(select.p1, new Player("Player 2")));
			}
		});
		button.setBounds(12, 498, 580, 130);
		frame.getContentPane().add(button);

		JButton btnNewButton = new JButton("2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fightSelection(new PlayerPvP(select.p1, select.p2));
			}
		});
		btnNewButton.setBounds(604, 498, 591, 130);
		frame.getContentPane().add(btnNewButton);

		frame.getContentPane().repaint();
	}

	public void saveGameSelection() {
		frame.getContentPane().removeAll();

		JButton button = new JButton("Create new game");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select = new Selection(new Player("Player 1"), new Player("Player 2"));
				actionSelection();
			}
		});
		button.setBounds(12, 498, 580, 130);
		frame.getContentPane().add(button);

		JButton btnNewButton = new JButton("Load existing game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select = new Selection();
				actionSelection();
			}
		});
		btnNewButton.setBounds(604, 498, 591, 130);
		frame.getContentPane().add(btnNewButton);

		frame.getContentPane().repaint();

	}

	public void itemSelection(Fight fight) {
		frame.getContentPane().removeAll();

		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(12, 276, 1208, 351);
		frame.getContentPane().add(scroll);

		JPanel panel = new JPanel();
		scroll.setViewportView(panel);
		panel.setLayout(new WrapLayout(WrapLayout.LEFT, 5, 5));

		StringBuilder s = new StringBuilder();
		for (Monster m : select.p1.team) {
			s.append(m.name + " Lv." + m.exp.level + " " + m.stats.hp + "/" + m.stats.maxHP + "HP" + "<br>");
		}

		String monsterString = "<html>" + s.toString() + "</htlm";

		JLabel lbl = new JLabel(monsterString, SwingConstants.CENTER);
		lbl.setBounds(12, 13, 1208, 250);
		frame.getContentPane().add(lbl);
		lbl.setFont(new Font(lbl.getFont().getName(), Font.PLAIN, 20));
		lbl.setPreferredSize(new Dimension(1100, 250));

		for (Item item : select.p1.items) {
			if (item.amount > 0) {
				JButton b = new JButton(item.name + " x" + item.amount);
				b.setPreferredSize(new Dimension(580, 100));
				b.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						teamSelectionForItem(fight, select.p1, item);
					}
				});
				panel.add(b);
			}
		}
		scroll.setBorder(null);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(12, 640, 1208, 50);
		frame.getContentPane().add(btnBack);
		btnBack.setPreferredSize(new Dimension(1220, 50));
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fightSelection(fight);
			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		frame.setVisible(true);

		frame.getContentPane().repaint();

	}

	public void storeSelection() {
		Store store = new Store();

		frame.getContentPane().removeAll();

		JButton button = new JButton("Potions");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storePotionSelection(store);
			}
		});
		button.setBounds(12, 498, 580, 130);
		frame.getContentPane().add(button);

		JButton btnNewButton = new JButton("Balls");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storeBallSelection(store);
			}
		});
		btnNewButton.setBounds(604, 498, 591, 130);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_5 = new JButton("Back");
		btnNewButton_5.setBounds(12, 641, 1183, 49);
		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				actionSelection();
			}
		});
		frame.getContentPane().add(btnNewButton_5);

		frame.getContentPane().repaint();
	}

	public void storePotionSelection(Store store) {
		frame.getContentPane().removeAll();

		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(12, 276, 1208, 351);
		frame.getContentPane().add(scroll);

		JPanel panel = new JPanel();
		scroll.setViewportView(panel);
		panel.setLayout(new WrapLayout(WrapLayout.LEFT, 5, 5));

		JLabel lbl = new JLabel("", SwingConstants.CENTER);
		lbl.setBounds(12, 13, 1208, 250);
		frame.getContentPane().add(lbl);
		lbl.setFont(new Font(lbl.getFont().getName(), Font.PLAIN, 20));
		lbl.setPreferredSize(new Dimension(1100, 250));

		for (Potion potion : store.potions) {
			JButton b = new JButton(potion.name + " " + potion.price + "$");
			b.setPreferredSize(new Dimension(595, 110));
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int amount;

					while (true) {
						String input = JOptionPane.showInputDialog(null, "How many do you want to purchase?",
								potion.name, JOptionPane.PLAIN_MESSAGE);
						if (input.matches("[0-9]+")) {
							amount = Integer.parseInt(input);
							break;
						}
					}
					if (amount > select.p1.money)
						lbl.setText("You don't have enough money for your purchase");
					// JOptionPane.showMessageDialog(null, "You don't have
					// enough money for your purchase", "",
					// JOptionPane.PLAIN_MESSAGE);
					else {
						select.p1.money -= (potion.price * amount);
						for (Item item : select.p1.items) {
							if (item.name.equals(potion.name.replace(" ", ""))) {
								item.amount += amount;
							} else {
								potion.amount = amount;
								select.p1.items.add(potion);
							}
						}
					}
				}
			});
			panel.add(b);
		}

		scroll.setBorder(null);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(15, 631, 1197, 60);
		frame.getContentPane().add(btnBack);
		btnBack.setPreferredSize(new Dimension(1220, 50));
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				storeSelection();
			}

		});

		frame.setVisible(true);

		frame.getContentPane().repaint();
	}

	public void storeBallSelection(Store store) {
		frame.getContentPane().removeAll();

		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(12, 276, 1232, 351);
		frame.getContentPane().add(scroll);

		JPanel panel = new JPanel();
		scroll.setViewportView(panel);
		panel.setLayout(new WrapLayout(WrapLayout.LEFT, 5, 5));

		JLabel lbl = new JLabel("", SwingConstants.CENTER);
		lbl.setBounds(12, 13, 1208, 250);
		frame.getContentPane().add(lbl);
		lbl.setFont(new Font(lbl.getFont().getName(), Font.PLAIN, 20));
		lbl.setPreferredSize(new Dimension(1100, 250));

		for (Ball ball : store.balls) {
			JButton b = new JButton(ball.name + " " + ball.price + "$");
			b.setPreferredSize(new Dimension(595, 110));
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int amount;

					while (true) {
						String input = JOptionPane.showInputDialog(frame, "How many do you want to purchase?", "",
								JOptionPane.PLAIN_MESSAGE);
						if (input.matches("[0-9]+")) {
							amount = Integer.parseInt(input);
							break;
						}
					}
					if (amount * ball.price > select.p1.money)
						lbl.setText("You don't have enough money for your purchase");
					// JOptionPane.showMessageDialog(frame, "You don't have
					// enough money for your purchase");
					else {
						select.p1.money -= (ball.price * amount);
						for (Ball ball : select.p1.balls) {
							if (ball.name.equals(ball.name.replace(" ", ""))) {
								System.out.println("AYE");
								ball.amount += amount;
							} else {
								System.out.println("REE");
								ball.amount = amount;
								select.p1.items.add(ball);
							}
						}
					}
				}
			});
			panel.add(b);
		}
		scroll.setBorder(null);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(15, 631, 1197, 60);
		frame.getContentPane().add(btnBack);
		btnBack.setPreferredSize(new Dimension(1220, 50));
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				storeSelection();
			}
		});

		frame.setVisible(true);

		frame.getContentPane().repaint();
	}

	public void teamSelectionForItem(Fight fight, Player p, Item item) {

		frame.getContentPane().removeAll();

		StringBuilder s = new StringBuilder();
		for (Monster m : p.team) {
			s.append(m.name + " Lv." + m.exp.level + " " + m.stats.hp + "/" + m.stats.maxHP + "HP" + "<br>");
		}

		String monsterString = "<html>" + s.toString() + "</htlm";

		JLabel lbl = new JLabel(monsterString, SwingConstants.CENTER);
		lbl.setFont(new Font(lbl.getFont().getName(), Font.PLAIN, 20));
		lbl.setBounds(12, 13, 1208, 203);
		frame.getContentPane().add(lbl);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 229, 1208, 410);
		frame.getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new WrapLayout());

		JButton btnNewButton_6 = new JButton("Back");
		btnNewButton_6.setBounds(16, 640, 1201, 60);
		btnNewButton_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				itemSelection(fight);
			}
		});
		frame.getContentPane().add(btnNewButton_6);

		for (int i = 0; i < 6; i++) {
			JButton b = new JButton();

			try {
				Monster m = p.team.get(i);
				b = new JButton("<html>" + m.name + "<br>" + " Lv." + m.exp.level + "<br>" + m.stats.hp + "/"
						+ m.stats.maxHP + " HP" + "</html>");
				b.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						item.effect(m);
						fightSelection(fight);
					}
				});
			} catch (Exception e) {
			}

			b.setPreferredSize(new Dimension(598, 130));
			panel.add(b);
		}

		scrollPane.setBorder(null);

		frame.getContentPane().repaint();

	}

	public void teamSelection(Player p) {
		frame.getContentPane().removeAll();

		StringBuilder s = new StringBuilder();
		for (Monster m : p.team) {
			s.append(m.name + " Lv." + m.exp.level + " " + m.stats.hp + "/" + m.stats.maxHP + "HP" + "<br>");
		}

		String monsterString = "<html>" + s.toString() + "</htlm";

		JLabel lbl = new JLabel(monsterString, SwingConstants.CENTER);
		lbl.setFont(new Font(lbl.getFont().getName(), Font.PLAIN, 20));
		lbl.setBounds(12, 13, 1208, 203);
		frame.getContentPane().add(lbl);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 229, 1208, 410);
		frame.getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new WrapLayout());

		JButton btnNewButton_6 = new JButton("Back");
		btnNewButton_6.setBounds(16, 640, 1201, 60);
		btnNewButton_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				teamOrBox(p);
			}
		});
		frame.getContentPane().add(btnNewButton_6);

		for (int i = 0; i < 6; i++) {
			JButton b = new JButton();

			try {
				Monster m = p.team.get(i);
				b = new JButton("<html>" + m.name + "<br>" + " Lv." + m.exp.level + "<br>" + m.stats.hp + "/"
						+ m.stats.maxHP + " HP" + "</html>");
				b.addActionListener(new ActionListener() {

					// JOptionPane mit Auswahl f�r verschiedene Aktionen
					// Jeweils eigener Button
					// Swap, Items und Stats ( neue Methode )

					@Override
					public void actionPerformed(ActionEvent e) {
						showStats(p, m, 0);
					}
				});
			} catch (Exception e) {
			}

			b.setPreferredSize(new Dimension(598, 130));
			panel.add(b);
		}

		scrollPane.setBorder(null);

		frame.getContentPane().repaint();
	}

	public void showStats(Player p, Monster m, int r) {
		frame.getContentPane().removeAll();

		JPanel panel = new JPanel();
		panel.setBounds(12, 26, 1208, 677);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblPicture = new JLabel("Picture");
		lblPicture.setBounds(0, 0, 300, 300);
		panel.add(lblPicture);

		JLabel lblStats = new JLabel(m.name);
		lblStats.setBounds(312, 0, 896, 300);
		panel.add(lblStats);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 320, 1208, 344);
		panel.add(panel_1);
		panel_1.setLayout(new WrapLayout());

		for (int i = 0; i < 4; i++) {
			JButton btn = new JButton();
			int slot = i;
			btn.setPreferredSize(new Dimension(596, 135));

			try {
				Move move = m.fightMoves.get(i);
				btn.setText(move.name);

				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						swapMoves(p, m, slot, r);
					}
				});

			} catch (Exception e) {
			}

			panel_1.add(btn);
		}

		JButton backBtn = new JButton("Back");
		backBtn.setPreferredSize(new Dimension(1197, 60));
		backBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (r == 0)
					teamSelection(p);
				else if (r == 1)
					boxSelection(p);
			}

		});
		panel_1.add(backBtn);

		frame.setVisible(false);
		frame.setVisible(true);

		frame.getContentPane().repaint();

	}

	public void swapMoves(Player p, Monster m, int slot, int r) {

		frame.getContentPane().removeAll();

		JLabel lblMove = new JLabel("Move");
		lblMove.setBounds(12, 13, 1220, 200);
		frame.getContentPane().add(lblMove);

		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(0, 226, 1244, 420);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(scroll);

		JPanel panel = new JPanel();
		panel.setBounds(12, 226, 1220, 420);
		panel.setLayout(new WrapLayout());
		scroll.setViewportView(panel);
		scroll.setBorder(null);

		int moveCount = m.moves.size();

		for (Move move : m.moves) {
			if (!m.fightMoves.contains(move)) {

				String s = "<html>" + move.name + "<br>" + "Damage: " + move.damage + "<br>" + "Type: "
						+ move.damageType.name + "<br>" + "</html>";

				JButton btn = new JButton(s);
				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						m.fightMoves.set(slot, move);
						showStats(p, m, r);
					}
				});
				btn.setPreferredSize(new Dimension(590, 133));
				btn.setText(s);
				panel.add(btn);
			} else
				moveCount--;
		}

		if (moveCount == 0) {
			showStats(p, m, r);
			return;
		}

		if (moveCount % 2 == 1) {
			for (int i = 0; i < 15; i++) {
				JButton btn = new JButton();
				btn.setPreferredSize(new Dimension(590, 133));
				panel.add(btn);
			}
		}

		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				showStats(p, m, r);
			}

		});
		backBtn.setBounds(18, 651, 1187, 51);
		frame.getContentPane().add(backBtn);

		frame.getContentPane().repaint();

	}

	public void swapMonsters(Fight fight, Player p, Monster mon) {
		frame.getContentPane().removeAll();

		StringBuilder s = new StringBuilder();
		for (Monster m : p.team) {
			s.append(m.name + " Lv." + m.exp.level + " " + m.stats.hp + "/" + m.stats.maxHP + "HP" + "<br>");
		}

		String monsterString = "<html>" + s.toString() + "</htlm";

		JLabel lbl = new JLabel(monsterString, SwingConstants.CENTER);
		lbl.setFont(new Font(lbl.getFont().getName(), Font.PLAIN, 20));
		lbl.setBounds(12, 13, 1208, 203);
		frame.getContentPane().add(lbl);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 229, 1208, 410);
		frame.getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new WrapLayout());

		JButton btnNewButton_6 = new JButton("Back");
		btnNewButton_6.setBounds(16, 640, 1201, 60);
		btnNewButton_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fightSelection(fight);
			}
		});
		frame.getContentPane().add(btnNewButton_6);

		for (int i = 0; i < 6; i++) {
			JButton b = new JButton();

			try {
				Monster m = p.team.get(i);
				b = new JButton("<html>" + m.name + "<br>" + " Lv." + m.exp.level + "<br>" + m.stats.hp + "/"
						+ m.stats.maxHP + " HP" + "</html>");
				b.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						p.setActiveMonster(m);
						fightSelection(fight);
					}
				});
			} catch (Exception e) {
			}

			b.setPreferredSize(new Dimension(598, 130));
			panel.add(b);
		}

		scrollPane.setBorder(null);

		frame.getContentPane().repaint();
	}

	public void teamOrBox(Player p) {

		frame.getContentPane().removeAll();

		JButton button = new JButton("Team");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamSelection(p);
			}
		});
		button.setBounds(12, 498, 604, 130);
		frame.getContentPane().add(button);

		JButton btnNewButton = new JButton("Box");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boxSelection(p);
			}
		});
		btnNewButton.setBounds(628, 498, 604, 130);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_8 = new JButton("Back");
		btnNewButton_8.setBounds(12, 641, 1220, 60);
		btnNewButton_8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actionSelection();
			}
		});
		frame.getContentPane().add(btnNewButton_8);

		frame.getContentPane().repaint();

	}

	public void boxSelection(Player p) {
		frame.getContentPane().removeAll();

		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(12, 276, 1208, 351);
		frame.getContentPane().add(scroll);

		JPanel panel = new JPanel();
		scroll.setViewportView(panel);
		panel.setLayout(new WrapLayout(WrapLayout.LEFT, 5, 5));

		StringBuilder sl = new StringBuilder();
		StringBuilder sr = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			try {
				Monster m = p.team.get(i);
				if (i % 2 == 0) {
					sl.append(m.name + " Lv." + m.exp.level + " " + m.stats.hp + "/" + m.stats.maxHP + "HP" + "<br>");
				} else {
					sr.append(m.name + " Lv." + m.exp.level + " " + m.stats.hp + "/" + m.stats.maxHP + "HP" + "<br>");
				}
			} catch (Exception e) {
				if (i % 2 == 0) {
					sl.append("<br>");
				} else {
					sr.append("<br>");
				}
			}
		}

		String monsterStringL = "<html>" + sl.toString() + "</htlm";
		String monsterStringR = "<html>" + sr.toString() + "</htlm";

		JLabel lbl = new JLabel(monsterStringL, SwingConstants.CENTER);
		lbl.setBounds(12, 13, 599, 250);
		frame.getContentPane().add(lbl);
		lbl.setFont(new Font(lbl.getFont().getName(), Font.PLAIN, 20));

		JLabel label = new JLabel(monsterStringR, SwingConstants.CENTER);
		label.setBounds(633, 13, 599, 250);
		frame.getContentPane().add(label);
		label.setFont(new Font(lbl.getFont().getName(), Font.PLAIN, 20));

		for (Monster m : p.box) {
			JButton b = new JButton("<html>" + m.name + "<br>" + " Lv." + m.exp.level + "<br>" + m.stats.hp + "/"
					+ m.stats.maxHP + " HP" + "</html>");
			b.setPreferredSize(new Dimension(580, 100));
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// Auswahl zwischen Stats und Swap
					// JOptionPane ?

					showStats(p, m, 1);
				}
			});
			panel.add(b);

		}
		scroll.setBorder(null);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(12, 640, 1208, 50);
		frame.getContentPane().add(btnBack);
		btnBack.setPreferredSize(new Dimension(1220, 50));
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				teamOrBox(p);
			}
		});

		frame.setVisible(true);

		frame.getContentPane().repaint();
	}
}
