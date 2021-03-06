package server;



import java.awt.BorderLayout;

import java.awt.Button;

import java.awt.Frame;

import java.awt.GridLayout;

import java.awt.List;

import java.awt.Panel;

import java.awt.TextField;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.OutputStream;

import java.io.OutputStreamWriter;

import java.net.Socket;

import java.util.Random;



class WaitingRoom extends Frame implements ActionListener,MouseListener {



	List list1, list2;

	TextField tf;

	Button b;

	Socket s;

	BufferedWriter bw;

	BufferedReader br;

	String id;

	Room room;

	WaitingRoom(String id) {

		super(id+"님 환영합니다");

		this.id = id;

		

		list1 = new List();

		list2 = new List();

		tf = new TextField();

		b = new Button("방만들기");

		b.addActionListener(this);

		list1.addMouseListener(this);

		Panel p1 = new Panel();

		p1.setLayout(new GridLayout(1, 2));

		Panel p2 = new Panel();

		p2.setLayout(new BorderLayout());



		p1.add(list1);

		p1.add(list2);



		p2.add(tf);

		p2.add(b, "East");



		add(p1);

		add(p2, "South");

		

	}

	void stop() throws IOException{

		s.close();

		

	}

	void initNet() throws Exception {

		s = new Socket("localhost", 8888);

		InputStream is = s.getInputStream();

		OutputStream os = s.getOutputStream();

		InputStreamReader isr = new InputStreamReader(is);

		br = new BufferedReader(isr);



		OutputStreamWriter osw = new OutputStreamWriter(os);

		bw = new BufferedWriter(osw);



		sendMsg("enter/" + id);

	}



	public void actionPerformed(ActionEvent e) {

		try {

			

			setVisible(false);

			dispose();

			room = new Room(id,tf.getText(),this);

			room.setBounds(200,200,400,300);

			room.setVisible(true);

			

			sendMsg("mkroom/"+tf.getText()); // mkroom/다방

			

		} catch (Exception e1) {

			e1.printStackTrace();

		}

	}



	void sendMsg(String msg) throws Exception {

		try{

		bw.write(msg + "\n");

		bw.flush();

		}

		catch(Exception ee){

			ee.printStackTrace();

		}

	}



	public void readMsg() {

		try {

			while (true) {

				String line = br.readLine();

				System.out.println(line);

				String[] array = line.split("/");



				switch (array[0]) {

				case "guestlist":

					list2.removeAll();

					int len = array.length;



					for (int i = 1; i < len; i++)

						list2.add(array[i]);



					break;

					

				case "roomlist" :

					list1.removeAll();

					len = array.length;

										 					

					for (int i = 1; i < len; i++)

						list1.add(array[i]);

					break;

					

				case "roomnum" :

					len = array.length;

					 

										

					for (int i = 1; i < len; i++)

						list1.replaceItem(list1.getItem(i-1)+"//"+array[i]+"명", i-1);

						

						

					break;

					

				case "guestlistRoom":

					room.list.removeAll();

					int len2 = array.length;

					for (int i = 1; i < len2; i++){

						room.list.add(array[i]);

					}

					break;

					

				case "out" :

					room.list.remove(array[1]);

					break;

					

				case "msg" :

					System.out.println("메시지 받음");

					room.ta.append("["+array[1]+"]"+array[2]+"\n");

					break;

				}

				

				

			}

		} catch (Exception e) {

			System.out.println("읽다가에러남~"+e.getMessage());

			e.printStackTrace();

		}

	}

	

	@Override

	public void mouseClicked(MouseEvent e) {

		if(e.getClickCount()==2){

			try {

				

				

				String roomname[] = list1.getSelectedItem().split("/");

				setVisible(false);

				dispose();

				room = new Room(id,roomname[0],this);

				room.setBounds(200,200,400,300);

				room.setVisible(true);

				

				sendMsg("roomjoin/"+id+"/"+roomname[0]);

				

			} catch (Exception e1) {

				// TODO Auto-generated catch block

				e1.printStackTrace();

			}

		}

		

	}



	@Override

	public void mouseEntered(MouseEvent e) {

		// TODO Auto-generated method stub

		

	}



	@Override

	public void mouseExited(MouseEvent e) {

		// TODO Auto-generated method stub

		

	}



	@Override

	public void mousePressed(MouseEvent e) {

		// TODO Auto-generated method stub

		

	}



	@Override

	public void mouseReleased(MouseEvent e) {

		// TODO Auto-generated method stub

		

	}



	public static void main(String args[]) throws Exception {

		Random rc = new Random();

		WaitingRoom client = new WaitingRoom(args[0]+rc.nextInt(10)*1);

		client.initNet();

		client.setBounds(200, 200, 400, 300);

		client.setVisible(true);

		client.readMsg();

	}

}
