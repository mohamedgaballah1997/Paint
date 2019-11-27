package paint.view;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import paint.controller.ColorState;
import paint.controller.DirectState;
import paint.controller.DrawingState;
import paint.controller.EditingState;
import paint.controller.GuiState;
import paint.controller.SaveLoadState;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Graphics;

public class GUI extends JFrame{
	private JPanel panel;
	//Point clicked;
	//For swaping between States
	GuiState guiState;
	//For colors:
	final JFileChooser fcOpen;
	JColorChooser jchoose;
	//for save:
	final JFileChooser fcSave;
	//jj
	
	
	public GUI() {
		guiState =new GuiState();
		jchoose= new JColorChooser();
		//clicked = new Point();
		ActionListener a = new myActionListener();
		MouseListener mListener = new myMouseListener();
		MouseMotionListener mMotion = new myMouseMotion();
		//for Saving:
		
		fcOpen = new JFileChooser("E:\\");	
		fcOpen.setFileFilter(new FileNameExtensionFilter( "XML",  "xml"));		
		fcOpen.setFileFilter(new FileNameExtensionFilter( "JSON",  "json"));	
		fcOpen.setAcceptAllFileFilterUsed(false);
		
		fcSave = new JFileChooser("E:\\");
		fcSave.addChoosableFileFilter(new FileNameExtensionFilter( "XML",  "xml"));
		fcSave.addChoosableFileFilter(new FileNameExtensionFilter( "JSON",  "Json"));
		
		fcSave.setAcceptAllFileFilterUsed(false);
		
		getContentPane().setBackground(new Color(0, 0, 128));	
		setSize(1370, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(null);
			

		panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		panel.setBounds(278, 0, 1076, 681);
		panel.addMouseListener(mListener);
		panel.addMouseMotionListener(mMotion);
		getContentPane().add(panel);		
		
	
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, null, null));
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(new Color(0, 0, 128));
		panel_1.setBounds(0, 0, 277, 98);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblWeCodeSo = new JLabel("We code");
		lblWeCodeSo.setFont(new Font("Brush Script MT", Font.PLAIN, 22));
		lblWeCodeSo.setForeground(Color.WHITE);
		lblWeCodeSo.setBounds(30, 0, 180, 25);
		panel_1.add(lblWeCodeSo);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GUI.class.getResource("/imgs/paint.png")));
		label.setBounds(208, 0, 75, 76);
		panel_1.add(label);
		
		JLabel lblP = new JLabel("P A I N T !!");
		lblP.setFont(new Font("Brush Script MT", Font.BOLD | Font.ITALIC, 22));
		lblP.setForeground(Color.WHITE);
		lblP.setBounds(10, 53, 132, 34);
		panel_1.add(lblP);
		
		JLabel lblSoYouDont = new JLabel("so you don\u2019t  have to");
		lblSoYouDont.setFont(new Font("Brush Script MT", Font.PLAIN, 22));
		lblSoYouDont.setForeground(Color.WHITE);
		lblSoYouDont.setBounds(10, 25, 167, 25);
		panel_1.add(lblSoYouDont);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 128));
		panel_2.setBounds(10, 109, 267, 151);		
		panel_2.setLayout(null);
		
		
		JButton btnRectangle = new JButton("Rectangle");
		btnRectangle.setIcon(new ImageIcon(GUI.class.getResource("/imgs/Rectangle.png")));
		btnRectangle.setBounds(0, 0, 65, 65);
		btnRectangle.addActionListener(a);
		btnRectangle.setActionCommand("paint.model.Rectangle");
		panel_2.add(btnRectangle);		
		
		JButton btnSquare = new JButton("Square");
		btnSquare.setIcon(new ImageIcon(GUI.class.getResource("/imgs/Square.png")));
		btnSquare.setBounds(87, 0, 65, 65);
		btnSquare.addActionListener(a);
		btnSquare.setActionCommand("paint.model.Square");
		panel_2.add(btnSquare);
		
		JButton btnCircle = new JButton("Circle");
		btnCircle.setBounds(87, 75, 65, 65);
		btnCircle.setIcon(new ImageIcon(GUI.class.getResource("/imgs/Circle.png")));
		btnCircle.addActionListener(a);
		btnCircle.setActionCommand("paint.model.Circle");
		panel_2.add(btnCircle);
		
		JButton btnLine = new JButton("Line");
		btnLine.setIcon(new ImageIcon(GUI.class.getResource("/imgs/Line.png")));
		btnLine.setBounds(174, 0, 65, 65);
		btnLine.addActionListener(a);
		btnLine.setActionCommand("paint.model.LineSegment");
		panel_2.add(btnLine);
		
		JButton btnEllipse = new JButton("Ellipse");
		btnEllipse.setIcon(new ImageIcon(GUI.class.getResource("/imgs/Ellipse.png")));
		btnEllipse.setBounds(0, 75, 65, 65);
		btnEllipse.addActionListener(a);
		btnEllipse.setActionCommand("paint.model.Ellipse");
		panel_2.add(btnEllipse);
		
		JButton btnTriangle = new JButton("Triangle");
		btnTriangle.setIcon(new ImageIcon(GUI.class.getResource("/imgs/Triangle.png")));
		btnTriangle.addActionListener(a);
		btnTriangle.setActionCommand("paint.model.Triangle");
		btnTriangle.setBounds(174, 75, 65, 65);
		panel_2.add(btnTriangle);	
		
		/*
		JButton btnPlugin = new JButton("Plugin");
	
		btnPlugin.setActionCommand("");
		btnPlugin.addActionListener(a);
		btnPlugin.setBounds(238, 0, 65, 65);		
		panel_2.add(btnPlugin);*/
		getContentPane().add(panel_2);		
	
		
		JButton fill = new JButton("");
		fill.setIcon(new ImageIcon(GUI.class.getResource("/imgs/fill.png")));
		fill.setBounds(12, 356, 65, 70);
		fill.addActionListener(a);
		fill.setActionCommand("colorInside");
		getContentPane().add(fill);
		
		JButton border = new JButton("border");
		border.addActionListener(a);
		border.setActionCommand("colorBorder");		
		border.setIcon(new ImageIcon(GUI.class.getResource("/imgs/border.jpg")));
		border.setBounds(97, 356, 65, 70);
		getContentPane().add(border);
		
		JLabel lblFillColor = new JLabel("Fill Color");
		lblFillColor.setForeground(Color.CYAN);
		lblFillColor.setBounds(12, 427, 64, 14);			
		getContentPane().add(lblFillColor);
		
		JButton deselect = new JButton("deselect");		
		deselect.setIcon(new ImageIcon(GUI.class.getResource("/imgs/deselect.png")));
		deselect.setBounds(97, 540, 65, 70);
		deselect.addActionListener(a);
		deselect.setActionCommand("deselect");
		getContentPane().add(deselect);
		
		JButton undo = new JButton("undo");
		undo.setBackground(Color.WHITE);
		undo.setIcon(new ImageIcon(GUI.class.getResource("/imgs/undo.jpg")));
		undo.setBounds(10, 457, 65, 70);
		undo.setActionCommand("undo");
		undo.addActionListener(a);
		getContentPane().add(undo);
		
		JButton select = new JButton("");
		select.setIcon(new ImageIcon(GUI.class.getResource("/imgs/selecto.png")));
		select.setBounds(10, 540, 65, 70);
		select.addActionListener(a);
		select.setActionCommand("select");
		getContentPane().add(select);		
		
		
		JButton redo = new JButton("");
		redo.setIcon(new ImageIcon(GUI.class.getResource("/imgs/Redo.png")));
		redo.setBounds(97, 457, 65, 70);
		redo.setActionCommand("redo");
		redo.addActionListener(a);
		getContentPane().add(redo);
		
		JButton save = new JButton("");
		save.setIcon(new ImageIcon(GUI.class.getResource("/imgs/save.png")));
		save.setBounds(184, 540, 65, 70);
		save.setActionCommand("save");
		save.addActionListener(a);
		getContentPane().add(save);
		
		JButton button_7 = new JButton("");
		button_7.setIcon(new ImageIcon(GUI.class.getResource("/imgs/load.png")));
		button_7.setBounds(184, 457, 65, 70);
		button_7.addActionListener(a);
		button_7.setActionCommand("load");
		getContentPane().add(button_7);
		
		JButton btnMove = new JButton("Move");
		btnMove.setBackground(Color.WHITE);
		btnMove.setIcon(new ImageIcon(GUI.class.getResource("/imgs/Move.png")));
		btnMove.setBounds(10, 270, 65, 70);
		btnMove.setActionCommand("move");
		btnMove.addActionListener(a);
		getContentPane().add(btnMove);
		
		JButton btnResize = new JButton("Resize");
		btnResize.setBackground(Color.WHITE);
		btnResize.setBounds(97, 270, 65, 70);
		btnResize.setIcon(new ImageIcon(GUI.class.getResource("/imgs/resize.png")));
		btnResize.addActionListener(a);
		btnResize.setActionCommand("resize");
		getContentPane().add(btnResize);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBackground(Color.WHITE);
		btnRemove.setBounds(184, 270, 65, 70);
		btnRemove.setActionCommand("remove");
		btnRemove.setIcon(new ImageIcon(GUI.class.getResource("/imgs/delete.png")));
		btnRemove.addActionListener(a);
		getContentPane().add(btnRemove);
		
		JButton btnCopy = new JButton("Copy");
		btnCopy.setBackground(Color.WHITE);
		btnCopy.setBounds(184, 356, 65, 70);
		getContentPane().add(btnCopy);
		btnCopy.setIcon(new ImageIcon(GUI.class.getResource("/imgs/copy.png")));
		btnCopy.setActionCommand("copy");
		btnCopy.addActionListener(a);
		setVisible(true);
	}


private class myActionListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("paint.model.Square")) {
			guiState.setState(new DrawingState(panel.getGraphics(), "square"));
		}
		else if(arg0.getActionCommand().equals("paint.model.Circle")) {
				guiState.setState(new DrawingState(panel.getGraphics(), "circle"));
		}
		else if(arg0.getActionCommand().equals("paint.model.Ellipse")) {
			guiState.setState(new DrawingState(panel.getGraphics(), "ellipse"));
		}
		else if(arg0.getActionCommand().equals("paint.model.Rectangle")) {
			guiState.setState(new DrawingState(panel.getGraphics(), "rectangle"));
		}
		else if(arg0.getActionCommand().equals("paint.model.LineSegment")) {
			guiState.setState(new DrawingState(panel.getGraphics(), "line"));
		}
		else if(arg0.getActionCommand().equals("paint.model.Triangle")) {
			guiState.setState(new DrawingState(panel.getGraphics(), "triangle"));
		}
		else if(arg0.getActionCommand().equals("deselect")) {
			if(guiState.ifSelected())
			guiState.setState(new EditingState(panel.getGraphics(),"deselect"));
			else JOptionPane.showMessageDialog(null, "Choose Shape first!!!!!!!!!!");  
		}
		else if(arg0.getActionCommand().equals("select")) { 
			guiState.setState(new EditingState(panel.getGraphics(),"select"));
		}
		else if(arg0.getActionCommand().equals("move")) {
			if(guiState.ifSelected())
			guiState.setState(new EditingState(panel.getGraphics(),"move"));
			else JOptionPane.showMessageDialog(null, "Choose Shape first!!!!!!!!!!");  
		}
		else if(arg0.getActionCommand().equals("resize")) {
			if(guiState.ifSelected())
			guiState.setState(new EditingState(panel.getGraphics(),"resize"));
			else JOptionPane.showMessageDialog(null, "Choose Shape first!!!!!!!!!!");  
		}
		else if(arg0.getActionCommand().equals("undo")) {
			guiState.setState(new DirectState(panel.getGraphics(),"undo"));
		}
		else if(arg0.getActionCommand().equals("redo")) {
			guiState.setState(new DirectState(panel.getGraphics(),"redo"));
		}
		else if(arg0.getActionCommand().equals("remove"))
		{
			if(guiState.ifSelected())
			guiState.setState(new DirectState(panel.getGraphics(),"remove"));
			else JOptionPane.showMessageDialog(null, "Choose Shape first!!!!!!!!!!");  
		}
		else if(arg0.getActionCommand().equals("colorInside"))
		{
			if(guiState.ifSelected()) {
			Color selectedColor= jchoose.showDialog(panel,"Choose Fill color",Color.white);
			guiState.setState(new ColorState(panel.getGraphics(), "colorInside",selectedColor));
			}
			else JOptionPane.showMessageDialog(null, "Choose Shape first!!!!!!!!!!");  
		}
		else if (arg0.getActionCommand().equals("copy"))
		{
			if(guiState.ifSelected())
			guiState.setState(new DirectState(panel.getGraphics(),"copy"));
			else JOptionPane.showMessageDialog(null, "Choose Shape first!!!!!!!!!!");  
		}
		else if(arg0.getActionCommand().equals("colorBorder"))
		{
			if(guiState.ifSelected()) {
			Color selectedColor= jchoose.showDialog(panel,"Choose Border color",Color.white);
			guiState.setState(new ColorState(panel.getGraphics(), "colorBorder",selectedColor));
		}
			else JOptionPane.showMessageDialog(null, "Choose Shape first!!!!!!!!!!");  
		}
		else if (arg0.getActionCommand().equals("save"))
		 {
			if(fcSave.showSaveDialog(panel) == JFileChooser.APPROVE_OPTION)
	    	{
		    	String path=fcSave.getSelectedFile().getPath();
		    		path+="."+fcSave.getFileFilter().getDescription().toLowerCase();
		    	guiState.setState(new SaveLoadState(path, "save",panel.getGraphics()));
		    	JOptionPane.showMessageDialog(null, "Saved successfully");
	    	}
		 }
			else if (arg0.getActionCommand().equals("load"))
			 {
				if(fcOpen.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION)
		    	{
			    	String path=fcOpen.getSelectedFile().getPath();		    	
			   	File f=new File(path);
			    	if(!f.exists())
		                JOptionPane.showMessageDialog(null, "No such file");  
			    	else
			    	guiState.setState(new SaveLoadState(path, "load",panel.getGraphics()));
		    	}
			}
		repaint();
	}
}
private class myMouseListener implements MouseListener{
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (guiState.getState()!=null)
			guiState.getState().doClicked(e.getPoint());		
			repaint();
	}
	
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		if (guiState.getState()!=null)
			guiState.getState().doPressed(arg0.getPoint());		
			repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (guiState.getState()!=null)
			guiState.getState().doReleased(arg0.getPoint());		
			repaint();
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	
}

private class myMouseMotion  implements MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent e) {
		if (guiState.getState()!=null)
			guiState.getState().doDraged(e.getPoint());	
	//	guiState.getState().draw();
			repaint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		panel.setCursor(Cursor.getDefaultCursor());
	}
}	

public void paint(Graphics g) {
	super.paint(g);
	try {
		guiState.getState().draw();
	}
	catch (Exception e) {
	}
}	
public static void main(String[] args) {
	new GUI();
}
}