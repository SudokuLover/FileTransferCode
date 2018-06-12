import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class FileTransfer extends JFrame implements ActionListener{
	
	JFrame jf;
	JButton jb1,jb2;
	JTextField tf;
	JFileChooser jfc;
	Socket s;
	DataInputStream din;
	DataOutputStream dout,dout1;
	
	String s1=new String();
	String s2="";
	File f;
	
	
	FileTransfer(){
		jf=new JFrame("File Transfer");
		jf.setSize(400,400);
		
		Container c=jf.getContentPane();
		c.setBackground(Color.red);
		jf.setLayout(null);
		
		jb1=new JButton("Choose File");
		jb2=new JButton("Send");
		
		jb1.setBounds(30,50,100,50);
		jb2.setBounds(250,150,70,50);
		
		jf.add(jb1);
		jf.add(jb2);
		
		tf=new JTextField();
		tf.setEnabled(false);
		tf.setBounds(150, 50, 150, 50);
		jf.add(tf);
		
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		jfc=new JFileChooser();
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
	}
	
	public void fileTransfer(String s1){
		
		try{
			
			dout1.writeUTF(s1);
			dout.flush();
			s2=f.getAbsolutePath();
			FileReader fr= new FileReader(s2);
			
			BufferedReader br=new BufferedReader(fr);
			
			String s3="";
			do{
				s3=br.readLine();
				if(s3!=null)
				{
					dout.writeUTF(s3);
					dout.flush();
				}
				
			}while(s3!=null);
			
		}
		catch(Exception e){
			System.out.println(e+"error aagaya yr");

		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()==jb1)
		{
			int x=jfc.showOpenDialog(null);
			if(x==JFileChooser.APPROVE_OPTION)
			{
				f=jfc.getSelectedFile();
				String path=f.getPath();
				s1=f.getName();
				tf.setText(path+"\\"+s1);
			}
		}
		
		if(e.getSource()==jb2)
		{
			try{
				//s1=tf.getText();
				s=new Socket("localhost",10);
				//s= new Socket("54.123.143.45",10);
				dout=new DataOutputStream(s.getOutputStream());
				dout1=new DataOutputStream(s.getOutputStream());
				
			}
			catch(Exception e1){
				
				System.out.println(e1);
			}
			fileTransfer(s1);
		}
		
	}
	
	public static void  main(String ...s)
	{
		FileTransfer ft= new FileTransfer();
	}
	
}

