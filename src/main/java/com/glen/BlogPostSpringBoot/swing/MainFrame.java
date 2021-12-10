package com.glen.BlogPostSpringBoot.swing;

import java.awt.*;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glen.BlogPostSpringBoot.panels.coursesTablePanel;
import com.glen.BlogPostSpringBoot.pojos.Course;
import com.glen.BlogPostSpringBoot.services.CourseService;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.lang.reflect.Array;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import java.awt.BorderLayout;

@SuppressWarnings("serial")
@Component
public class MainFrame extends JFrame{
	
	JTable coursesTbl;
	JScrollPane pane; 
	CourseService courseService;
	JLabel idlbl,titlelbl,descriptionlbl,linklbl;
	JTextField idtxt,titletxt,descriptiontxt,linktxt;
	JButton addBtn,editBtn,deleteBtn,refreshBtn;
	DefaultTableModel courseModel;

	@Autowired
	public MainFrame(CourseService courseService) throws HeadlessException {
		this.courseService = courseService;
		initTable();
		initForm();
		initButtons();
		init();
		initListeners();
	}
	
	private void initButtons() {
		JPanel btnPnl = new JPanel();
		btnPnl.setSize(100,100);
		btnPnl.setLayout(new GridLayout(1,4));
		addBtn = new JButton("Add");
		editBtn = new JButton("Update");
		deleteBtn = new JButton("Delete");
		refreshBtn = new JButton("Refresh");
		btnPnl.add(addBtn);
		btnPnl.add(editBtn);
		btnPnl.add(deleteBtn);
		btnPnl.add(refreshBtn);
		add(btnPnl);
	}

	private void initListeners() {
		coursesTbl.getSelectionModel()
		.addListSelectionListener(e->{
			if(coursesTbl.getSelectedRow()!=-1)
				setFormValues(coursesTbl.getSelectedRow());
		});
		
		addBtn.addActionListener((ae)->{
			if(titletxt.getText()!=""
					&& descriptiontxt.getText()!=""
					&& linktxt.getText()!=""
			) {
				Course newCourse = new Course(
						titletxt.getText(),
						descriptiontxt.getText(),
						linktxt.getText()
				);
				if(courseService.addNewCourse(newCourse))
					refreshTable();
			}
			
		});
		
		deleteBtn.addActionListener(ae->{
			int id = Integer.parseInt(
					(String) coursesTbl
					.getValueAt(coursesTbl.getSelectedRow(), 0)
			);
			if(id>0) {
				if(courseService.deleteById(id)) {
					refreshTable();
				}
				
			}
		});
		
		editBtn.addActionListener(ae->{
			if(titletxt.getText()!=""
					&& descriptiontxt.getText()!=""
					&& linktxt.getText()!=""
			) {
				Course editCourse = new Course(
						titletxt.getText(),
						descriptiontxt.getText(),
						linktxt.getText()
				);
				
				int id= Integer.parseInt(
						(String) coursesTbl
						.getValueAt(coursesTbl.getSelectedRow(), 0));
				if(id>1) {
					if(courseService.updateCourse(editCourse,id))
						refreshTable();
				}
				
			}
		});
		
		refreshBtn.addActionListener(ae->{
			refreshTable();
		});
		
	}

	private void setFormValues(int selectedRow) {
		idtxt.setText((String) coursesTbl.getValueAt(selectedRow, 0));
		titletxt.setText((String)coursesTbl.getValueAt(selectedRow, 1));
		descriptiontxt.setText((String)coursesTbl.getValueAt(selectedRow, 2));
		linktxt.setText((String)coursesTbl.getValueAt(selectedRow, 3));
	}

	private void initForm() {
		JPanel formPnl = new JPanel();
		formPnl.setLayout(new GridLayout(4, 2));
		idlbl = new JLabel("Id");
		idtxt = new JTextField(20);
		titlelbl=new JLabel("Title");
		titletxt = new JTextField(20);
		descriptionlbl = new JLabel("Description");
		descriptiontxt = new JTextField(20);
		linklbl = new JLabel("Link");
		linktxt = new JTextField(20);
		formPnl.add(idlbl);
		formPnl.add(idtxt);
		formPnl.add(titlelbl);
		formPnl.add(titletxt);
		formPnl.add(descriptionlbl);
		formPnl.add(descriptiontxt);
		formPnl.add(linklbl);
		formPnl.add(linktxt);
		add(formPnl);
		
	}

	public void init() {
		
		setVisible(true);
		setSize(800,400);
		setTitle("Courses");
		setLayout(new GridLayout(3, 1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	private void initTable() {
		generateCourseModel();
		coursesTbl = new JTable(courseModel);
		pane = new JScrollPane(coursesTbl);
		add(pane);
		
	}
	
	private void generateCourseModel() {
		String cols[]={"ID","TITLE","DESCRIPTION","LINK"};
		String[][] data = courseService.getAllCoursesAsStringArray();
		courseModel = new DefaultTableModel(data, cols);
	}
	
	private void refreshTable() {
		generateCourseModel();
		coursesTbl.setModel(courseModel);
		coursesTbl.repaint();
	}
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	
}
