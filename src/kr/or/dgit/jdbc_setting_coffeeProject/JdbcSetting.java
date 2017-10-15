package kr.or.dgit.jdbc_setting_coffeeProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;

public class JdbcSetting extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JdbcSetting frame = new JdbcSetting();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JdbcSetting() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 118);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 1, 0, 0));
		
		JButton btnInit = new JButton(new BtnAction("초기화"));
		contentPane.add(btnInit);
		
		JButton btnExport = new JButton(new BtnAction("백업"));
		contentPane.add(btnExport);
		
		JButton btnImport = new JButton(new BtnAction("복원"));
		contentPane.add(btnImport);
	}

}
