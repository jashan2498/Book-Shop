import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

class sale_history extends JFrame implements TextListener
{
	JScrollPane jsp;
	JTable jt;
	String data[][];
	String heads[]={"SALEID","BOOKID","SOLD TO","QUANTITY","PRICE","DATE OF SALE"};
        Label l1;
	
        sale_history()
	{
		setTitle("Sale History");
		setLayout(null);
		setSize(1000,600);
                getContentPane().setBackground(Color.pink);
               
                
                        
		String m="select count(*) from sale";
		data=new String[Integer.parseInt(DBLayer.getScalar(m))][6];
		jt=new JTable(data,heads);
                
		jsp=new JScrollPane(jt);
		jsp.setBounds(50,100,900,375);
                
                l1=new Label("SALE HISTORY");
                l1.setBounds(50, 40, 500, 60);
                l1.setFont(new Font("Algerian",Font.BOLD|Font.ITALIC,40));
                add(l1);
		add(jsp);
		
		try
		{
			String q="select * from sale";
			ResultSet rs=DBLayer.getResult(q);
			int i=0;
			while(rs.next())
			{
				System.out.println("filling row "+i);
				data[i][0]=rs.getString("saleid");
				data[i][1]=rs.getString("bookid");
				data[i][2]=rs.getString("soldto");
				data[i][3]=rs.getString("qty");
				data[i][4]=rs.getString("price");
                                data[i][5]=rs.getString("date_of_sale");
		
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
		String m="select count(*) from sale";
		data=new String[Integer.parseInt(DBLayer.getScalar(m))][6];
		
		String q="select * from sale ";
		ResultSet rs=DBLayer.getResult(q);
		
		int i=0;
		try
		{
			while(rs.next())
			{
				System.out.println(" row "+i);
				data[i][0]=rs.getString("sailid");
				data[i][1]=rs.getString("bookid");
				data[i][2]=rs.getString("soldto");
				data[i][3]=rs.getString("qty");
				data[i][4]=rs.getString("price");
                                data[i][5]=rs.getString("date_of_sale");
				
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


