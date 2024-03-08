package org.example;

import java.util.*;
import java.util.regex.*;
import java.util.regex.Pattern;

public class Polinom{
    private String stringPoli;
    private HashMap<Integer, Integer> hashPoli;
    private static HashMap<Integer, Integer> rezPoli;

    public Polinom(String stringPoli){
        this.stringPoli = stringPoli;
        hashPoli = new HashMap<Integer,Integer>();
        rezPoli = new HashMap<Integer,Integer>();
    }
    public HashMap<Integer,Integer> getRezPoli(){ return rezPoli; }
    public String getStringPoli(){ return stringPoli; }
    public HashMap<Integer, Integer> getHashPoli() { return hashPoli; }

    public Map<Integer,Integer> getHash(String poli) { //despart polinomul
        int exp, coef;
        Pattern pattern = Pattern.compile("((?:((?:^|\\s*[+-]\\s*)\\d*)x(?:\\^(\\d+))?)|(?:[+-]\\s*\\d+))");
        Matcher matcher = pattern.matcher(poli);
        while (matcher.find()) {
            if (matcher.group(1).matches("[+-]\\s*\\d+")) {
                exp = 0;
                coef = Integer.parseInt(matcher.group(1).replace(" ", ""));
                hashPoli.put(exp, coef);
            }
            else {
                exp = (matcher.group(3) != "" && matcher.group(3) != null) ? Integer.parseInt(matcher.group(3)) : 1;
                try {
                    coef = (matcher.group(2) != "") ? Integer.parseInt(matcher.group(2).replace(" ", "")) : 1;
                }
                catch(NumberFormatException e){
                    if(matcher.group(2).startsWith("-"))
                        coef = -1;
                    else coef = 1;
                }
                hashPoli.put(exp, coef);
            }
        }
        return hashPoli;
    }

    public static HashMap<Integer,Integer> getAdd(Polinom p1,Polinom p2){
        for(HashMap.Entry<Integer,Integer> i : p1.hashPoli.entrySet()) //aduna coeficientii cu puteri egale
            for(Map.Entry<Integer,Integer> j : p2.hashPoli.entrySet())
                if(i.getKey().equals(j.getKey()))
                    rezPoli.put(i.getKey(),i.getValue()+j.getValue());

        for(HashMap.Entry<Integer,Integer> i : p1.hashPoli.entrySet()) //pune in hash ce o ramas din p1
            if(!rezPoli.containsKey(i.getKey()))
                rezPoli.put(i.getKey(),i.getValue());

        for(HashMap.Entry<Integer,Integer> i : p2.hashPoli.entrySet()) //pune in hash ce o ramas din p2
            if(!rezPoli.containsKey(i.getKey()))
                rezPoli.put(i.getKey(),i.getValue());

        return rezPoli;
    }
    public static HashMap<Integer,Integer> getSub(Polinom p1,Polinom p2){
        for(HashMap.Entry<Integer,Integer> i : p1.hashPoli.entrySet())
            for(HashMap.Entry<Integer,Integer> j : p2.hashPoli.entrySet())
                if(i.getKey().equals(j.getKey()))
                    rezPoli.put(i.getKey(),i.getValue()-j.getValue());

        for(HashMap.Entry<Integer,Integer> i : p1.hashPoli.entrySet())
            if(!rezPoli.containsKey(i.getKey()))
                rezPoli.put(i.getKey(),i.getValue());

        for(HashMap.Entry<Integer,Integer> i : p2.hashPoli.entrySet())
            if(!rezPoli.containsKey(i.getKey()))
                rezPoli.put(i.getKey(),-i.getValue());

        return rezPoli;
    }
    public static HashMap<Integer,Integer> getMulti(Polinom p1,Polinom p2){
        for(HashMap.Entry<Integer,Integer> i : p1.hashPoli.entrySet()) {
            for (HashMap.Entry<Integer, Integer> j : p2.hashPoli.entrySet())
                if(!rezPoli.containsKey(i.getKey()+j.getKey())) //daca nu avem cheia i*j o adaugam
                    rezPoli.put(i.getKey()+j.getKey(), i.getValue() * j.getValue());
                else rezPoli.put(i.getKey()+ j.getKey(),rezPoli.get(i.getKey()+ j.getKey())+i.getValue() * j.getValue()); //daca o avem folosim map.get() ca sa facem update la valoare
        }
        return rezPoli;
    }
    /*public int getOrdin(HashMap<Integer,Integer> map){
        int ordin=0;
        for(HashMap.Entry<Integer,Integer> i : map.entrySet())
            ordin=i.getKey();
        return ordin;
    }
    public HashMap<Integer,Integer> getDiv(Polinom p1,Polinom p2){
        if(getOrdin(p1.hashPoli)>getOrdin(p2.hashPoli)){
            for(HashMap.Entry<Integer,Integer> i : p1.hashPoli.entrySet()){
            }
        }
        return rezPoli;
    }*/
    public static HashMap<Integer,Integer> getDer(Polinom p1){
        for(HashMap.Entry<Integer,Integer> i : p1.hashPoli.entrySet())
            if(i.getKey()==1 && i.getValue()==-1)
                rezPoli.put(0,-1);
            else if(i.getKey()-1>=0)
                rezPoli.put(i.getKey()-1 ,i.getValue()*i.getKey());

        return rezPoli;
    }
    public static String getInt(Polinom p1){
        HashMap<Integer,String> integral = new HashMap<Integer,String>();
        String s="";

        for(HashMap.Entry<Integer,Integer> i : p1.hashPoli.entrySet())
            integral.put(i.getKey() + 1, i.getValue() + "/" + (i.getKey() + 1));

        for(HashMap.Entry<Integer,String> j : integral.entrySet()) {
            if(j.getKey()==1)
                s+=p1.getHashPoli().get(0) +"x";
            else if (!j.getValue().startsWith("-"))
                s += " + "+j.getValue()+"x^"+j.getKey();
            else s += j.getValue() + "x^" + j.getKey();
        }
        s+=" + C";
        return s;
    }

    public String toString(){
        String poliFinal="";

        for(HashMap.Entry<Integer,Integer> i : rezPoli.entrySet()){
           if(i.getKey()==0) {
               if (i.getValue() >= 1) poliFinal += "+"+i.getValue();
               else poliFinal+=i.getValue();
           }
           if(i.getKey()==1){
               if(i.getValue()==1) poliFinal += "+x";
               else if(i.getValue()==-1) poliFinal+="-x";
               else if(i.getValue()>1) poliFinal +="+"+i.getValue()+"x";
               else
                   poliFinal +=i.getValue()+"x";
           }
           if(i.getKey()>1){
               if(i.getValue()==1) poliFinal+="+x^"+i.getKey();
               else if(i.getValue()==-1) poliFinal+="-x^"+i.getKey();
               else if(i.getValue()>1) poliFinal+="+"+i.getValue()+"x^"+i.getKey();
               else poliFinal+=i.getValue()+"x^"+i.getKey();
           }
        }
        return poliFinal;
    }
}
