package com.bowchan.tutorialbot.fishing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class fisher {
    public fisher(){
        try {

            File file = new File("fishinginformation","fisher");
            Scanner sc = new Scanner(file);

            while (sc.hasNext()){
                ArrayList<String> m = new ArrayList<String>();
                while (true){
                    String n =sc.next();
                    if(n.equals("end")){
                        break;
                    }else{
                        m.add(n);
                    }
                }
                fisherList.add(m);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //用戶,等級,現有經驗值,升等經驗值,金錢
    public ArrayList<ArrayList> fisherList = new ArrayList<ArrayList>();

    //回傳位置
    public int getList(String userTag) {
        for(int i=0;i<fisherList.size();i++){
            if(fisherList.get(i).get(0).equals(userTag)){
                return i;
            }
        }
        ArrayList userFisher = new ArrayList();
        userFisher.add(userTag);
        userFisher.add(1);
        userFisher.add(0);
        userFisher.add(0);
        userFisher.add(0);
        fisherList.add(userFisher);
        updateFisher();
        return fisherList.size()-1;
    }

    public  void  upExp(String name ,int Gyan) {
        getList(name);

        for (int i = 0; i <fisherList.size(); i++){
            if(fisherList.get(i).get(0).equals(name)){
                fisherList.get(i).set(2,String.valueOf(Integer.valueOf(String.valueOf(fisherList.get(i).get(2)))+Gyan));
                updateGrade(name);
                return;
            }
        }

    }

    public void updateGrade(String name){
        int n = getList(name);
        int x = Integer.valueOf(String.valueOf(fisherList.get(n).get(2)));
        int y = Integer.valueOf(String.valueOf(fisherList.get(n).get(3)));

        while (x >= y){
            x-=y;
            fisherList.get(n).set(1,Integer.valueOf(String.valueOf(fisherList.get(n).get(1)))+1);
            y = (int)Math.pow(Integer.valueOf(String.valueOf(fisherList.get(n).get(1))),2);
        }
        fisherList.get(n).set(2,x);
        fisherList.get(n).set(3,y);
        updateFisher();
    }

    //檔案更新
    public void updateFisher() {

        try {
            File file = new File("fishinginformation","fisher");
            FileWriter fileWriter = new FileWriter(file);
            for (ArrayList i : fisherList){
                for (Object j : i){
                    fileWriter.write(j+" ");
                }
                fileWriter.write("end\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
