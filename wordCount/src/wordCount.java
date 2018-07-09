import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class wordCount {

    public static void main(String[] args) {
        // write your code here
        BufferedReader br = null;
        String writeToFile="";
        String str = "";
        int lineNum=0;
        StringBuffer sb = new StringBuffer();
        try {
            br = new BufferedReader(new FileReader("D:/CodesJava/wordCount/src/input.txt"));
            while ((str = br.readLine()) != null) {
                sb.append(str);
                if (!str.matches("^[//s&&[^//n]]*$"))
                    lineNum++;

            }
            char[] charArray = sb.toString().toCharArray();
            int num=charArray.length;
            //int num = Process(sb.toString());
            writeToFile="characters:" + num+"\r\n";
            System.out.println("characters:" + num);


            Map<String, Integer> map = findEnglishNum(sb.toString());
            //输出到控制台
            //System.out.println("各个单词出现的频率为：");
            Iterator<String> iter = map.keySet().iterator();
            int wordCount = 0;
            while (iter.hasNext()) {
                String key = iter.next();
                Integer wordNum = map.get(key);
                wordCount++;
                //System.out.println(key + ":\t\t" + wordNum + "次\n-------------------");
            }
            System.out.println("words:" + wordCount);
            System.out.println("lines:" + lineNum);
            writeToFile+="words:" + wordCount+"\r\n"+"lines:" + lineNum+"\r\n";


            ByValueComparator bvc = new ByValueComparator(map);
            List<Map.Entry<String, Integer>> ll = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
            Collections.sort(ll, bvc);
            int i=0;

            for (Map.Entry<String, Integer> strWord : ll) {
                if(i<10) {

                    System.out.println(strWord.getKey() + ":" + strWord.getValue() + "\r\n");
                    writeToFile += strWord.getKey() + ":" + strWord.getValue() + "\r\n";
                }
                i++;
            }


            WriteToFile(writeToFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }


    }



    public static Map<String, Integer> findEnglishNum(String text) {
        //找出所有的单词
        String[] array = {".", " ", "?", "!"};
        for (int i = 0; i < array.length; i++) {
            text = text.replace(array[i], ",");
        }
        String[] textArray = text.split(",");

        //遍历 记录
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < textArray.length; i++) {
            String key = textArray[i];
            //转为小写
            String key_l = key.toLowerCase();
            if (!"".equals(key_l)) {
                Integer num = map.get(key_l);
                if (num == null || num == 0) {
                    map.put(key_l, 1);
                } else if (num > 0) {
                    map.put(key_l, num + 1);
                }
            }
        }
        return map;
    }


    public static void WriteToFile(String str){
        try  {
            BufferedWriter writer=new BufferedWriter(new FileWriter(new File("D:/CodesJava/wordCount/src/Result.txt")));
            //for (int i = 0; i < str.length; i++) {
            //    String s=String.valueOf(i+1)+" "+str[i]+"\r\n";
            writer.write(str);
            //}
            writer.close();
        }catch (Exception e){

        }

    }

}
