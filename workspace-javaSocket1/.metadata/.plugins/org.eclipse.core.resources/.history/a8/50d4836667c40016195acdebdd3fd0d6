package client.rcs;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;


public class MyClient //implements Runnable
{
    //接続先ホスト名(今回はローカルホスト)
    //例)　192.xxx.yyy.z --> "192.xxx.yyy.z"
    //public static final String HOST = "localhost";
    //接続先ポート番号(サーバー側で設定したものと同じもの)
    //public static final int PORT    = 10000;

    private JTextField tf;
    private JTextArea  ta;
    private JScrollPane sp;
    private JPanel pn;
    private JButton bt;

    private Socket sc;
    private BufferedReader br;
    private PrintWriter pw;

    public static void main(String[] args)
    {
        MyClient cl = new MyClient();
    }

    public MyClient()
    {
        //親クラスのコンストラクタを呼び出し
        /*super("Client Field");

        tf = new JTextField();
        ta = new JTextArea();
        sp = new JScrollPane(ta);
        pn = new JPanel();
        bt = new JButton("Send");

        //以下、GUIレイアウトとコンポーネントのイベント設定
        pn.add(bt);
        add(tf, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        add(pn, BorderLayout.SOUTH);

        bt.addActionListener(new SampleActionListener());
        addWindowListener(new SampleWindowListener());

        setSize(400,300);
        setVisible(true);
		*/



        //Threadクラスのインスタンスを作成・実行
        //Thread th = new Thread(this);
        //th.start();

    	System.out.println("--------Remote Control Client for Java--------");

    	Ini myIni = loadIni();

    	if(myIni.equals(null)){
    		System.out.println("ERROR loading settings.ini...");
    		return;
    	}


    	try{
    		String host = myIni.ipAddr;
    		int port = myIni.port;

    		System.out.println("SERVER:"+ host + ":"+port);

    		sc = new Socket(host,port);
            br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())));


            try{
                String str = myIni.command;
                System.out.println("COMMAND:"+str);

                pw.println(str);
                pw.flush();

                System.out.println("COMMAND SENT.");

            }
            catch(Exception e){
                br.close();
                pw.close();
                sc.close();

            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            System.out.println("CONNECTION ERROR!");
        }


    	/*
        try{
            String str = "Hello world.";
            pw.println(str);
            ta.append(str + "\n");
            pw.flush();
            //tf.setText("");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        */

    }


    // LOAD settings.ini
    private Ini loadIni(){
    	Ini myIni = new Ini();

    	try{
    		String dir = System.getProperty("user.dir");

    		  File file = new File(dir+"\\"+"settings.ini");


    		  BufferedReader br = new BufferedReader(new FileReader(file));

    		  String str = br.readLine();

    		  br.close();

    		  if(str.indexOf("//") >-1){
    			  str = str.substring(0, str.indexOf("//"));
    		  }

    		  String[] params = str.split(",",0);
    		  myIni.ipAddr = params[0];
    		  myIni.port = Integer.parseInt(params[1]);
    		  myIni.command = params[2];

    		  return myIni;


		}catch(FileNotFoundException e){
		  System.out.println(e);
		}catch(IOException e){
		  System.out.println(e);
		}

    	return null;

    }

    /*
    //Runメソッドの実装
    public void run()
    {
        try{
            sc = new Socket(HOST,PORT);
            br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())));

            while(true){
                try{
                    String str = br.readLine();
                    ta.append(str + "\n");
                }
                catch(Exception e){
                    br.close();
                    pw.close();
                    sc.close();
                    break;
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    */

    /*
    public class SampleActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try{
                String str = tf.getText();
                pw.println(str);
                ta.append(str + "\n");
                pw.flush();
                tf.setText("");
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    */

    /*
    class SampleWindowListener extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            System.exit(0);
        }
    }*/
}

