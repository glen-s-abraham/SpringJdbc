package com.glen.BlogPostSpringBoot.panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class coursesTablePanel extends JPanel{
	private JTable course;
	public coursesTablePanel() {
		setBackground(Color.BLACK);
		
		course = new JTable();
		course.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"link", "description", "title", "id"
			}
		));
		add(course);
		
		
	}
}
