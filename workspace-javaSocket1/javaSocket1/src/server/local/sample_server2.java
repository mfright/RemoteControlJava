package server.local;
import java.io.*;
import java.net.*; //ServerSocket Class defined
import java.util.*;



public class sample_server2
{
    //PORT Number
    public static final int PORT = 10000; //待ち受けポート番号

    public static void main(String[] args)
    {
    	loadCommand();

    	 sample_server2 sm = new sample_server2();

        try{

			ServerSocket ss = new ServerSocket(PORT);

            System.out.println("Waiting now ...");
            while(true){
                try{
                    //サーバー側ソケット作成
                    Socket sc = ss.accept();
                    System.out.println("Client connedted.");

                    Client cl = new Client(sc);
                    cl.start();
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                    break;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void loadCommand(){


    	try{
    		String dir = System.getProperty("user.dir");

    		  File file = new File(dir+"\\"+"commands.ini");

    		  BufferedReader br = new BufferedReader(new FileReader(file));


    		  String str = br.readLine();

    		  while(str != null){
    			  if(str.indexOf("//")==0 || str.length() < 3){

    			  }else if(str.indexOf("//")>0){
    				  str =str.substring(0,str.indexOf("//"));

    				  System.out.println("COMMAND:"+str+" loaded.");
        			  coms.commandList.add(str);
    			  }else{
    				  System.out.println("COMMAND:"+str+" loaded.");
        			  coms.commandList.add(str);
    			  }


    		    str = br.readLine();
    		  }


    		  br.close();

		}catch(FileNotFoundException e){
		  System.out.println(e);
		}catch(IOException e){
		  System.out.println(e);
		}

    }
}



//java.lang Package
class Client extends Thread
{
    private Socket sc;
    private BufferedReader br;
    private PrintWriter pw;

    public Client(Socket s)
    {
        sc = s;
    }
    public void run()
    {
        try{
            br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        while(true){
            try{
                String str = br.readLine();
                System.out.println("ExecuteNumber:"+str);
                runCommand(str);

                //クライアントからのメッセージの語尾＋ランダムな文字配列を出力Befferへ返す
                pw.println("Server : [" + str.charAt(str.length()-1) /* + rs.GetRandomString(rnd.nextInt(10)) */ + "] welcome.");
                pw.flush();
            }
            catch(Exception e){
                try{
                    br.close();
                    pw.close();
                    sc.close();
                    System.out.println("DISCONNECTED");
                    break;
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            }
        }
    }

    private void runCommand(String comNum){
    	int commandNum = Integer.parseInt(comNum);

    	if(coms.commandList.size() < commandNum){
    		System.out.println("ERROR: Ordered command number is bigger than registered commands.");
    		return;
    	}


    	String comStr = coms.commandList.get(commandNum-1);

    	try {
    		Runtime r = Runtime.getRuntime();
			Process process = r.exec(comStr);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("ERROR: Run command :"+comStr);
		}
    }
}
/*
class RamdomStrings{
    private final String stringchar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Random rnd = new Random();
    private StringBuffer sbf = new StringBuffer(15);

    public String GetRandomString(int cnt){
        for(int i=0; i<cnt; i++){
            int val = rnd.nextInt(stringchar.length());
            sbf.append(stringchar.charAt(val));
        }

        return sbf.toString();
    }
}*/
