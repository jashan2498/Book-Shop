import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

class frmModifyBook extends JFrame implements ActionListener,TextListener
{
	TextField t=new TextField();
	TextField t1=new TextField();
	TextField t2=new TextField();
	TextField t3=new TextField();
	TextField t4=new TextField();
	//TextField t5=new TextField();
	Label L=new Label("enter Book id");
	Label L1=new Label("Title");
	Label L2=new Label("Author");
	Label L3=new Label("Publisher");
	Label L4=new Label("Price");
	//Label L5=new Label("ReOrder Level");
	Button b1=new Button("Update");
	Button b2=new Button("Remove");
	Panel p=new Panel();
		
	public frmModifyBook()
		{
			setSize(350,400);
			setLayout(null);
			setTitle("Modify Book");
                        getContentPane().setBackground(Color.pink);
			
			b1.addActionListener(this);
		//	b1.setEnabled(false);
			
			b2.addActionListener(this);
			t.addTextListener(this);
			
			L.setBounds(80,50,80,25);
			t.setBounds(180,50,100,25);
			
			p.setLayout(new GridLayout(5,2));
			p.setBounds(50,100,250,200);
			p.add(L1);
			p.add(t1);
			p.add(L2);
			p.add(t2);
			p.add(L3);
			p.add(t3);
			p.add(L4);
			p.add(t4);
			//p.add(L5);
			//p.add(t5);
			p.add(b1);
			p.add(b2);
			
			p.setVisible(false);
			
			add(L);
			add(t);
			add(p);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==b2)
			{
				int k=JOptionPane.showConfirmDialog(this,"realy delete book ith id "+t.getText());
				if(k==0)
				{
					DBLayer.executeq("delete from books where bookid="+t.getText());
					t.setText("");
				}
			}	
                        else if(e.getSource()==b1)
                        {
                            DBLayer.executeq("update books set title='"+t1.getText()+"',author='"+t2.getText()+"',publisher='"+t3.getText()+"',price='"+t4.getText()+"' where bookid='"+t.getText()+"'");
                            JOptionPane.showMessageDialog(this, "Book details Updated");
                        }
		}
		
		public void textValueChanged(TextEvent e)
		{
                    if(t.getText().trim().equalsIgnoreCase(""))
                    {
                        p.setVisible(false);
                        return;
                    }
			ResultSet rs=DBLayer.getResult("select * from books where bookid="+t.getText());
			try
			{
			if(rs==null || rs.next()==false)
			{
				t.setForeground(Color.red);
				p.setVisible(false);
			}
			else
			{
				t.setForeground(Color.black);
				p.setVisible(true);
				t1.setText(rs.getString("title"));
				t2.setText(rs.getString(3));
				t3.setText(rs.getString(4));
				t4.setText(rs.getString(5));
				//t5.setText(rs.getString(6));
				
			}
			}
			catch(Exception ex)
			{
				System.out.println("Err:"+ex);
			}
		}
}








