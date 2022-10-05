import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class frm_add_purchase extends JFrame implements ItemListener,ActionListener
{
	Choice c1=new Choice();
	Label l1=new Label();
	Label l2=new Label();
	Label l3=new Label("price");
	Label l4=new Label("quantity");
	Label l5=new Label("current stock");
	Label l6=new Label("bookid");
	JButton b1=new JButton("submit");
	JButton b2=new JButton("cancel");
	JButton b3=new JButton("add book");
	TextField t1=new TextField();
	TextField t2=new TextField();
	TextField t3=new TextField();
	
	frm_add_purchase()
	{
		setLayout(new GridLayout(8,2));
		setSize(400,400);
                setTitle("Add Purchase");
                getContentPane().setBackground(Color.pink);
		add(l6);
		add(c1);
		add(l1);
		add(l2);
		add(l3);
		add(t1);
		add(l5);
		add(t2);
		add(l4);
		add(t3);
		add(b1);
		add(b2);
		add(b3);
		t2.setEditable(false);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		c1.addItemListener(this);
		c1.addItem("select");
		String q="select bookid from books order by bookid";
		ResultSet rs=DBLayer.getResult(q);
		try
		{
			while(rs.next())
				c1.addItem(rs.getString(1));
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"error in choice filling"+ex);
		}
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		String q="select * from books where bookid="+c1.getSelectedItem();
		ResultSet rs=DBLayer.getResult(q);
		try
		{
			rs.next();
			l1.setText(rs.getString("title"));
			l2.setText(rs.getString("author"));
			t1.setText(rs.getString("price"));
			t2.setText(rs.getString("qty"));
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"error in itemStateChanged"+ex);
		}
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b2)
			this.dispose();
		else if(e.getSource()==b1)
		{
			String q="insert into purchase (bookid,qty,price,date_of_purchase) values ("+c1.getSelectedItem()+","+t3.getText()+","+t1.getText()+",NOW())";
			if(DBLayer.executeq(q)==true)
			{
				String m="update books set qty=(qty+"+t3.getText()+") where bookid="+c1.getSelectedItem();
				if(DBLayer.executeq(m)==true)
				{
					t2.setText("");
					t3.setText("");
					t1.setText("");
				//	c1.insert("select",0);
				//	c1.select(0);
					
					c1.select("select");
				}
			}
			else
				JOptionPane.showMessageDialog(this,"insert nhi hoya");
		}
		else if(e.getSource()==b3)
			new frm_add_book().show();
	}
	
}