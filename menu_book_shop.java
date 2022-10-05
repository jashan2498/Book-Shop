import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class Menu_Book_Shop extends MenuBar implements ActionListener
{
	Menu m1=new Menu("Books");
	Menu m2=new Menu("Sales");
	Menu m3=new Menu("Purchase");
	Menu m4=new Menu("Manage");
	MenuItem mi1=new MenuItem("add Book");
	MenuItem mi2=new MenuItem("View");
	MenuItem mi3=new MenuItem("Modify");
	MenuItem mi4=new MenuItem("Quit");
	MenuItem mi5=new MenuItem("add Sale");
	MenuItem mi6=new MenuItem("Sales History");	
	
	
	public Menu_Book_Shop()
	{
		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		mi4.addActionListener(this);
		mi5.addActionListener(this);
		mi6.addActionListener(this);
		
		
		m4.add(mi2);
		m4.add(mi3);
		
		m1.add(mi1);
		m1.add(m4);
		m1.add(mi4);
		
		m2.add(mi5);
		m2.add(mi6);
		
		this.add(m1);
		this.add(m2);
		this.add(m3);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==mi4)
			System.exit(0);
		if(e.getSource()==mi1)
			new frm_add_book().show();
		else if(e.getSource()==mi5)
			new frm_add_sale().show();
		else if(e.getSource()==mi2)
			new frm_view_books().show();
	}
	
}


