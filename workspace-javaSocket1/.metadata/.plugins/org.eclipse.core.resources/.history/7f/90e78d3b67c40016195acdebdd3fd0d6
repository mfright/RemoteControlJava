package client.local;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;


public class Client extends JFrame implements Runnable
{
    //接続先ホスト名(今回はローカルホスト)
    //例)　192.xxx.yyy.z --> "192.xxx.yyy.z"
    public static final String HOST = "localhost";
    //接続先ポート番号(サーバー側で設定したものと同じもの)
    public static final int PORT    = 10000;

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
        Client cl = new Client();
    }

    public Client()
    {
        //親クラスのコンストラクタを呼び出し
        super("Client Field");

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

        //Threadクラスのインスタンスを作成・実行
        Thread th = new Thread(this);
        th.start();
    }

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

    class SampleWindowListener extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            System.exit(0);
        }
    }
}

