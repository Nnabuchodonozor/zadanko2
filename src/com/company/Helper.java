package com.company;

import java.io.File;
import java.util.*;

public class Helper {

    public Helper() {
    }

    public void doStuff(){
        File f = new File("wordlist.10000");
        File g = new File("chvstup.txt");
        ArrayList<String>  dict = new ArrayList<>();
        String[] sentence = readSentence(g);;

        readDictionary(f,dict);
        String finalnaVeta = "";
        for(String s : sentence){
            System.out.println(s);
            List<Word> vsetkyVzdialenosti = new ArrayList<>();
            boolean bolo =false;
            for(String s2 : dict){
                if(s.equals(s2)){
                    bolo = true;
                    break;
                }else{
                    Word w = new Word(this.minEditDist(s,s2),s2);
                    vsetkyVzdialenosti.add(w);
                }
            }
            if(bolo==true){
                finalnaVeta = finalnaVeta + " " + s;
            }else{
                int minimum = 0;
                for(int i =0; i< vsetkyVzdialenosti.size();i++){
                    if(vsetkyVzdialenosti.get(i).getDistance()<vsetkyVzdialenosti.get(minimum).getDistance()){
                        minimum=i;
                    }
                }
                finalnaVeta = finalnaVeta + " " + vsetkyVzdialenosti.get(minimum).getWord();
            }

        }

        System.out.println(finalnaVeta);
    }

    public void readDictionary(File file, ArrayList<String> dictionary){
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine())
                dictionary.add(sc.nextLine());

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public String[] readSentence(File file){
        String[] sentence = null;
        try {
            Scanner sc = new Scanner(file);
            String s = sc.nextLine();
            s= s.toLowerCase();
            sentence = s.split(" ");

            }catch (Exception e){
            e.printStackTrace();
        }
        return sentence;
    }
    //                      s1 original string, s2 correct string
    public int minEditDist(String s1, String s2){
        List<Integer> changesMade = new ArrayList<>(); //0 delete, 1 replace, 2 insert, -1 leave as it is
        int[][] a = new int[s2.length()+1][s1.length()+1];

        for (int i = 0; i < s1.length()+1; i++){
            a[0][i]=i;
        }
        for (int i = 0; i < s2.length()+1; i++){
            a[i][0]=i;
        }


        for(int i = 1; i< s2.length()+1; i++)
        {

            for (int j = 1; j < s1.length()+1; j++){
                 //dynamic programming part
                    if(s1.charAt(j-1) == s2.charAt(i-1)){  //strings are equall, change nothing
                        a[i][j] = a[i-1][j-1];
                        changesMade.add(-1);
                    }else{  //strings are not equall, compare previous edit distances
                        int[] temp = {a[i][j-1], a[i-1][j-1], a[i-1][j]};
                        int toAdd= minimum(temp);
                        a[i][j]=temp[toAdd]+1;
                        changesMade.add(toAdd);
                    }

            }

        }


        return a[s2.length()][s1.length()];
    }

    private int minimum(int[] a){
        int min = 0;
        if(a[1] < a[min]){
            min = 1;
        }
        if(a[2] <a[min]){
            min = 2;
        }
        return min;
    }


}
