import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class frm_add_sale extends JFrame implements ActionListener,ItemListener
{
	Choice c1=new Choice();
	Choice c2=new Choice();
	Panel p=new Panel();
	Label l1=new Label();
	Label l2=new Label();
	Label l3=new Label("price");
	Label l4=new Label("quantity");
	Label l5=new Label("sold to");
        
	Label l6=new Label("bookid");
	TextField t1=new TextField();
	TextField t2=new TextField();
        
        
//	TextField t3=new TextField();
//	TextField t4=new TextField();
	Button b1=new Button("submit");
	Button b2=new Button("cancel");
	
	frm_add_sale()
	{
		setLayout(null);
		setSize(500,500);
                setTitle("Add Sale");
                getContentPane().setBackground(Color.pink);
		add(l6);
		add(c1);
		add(l1);
		add(l2);
		add(p);
		p.add(l3);
		p.add(t1);
		p.add(l4);
		p.add(c2);
		p.add(l5);
		p.add(t2);
                
		p.add(b1);
		p.add(b2);
		p.setLayout(new GridLayout(4,2));
		l6.setBounds(20,20,100,40);
		c1.setBounds(130,20,100,40);
		l1.setBounds(20,70,200,40);
		l2.setBounds(20,120,200,50);
		p.setBounds(20,200,250,200);
		b1.addActionListener(this);
		b2.addActionListener(this);
		c1.addItemListener(this);
		b1.setEnabled(false);
		c1.addItem("select");
		try
		{
		String q="select bookid from books order by bookid";
		ResultSet rs=DBLayer.getResult(q);
		while(rs.next())
			c1.addItem(rs.getString(1));
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"exception in adding items in c1"+ex);
		}
			
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			String q="insert into sale(bookid,soldto,qty,price,date_of_sale) values ("+c1.getSelectedItem()+",'"+t2.getText()+"',"+c2.getSelectedItem()+",'"+t1.getText()+"',NOW())";
			System.out.println(q);
			if(DBLayer.executeq(q)==true)
			{
				String m="update books set qty=(qty-"+c2.getSelectedItem()+") where bookid="+c1.getSelectedItem();
				System.out.println(m);
				if(DBLayer.executeq(m)==true)
				{
					t1.setText("");
					t2.setText("");
				//	c1.insert("select",0);
					c1.select("select");
                                       
					b1.setEnabled(false);
					JOptionPane.showMessageDialog(this,"selled successfully");
				}
			}
			else
				JOptionPane.showMessageDialog(this,"not inserted");
		}
		else
			this.dispose();
	}
	public void itemStateChanged(ItemEvent e)
	{
		if(c1.getSelectedItem().equals("select"))
			c1.remove("select");
		else
		{
			c2.removeAll();
			try
			{
			String q="select * from books where bookid="+c1.getSelectedItem();
			ResultSet rs=DBLayer.getResult(q);
			rs.next();
			l1.setText(rs.getString("title"));
			l2.setText(rs.getString("author"));
			t1.setText(rs.getString("price"));
			int n=Integer.parseInt(rs.getString("qty"));
			for(int i=1;i<=n;i++)
				c2.addItem(""+i);
				
			b1.setEnabled(true);
			
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,"exception in itemStateChanged"+ex);
			}	
		}
		}
}