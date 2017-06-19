package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import monsters.Krato;
import monsters.Monster;
import monsters.Wotah;
import moves.Move;

public class UI {

	private JFrame frame;
	private JPanel panel;
	private JPanel topPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI(new Wotah(50), new Krato(50));
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI(Monster m1, Monster m2) {
		initialize(m1, m2);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Monster m1, Monster m2) {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(900, 200));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);

		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(900, 450));
		frame.getContentPane().add(topPanel, BorderLayout.CENTER);

		if (Fight.turn % 2 == 0)
			fightOrSwitch(m1);
		else
			fightOrSwitch(m2);

	}

	private void fightOrSwitch(Monster m) {
		panel.removeAll();
		JButton btnFight = new JButton("Fight");
		btnFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fight(m);
			}
		});
		panel.add(btnFight);
		btnFight.setPreferredSize(new Dimension(435, 175));
		btnFight.setFont(new Font("Arial", Font.PLAIN, 30));

		JButton btnSwitch = new JButton("Switch");
		btnSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnSwitch);
		btnSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchMonster(new Player("Kappa"), m);
			}
		});
		btnSwitch.setPreferredSize(new Dimension(435, 175));
		btnSwitch.setFont(new Font("Arial", Font.PLAIN, 30));

	}

	private void fight(Monster m) {
		panel.removeAll();
		panel.getRootPane().setLayout(new BorderLayout(0, 0));

		for (Move move : m.moves) {
			JButton btn = new JButton(move.name);
			btn.setPreferredSize(new Dimension(435, 70));
			btn.setVisible(true);
			panel.add(btn);
		}
		if (m.moves.size() % 2 == 1) {
			JButton blankBtn = new JButton("");
			blankBtn.setPreferredSize(new Dimension(435, 70));
			blankBtn.setVisible(true);
			panel.add(blankBtn);
			JButton btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					topPanel.removeAll();
					topPanel.repaint();
					fightOrSwitch(m);
				}
			});
			btnBack.setPreferredSize(new Dimension(875, 50));
			btnBack.setVisible(true);
			panel.add(btnBack);
		} else if (m.moves.size() % 2 == 0) {
			JButton btn = new JButton("Back");
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					topPanel.removeAll();
					topPanel.repaint();
					panel.remove(btn);
					panel.repaint();
					fightOrSwitch(m);
				}
			});
			btn.setPreferredSize(new Dimension(875, 50));
			btn.setVisible(true);
			panel.getRootPane().add(btn, BorderLayout.SOUTH);
		}

		panel.repaint();
		panel.setVisible(false);
		panel.setVisible(true);
	}

	private void switchMonster(Player p, Monster m) {
		panel.removeAll();
		if (p.team.size() % 2 == 0) {
			for (Monster mt : p.team) {
				JButton btn = new JButton(mt.name);
				btn.setPreferredSize(new Dimension(435, 75));
				btn.setVisible(true);
				panel.add(btn);
			}
			JButton btn = new JButton("Back");
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fightOrSwitch(m);
				}
			});
			btn.setPreferredSize(new Dimension(875, 75));
			btn.setVisible(true);
			panel.add(btn);
		}
		panel.repaint();
		panel.setVisible(false);
		panel.setVisible(true);
	}

	private void manageMonsters(Player p) {
		panel.removeAll();
		panel.repaint();
		for (Monster m : p.team) {
			JButton btn = new JButton(m.name);
			btn.setPreferredSize(new Dimension(435, 75));
			btn.setVisible(true);
			topPanel.add(btn);
		}
		topPanel.repaint();

	}

}
