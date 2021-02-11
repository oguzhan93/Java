package com.company;

public class PerfectNumber {
    private int SayıDeğeri ;
    public void SetSayıDeğeri(int Sayı){
        this.SayıDeğeri= Sayı ;
    }
    public int GetSayıDeğeri(){
        return this.SayıDeğeri;
    }
    public void IsItPerfect(int Number){
        int Counter = 0 ;
        for(int i = 1 ; i<Number ; i++){
            if(Number%i == 0){
                Counter += i ;
            }
        }
        if(Number == Counter ){
            System.out.println( Number + " IS A Perfect Number!!!");
        }
        else
            System.out.println(Number + " Isn't A Perfect Number. ");
    }
}
