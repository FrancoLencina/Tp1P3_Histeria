package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;

import Scores.Score;

import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class InitScreen {

	Dimension screenSize;
    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InitScreen window = new InitScreen();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public InitScreen() {
    	screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Hysteria");
        frame.setBounds(0, 0, screenSize.width, screenSize.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JLabel lblSeleccionDificultad = new JLabel("Seleccione la dificultad");
        lblSeleccionDificultad.setFont(new Font("Arial", Font.BOLD, 48));
        frame.getContentPane().add(lblSeleccionDificultad, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 2));
        frame.getContentPane().add(panelBotones, BorderLayout.CENTER);

        JButton btnFacil = new JButton("Fácil (4x4)");
        btnFacil.setForeground(new Color(255, 255, 255));
        btnFacil.setFont(new Font("Tahoma", Font.PLAIN, 48));
        btnFacil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GameScreen vw = new GameScreen(4);
				frame.dispose();
				vw.frame.setVisible(true);
        	}
        });
        btnFacil.setBackground(new Color(50,50,50));
        panelBotones.add(btnFacil);

        JButton btnNormal = new JButton("Normal (5x5)");
        btnNormal.setForeground(new Color(255, 255, 255));
        btnNormal.setFont(new Font("Tahoma", Font.PLAIN, 48));
        btnNormal.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GameScreen vw = new GameScreen(5);
				frame.dispose();
				vw.frame.setVisible(true);
        	}
        });
        btnNormal.setBackground(new Color(50,50,50));
        panelBotones.add(btnNormal);

        JButton btnDificil = new JButton("Difícil (6x6)");
        btnDificil.setForeground(new Color(255, 255, 255));
        btnDificil.setFont(new Font("Tahoma", Font.PLAIN, 48));
        btnDificil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GameScreen vw = new GameScreen(6);
				frame.dispose();
				vw.frame.setVisible(true);
        	}
        });
        btnDificil.setBackground(new Color(50,50,50));
        panelBotones.add(btnDificil);

        JButton btnMuyDificil = new JButton("Muy difícil (7x7)");
        btnMuyDificil.setForeground(new Color(255, 255, 255));
        btnMuyDificil.setFont(new Font("Tahoma", Font.PLAIN, 48));
        btnMuyDificil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GameScreen vw = new GameScreen(7);
				frame.dispose();
				vw.frame.setVisible(true);
        	}
        });
        btnMuyDificil.setBackground(new Color(50,50,50));
        panelBotones.add(btnMuyDificil);
        
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Reiniciar puntuaciones");
        mntmNewMenuItem.setBackground(new Color(128, 128, 128));
        mntmNewMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Score.resetScores();
        		JOptionPane.showMessageDialog(null, "Las puntuaciones han sido reiniciadas");
        	}
        });
        menuBar.add(mntmNewMenuItem);
    }
}