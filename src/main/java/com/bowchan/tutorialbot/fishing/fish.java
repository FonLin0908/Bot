package com.bowchan.tutorialbot.fishing;

import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class fish {
    public static String plname = "";
    public fish(String name){
        plname = name;
        try {
            File file = new File("fishinginformation","fish");
            Scanner sc = new Scanner(file);

            fish = new ArrayList<>();
            boolean p;
            while (sc.hasNext()){
                p=true;
                String n = sc.next();
                for (int i = 0; i < fish.size(); i++){
                    if(n.equals(fish.get(i).get(0).get(0))){
                        fish.get(i).add(new ArrayList());
                        int x = fish.get(fish.size()-1).size()-1;
                        fish.get(i).get(x).add(n);
                        while (true){
                            n=sc.next();
                            if(n.equals("end")){
                                break;
                            }else{
                                fish.get(i).get(x).add(n);
                            }
                        }
                        p=false;
                        break;
                    }
                }
                if(p){
                    fish.add(new ArrayList());
                    fish.get(fish.size()-1).add(new ArrayList());

                    fish.get(fish.size()-1).get(fish.get(fish.size()-1).size()-1).add(n);
                    while (true){
                        n=sc.next();
                        if(n.equals("end")){
                            break;
                        }else{
                            fish.get(fish.size()-1).get(fish.get(fish.size()-1).size()-1).add(n);
                        }
                    }
                }
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    //稀有度,魚名,提供經驗值,價錢
    public static ArrayList<ArrayList<ArrayList>> fish = new ArrayList<>();

    //隨機釣魚 返回 稀有度,魚名,經驗,機率,文件
    public ArrayList fishingAct(){
        fisher Fisher = new fisher();
        String filename[] = {"转手指妖梦.gif","转手指大小姐.gif","转手指二階堂真紅.gif","六花转OSU.gif"};
        double n = (Math.random()*1000)%100;
        ArrayList arrayList = new ArrayList();
        int Gya = 0;

        if(n>=100-Math.pow(2.0,0.0)){
            int m =(int)(Math.random()*1000)%fish.get(0).size();
            arrayList.add(fish.get(0).get(m).get(0));
            arrayList.add(fish.get(0).get(m).get(1));
            arrayList.add(fish.get(0).get(m).get(2));
            arrayList.add(Math.pow(2.0,0.0)/fish.get(0).size());
            File file = new File("picture","61ee26df56cab2yl.gif");
            arrayList.add(file);

            Gya = (Integer.valueOf(String.valueOf(fish.get(0).get(m).get(2))));

        }else if(n>=100-(int)Math.pow(2.0,2.0)){
            int m =(int)(Math.random()*1000)%fish.get(1).size();
            arrayList.add(fish.get(1).get(m).get(0));
            arrayList.add(fish.get(1).get(m).get(1));
            arrayList.add(fish.get(1).get(m).get(2));
            arrayList.add(Math.pow(2.0,2.0)/fish.get(1).size());
            File file = new File("picture",filename[m]);
            arrayList.add(file);
            Gya = (Integer.valueOf(String.valueOf(fish.get(1).get(m).get(2))));

        }else if(n>=100-(int)Math.pow(2.0,3.0)){
            int m =(int)(Math.random()*1000)%fish.get(2).size();
            arrayList.add(fish.get(2).get(m).get(0));
            arrayList.add(fish.get(2).get(m).get(1));
            arrayList.add(fish.get(2).get(m).get(2));
            Gya = (Integer.valueOf(String.valueOf(fish.get(2).get(m).get(2))));

        }else if(n>=100-(int)Math.pow(2.0,4.0)){
            int m =(int)(Math.random()*1000)%fish.get(3).size();
            arrayList.add(fish.get(3).get(m).get(0));
            arrayList.add(fish.get(3).get(m).get(1));
            arrayList.add(fish.get(3).get(m).get(2));
            Gya = (Integer.valueOf(String.valueOf(fish.get(3).get(m).get(2))));

        }else {
            int m =(int)(Math.random()*1000)%fish.get(4).size();
            arrayList.add(fish.get(4).get(m).get(0));
            arrayList.add(fish.get(4).get(m).get(1));
            arrayList.add(fish.get(4).get(m).get(2));
            Gya = (Integer.valueOf(String.valueOf(fish.get(4).get(m).get(2))));
        }
        Fisher.upExp(plname,Gya);
        return arrayList;
    }

    //獲取機率
    public String probability(){
        String k="";
        double n=0.0;
        for (int i = 0;i < fish.size(); i++){
            if(i==0){
                k+=fish.get(i).get(0).get(0)+"  (機率:"+ Math.pow(2.0,Double.valueOf(i)) +"%) :\n";
            }else if(i==fish.size()-1){
                k+=fish.get(i).get(0).get(0)+"  (機率:"+ (100-n) +"%) :\n";
            }else{
                k+=fish.get(i).get(0).get(0)+"  (機率:"+ Math.pow(2.0,Double.valueOf(i+1)) +"%) :\n";
            }
            for (ArrayList j: fish.get(i)){

                if(i==0){
                    k+=j.get(0)+"   "+j.get(1)+" 機率: "+((Math.pow(2.0,Double.valueOf(i)))/Double.valueOf(fish.get(i).size()))+"%\n";
                }else if(i==fish.size()-1){
                    k+=j.get(0)+"   "+j.get(1)+" 機率: "+((100-n)/Double.valueOf(fish.get(i).size()))+"%\n";
                }else{
                    k+=j.get(0)+"   "+j.get(1)+" 機率: "+((Math.pow(2.0,Double.valueOf(i+1)))/Double.valueOf(fish.get(i).size()))+"%\n";
                }
            }
            if(i==0){
                n+=Math.pow(2.0,Double.valueOf(i));
            }else if(i==fish.size()-1){

            }else{
                n+=Math.pow(2.0,Double.valueOf(i+1));
            }

            k+="\n";
        }
        return k;
    }
}
