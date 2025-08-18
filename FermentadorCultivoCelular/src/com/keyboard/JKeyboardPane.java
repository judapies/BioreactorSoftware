package com.keyboard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class JKeyboardPane extends JPanel{
	private static final long serialVersionUID = 01;
	JTextField txt;
	String teclas[]={"1","2","3","4","5","6","7","8","9","0","Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Ñ","Z","X","C","V","B","N","M","."};
	ArrayList<JButton> botones=new ArrayList<JButton>();
	JPanel pletras,pespacio,pclear;
	
	public JKeyboardPane(JTextField t){
		
		txt=t;
		pletras=new JPanel();
		setLayout(new BorderLayout());
		pletras.setLayout(new GridLayout(4,10,0,0));
		
		ActionListener accion=new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b=(JButton)e.getSource();
				if(!b.getText().equalsIgnoreCase(" ")){
					txt.setText(""+txt.getText()+b.getText());
				}else{
					txt.setText(txt.getText()+" ");
				}
                                if("CLEAR".equals(b.getText()))
                                {
                                    txt.setText(null);
                                }
			}
			
		};
		
		for(int i=0;i<38;i++){
			if(teclas[i].equalsIgnoreCase("Z")){
				JLabel l=new JLabel();
				pletras.add(l);
			}
			JButton b=new JButton(teclas[i]);
			b.addActionListener(accion);
                        b.setPreferredSize(new Dimension(50, 50));
			pletras.add(b);
			botones.add(b);
		}
		
		pespacio=new JPanel(new GridLayout(1,3));
		JButton bespacio=new JButton(" ");
		bespacio.addActionListener(accion);
		pespacio.add(new JLabel());
		pespacio.add(bespacio);
		pespacio.add(new JLabel());
		add(pletras);
		add(pespacio,BorderLayout.SOUTH);
                
                pclear=new JPanel(new GridLayout(1,1));
		JButton bclear=new JButton("CLEAR");
		bclear.addActionListener(accion);
		pclear.add(new JLabel());
		pclear.add(bclear);
		pclear.add(new JLabel());
		pletras.add(bclear);
                //add(pletras);
		//add(pclear,BorderLayout.NORTH);
		
	}

}
