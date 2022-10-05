import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

class purchase_history extends JFrame implements TextListener
{
	JScrollPane jsp;
	JTable jt;
	String data[][];
	String heads[]={"PURCHASEID","BOOKID","QUANTITY","PRICE","DATE OF PURCHASE"};
        Label l1;
	
        purchase_history()
	{
		setTitle("Purchase History");
		setLayout(null);
		setSize(1000,600);
                getContentPane().setBackground(Color.pink);
               
                
                        
		String m="select count(*) from purchase";
		data=new String[Integer.parseInt(DBLayer.getScalar(m))][6];
		jt=new JTable(data,heads);
                
		jsp=new JScrollPane(jt);
		jsp.setBounds(50,100,900,375);
                
                l1=new Label("PURCHASE HISTORY");
                l1.setBounds(50, 40, 500, 60);
                l1.setFont(new Font("Algerian",Font.BOLD|Font.ITALIC,40));
                add(l1);
		add(jsp);
		
		try
		{
			String q="select * from purchase";
			ResultSet rs=DBLayer.getResult(q);
			int i=0;
			while(rs.next())
			{
				System.out.println("filling row "+i);
				data[i][0]=rs.getString("purchaseId");
				data[i][1]=rs.getString("bookid");
				data[i][2]=rs.getString("qty");
				data[i][3]=rs.getString("price");
				data[i][4]=rs.getString("date_of_purchase");
                                
		
				i++;
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"exception"+ex);
		}
	}
	public void textValueChanged(TextEvent e)
	{
		String m="select count(*) from purchase";
		data=new String[Integer.parseInt(DBLayer.getScalar(m))][6];
		
		String q="select * from purchase ";
		ResultSet rs=DBLayer.getResult(q);
		
		int i=0;
		try
		{
			while(rs.next())
			{
				System.out.println(" row "+i);
				data[i][0]=rs.getString("purchaseId");
				data[i][1]=rs.getString("bookid");
				data[i][2]=rs.getString("qty");
				data[i][3]=rs.getString("price");
				data[i][4]=rs.getString("date_of_prchase");
                                
				
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
	
}
