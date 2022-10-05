import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class frm_main_menu extends JFrame implements ActionListener
{
	JButton b1=new JButton("ADD BOOK");
	JButton b2=new JButton("ADD SALE");
	JButton b3=new JButton("ADD PURCHASE");
	JButton b4=new JButton("VIEW STOCK");
	JButton b5=new JButton("EXIT");
        JButton b6=new JButton("MODIFY BOOK");
        JButton b7=new JButton("SALE HISTORY");
        JButton b8=new JButton("PURCHASE HISTORY");
	frm_main_menu()
	{
		setTitle("Book Shop Menu");
		setSize(400,600);
		setLayout(new GridLayout(8,1));
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
                b6.addActionListener(this);
                b7.addActionListener(this);
                b8.addActionListener(this);
                add(b1);
		add(b2);
		add(b3);
		add(b4);
                add(b6);
                add(b7);
                add(b8);
		add(b5);
                
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b5)
			System.exit(0);
		else if(e.getSource()==b2)
			new frm_add_sale().show();
		else if(e.getSource()==b4)
			new frm_view_books().show();
		else if(e.getSource()==b3)
			new frm_add_purchase().show();
                else if(e.getSource()==b1)
			new frm_add_book().show();
                else if(e.getSource()==b6)
                        new frmModifyBook().show();
                else if(e.getSource()==b7)
                        new sale_history().show();
                else if(e.getSource()==b8)
                        new purchase_history().show();
	}
	
	
}