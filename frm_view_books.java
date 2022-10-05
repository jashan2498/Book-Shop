import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

class frm_view_books extends JFrame implements TextListener,ActionListener
{
	JScrollPane jsp;
	JTable jt;
	String data[][];
	String heads[]={"id","title","author","publisher","price","qty"};
	TextField t=new TextField();
	CheckboxGroup cbg=new CheckboxGroup();
	Checkbox c1=new Checkbox("title",cbg,true);
	Checkbox c2=new Checkbox("author",cbg,false);
	Checkbox c3=new Checkbox("publisher",cbg,false);
	MenuBar mb=new MenuBar();
	Menu m1=new Menu("Purchase");
	Menu m2=new Menu("Sale");
	Menu m3=new Menu("Book");
	Menu m4=new Menu("Quit");
	MenuItem mi1= new MenuItem("Add Purchase");
	MenuItem mi2= new MenuItem("Purchase History");
	MenuItem mi3= new MenuItem("Add Sale");
	MenuItem mi4= new MenuItem("Sale History");
	MenuItem mi5= new MenuItem("Exit");
        MenuItem mi6= new MenuItem("Add Book");
	
	frm_view_books()
	{
		mi1.addActionListener(this);
                mi2.addActionListener(this);
                mi3.addActionListener(this);
		mi4.addActionListener(this);
		mi5.addActionListener(this);
                mi6.addActionListener(this);
		setLayout(null);
		setSize(1000,600);
                setTitle("View Stock");
                getContentPane().setBackground(Color.pink);
		String m="select count(*) from books";
		data=new String[Integer.parseInt(DBLayer.getScalar(m))][6];
		jt=new JTable(data,heads);
		jsp=new JScrollPane(jt);
		jsp.setBounds(50,100,900,375);
		add(jsp);
		c1.setBounds(160,60,80,30);
		c2.setBounds(250,60,80,30);
		c3.setBounds(340,60,80,30);
		add(c1);
		add(c2);
		add(c3);
		setMenuBar(mb);
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
                mb.add(m4);
		m1.add(mi1);
		m1.add(mi2);
		m2.add(mi3);
		m2.add(mi4);
                m3.add(mi6);
		m4.add(mi5);
		
		t.addTextListener(this);
		t.setBounds(50,60,100,30);
		add(t);
		try
		{
			String q="select * from books";
			ResultSet rs=DBLayer.getResult(q);
			int i=0;
			while(rs.next())
			{
				System.out.println("filling row "+i);
				data[i][0]=rs.getString("bookid");
				data[i][1]=rs.getString("title");
				data[i][2]=rs.getString("author");
				data[i][3]=rs.getString("publisher");
				data[i][4]=rs.getString("price");
				data[i][5]=rs.getString("qty");
				i++;
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"exception aa gyi"+ex);
		}
	}
	public void textValueChanged(TextEvent e)
	{
		String m="select count(*) from books";
		data=new String[Integer.parseInt(DBLayer.getScalar(m))][6];
		
		String q="select * from books where "+ cbg.getSelectedCheckbox().getLabel()+" like '%"+t.getText()+"%'";
		ResultSet rs=DBLayer.getResult(q);
		
		int i=0;
		try
		{
			while(rs.next())
			{
				System.out.println(" row "+i);
				data[i][0]=rs.getString("bookid");
				data[i][1]=rs.getString("title");
				data[i][2]=rs.getString("author");
				data[i][3]=rs.getString("publisher");
				data[i][4]=rs.getString("price");
				data[i][5]=rs.getString("qty");
				i++;
			}
try{remove(jsp);} catch(Exception ex){}

		jt=new JTable(data,heads);
		jsp=new JScrollPane(jt);
		jsp.setBounds(50,100,900,375);
		add(jsp);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"exception in textlistener"+ex);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==mi1)
			new frm_add_purchase().show();
                else if(e.getSource()==mi2)
			new purchase_history().show();
		else if(e.getSource()==mi3)
			new frm_add_sale().show();
                else if(e.getSource()==mi4)
			new sale_history().show();
                else if(e.getSource()==mi6)
			new frm_add_book().show();
		else if(e.getSource()==mi5)
			this.dispose();
	}
}
