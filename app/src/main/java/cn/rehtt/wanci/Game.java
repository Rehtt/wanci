package cn.rehtt.wanci;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import net.sf.json.JSONObject;




public class Game {
    public  void main() {
        String url="https://wanci.rehtt.cn/wanciwang/getSomeWord.php";//单词接口
        String json=loadJSON(url);//获得15单词

        JSONObject js=JSONObject.fromObject(json);//转化为json对象

        String str="";

        int i=1;
        int blood=5;
        int robotBlood=5;


        while(blood!=0&&robotBlood!=0){
            //显示
            str=(String)js.get(String.valueOf(i));
            System.out.println("拿到的单词:"+str);
            i++;

            //计时
            SimpleDateFormat sdf= new   SimpleDateFormat("hhmmss");
            //String starDate1 = sdf.format(new Date());//获取游戏开始时间
            //System.out.println("开始时间:"+starDate1);

//			System.out.println(Integer.parseInt(starDate1));
            long starDate2=new Date().getTime();
            long end=starDate2;

            while(  (end -starDate2)<=5000       ){



                String record=recordResult();



                //	System.out.println("读到的单词:"+record);
                if(record.equals(str)){//对
                    robotBlood--;
                    break;
                    //动画
                    //词飞走
                }
                //错


                //	System.out.println("测试时间:"+new Date().getTime());
                end= new Date().getTime();
                //	System.out.println("开始时间:"+starDate2);
                //	System.out.println("结束时间:"+end);
                //	System.out.println("时间:"+(end-starDate2));
            }
            if(end-starDate2>10){
                blood--;
                //动画
                //词飞
            }

            System.out.println("玩家血量:"+blood+"，机器血量:"+robotBlood);

        }//
        System.out.println("玩家血量:"+blood+"，机器血量:"+robotBlood);

    }
    //

    //得到语音接口返回的字符串
    public static String recordResult(){
        return "word";
    }
    //从接口获得单词
    public static String loadJSON(String url){
        StringBuilder json=new StringBuilder();
        try {
            URL u=new URL(url);
            URLConnection uc=u.openConnection();
            BufferedReader br=new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine="";
            while((inputLine=br.readLine())!=null){
                json.append(inputLine);
            }
            br.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return json.toString();
    }
}
