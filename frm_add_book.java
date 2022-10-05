import java.sql.*;
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
class frm_add_book extends JFrame implements TextListener,ActionListener
{
	Label l1=new Label("title");
	Label l2=new Label("author");
	Label l3=new Label("publisher");
	Label l4=new Label("price");
	Label l5=new Label("quantity");
	TextField t1=new TextField();
	TextField t2=new TextField();
	TextField t3=new TextField();
	TextField t4=new TextField();
	TextField t5=new TextField();
	Button b1=new Button("save");
	Button b2=new Button("cancel");
	frm_add_book()
	{
		setLayout(new GridLayout(6,2));
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(l3);
		add(t3);
		add(l4);
		add(t4);
		add(l5);
		add(t5);
		add(b1);
		add(b2);
		setSize(400,400);
                setTitle("Add Book");
		b1.setEnabled(false);
		b1.addActionListener(this);
		b2.addActionListener(this);
		t4.addTextListener(this);
		t5.addTextListener(this);
	}
	
	public void textValueChanged(TextEvent e)
	{
		if((t4.getText().equals("")==false) && (t5.getText().equals("")==false))
		{
			b1.setEnabled(true);
		}
		else
			b1.setEnabled(false);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b2)
			this.dispose();
		else
		{
			String q="insert into books (title,author,publisher,price,qty) values ('"+t1.getText()+"','"+t2.getText()+"','"+t3.getText()+"','"+t4.getText()+"','"+t5.getText()+"')";
			
			if(DBLayer.executeq(q)==true)
			{
				JOptionPane.showMessageDialog(this,"book added successfully");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
			}
		}
	}
}
class test{
	public static void main (String[] args)
	{
		frm_add_book f=new frm_add_book();
		f.show();
	}
}