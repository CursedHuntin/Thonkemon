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

public class TestUI {

	private JFrame frame;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestUI window = new TestUI(new Wotah(50), new Krato(50));
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
	public TestUI(Monster m1, Monster m2) {
		initialize(m1, m2);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param m2
	 * @param m1
	 */
	private void initialize(Monster m1, Monster m2) {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setPreferredSize(new Dimension(900, 175));

		JButton btnFight = new JButton("Fight");
		panel.add(btnFight);

		JButton btnSwitch = new JButton("Switch");
		panel.add(btnSwitch);

		// JPanel panel_1 = new JPanel();
		// frame.getContentPane().add(panel_1, BorderLayout.NORTH);

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
		btnFight.setPreferredSize(new Dimension(435, 150));
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
		btnSwitch.setPreferredSize(new Dimension(435, 150));
		btnSwitch.setFont(new Font("Arial", Font.PLAIN, 30));
		panel.repaint();
	}

	private void fight(Monster m) {
		panel.removeAll();
		// panel.setLayout(new GridBagLayout());
		// GridBagConstraints c = new GridBagConstraints();
		// c.gridx = 0;
		// c.gridy = 0;
		JPanel movePanel = new JPanel();

		for (Move move : m.moves) {
			JButton btn = new JButton(move.name);
			btn.setPreferredSize(new Dimension(435, 55));
			btn.setVisible(true);
			panel.add(btn);
		}
		if (m.moves.size() % 2 == 1) {
			JButton btn = new JButton("Back");
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fightOrSwitch(m);
				}
			});
			btn.setPreferredSize(new Dimension(875, 50));
			btn.setVisible(true);
			panel.add(btn);
		} else if (m.moves.size() % 2 == 0) {
			JButton btn = new JButton("Back");
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fightOrSwitch(m);
				}
			});
			btn.setPreferredSize(new Dimension(875, 40));
			btn.setVisible(true);
			panel.add(btn);
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

	// private void manageMonsters(Player p) {
	// panel.removeAll();
	// panel.repaint();
	// for (Monster m : p.team) {
	// JButton btn = new JButton(m.name);
	// btn.setPreferredSize(new Dimension(435, 75));
	// btn.setVisible(true);
	// topPanel.add(btn);
	// }
	// topPanel.repaint();
	//
	// }
}
