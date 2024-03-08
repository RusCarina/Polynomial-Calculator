package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class OperationsTest {
    @Test
    public void addTest(){
        Polinom p1=new Polinom("2x^5+x^4-3x^2-x+8");
        p1.getHash(p1.getStringPoli());
        Polinom p2=new Polinom("x^5-2x^4+x^3-2x^2+x-4");
        p2.getHash(p2.getStringPoli());
        Polinom result=new Polinom("3x^5-x^4+x^3-5x^2+0x+4");
        result.getHash(result.getStringPoli());
        assertEquals(Polinom.getAdd(p1,p2),result.getHashPoli());
    }
    @Test
    public void subTest(){
        Polinom p1=new Polinom("2x^5+x^4-3x^2-x+8");
        p1.getHash(p1.getStringPoli());
        Polinom p2=new Polinom("x^5-2x^4+x^3-2x^2+x-4");
        p2.getHash(p2.getStringPoli());
        Polinom result=new Polinom("x^5+3x^4-x^3-x^2-2x+12");
        result.getHash(result.getStringPoli());
        assertEquals(Polinom.getSub(p1,p2),result.getHashPoli());
    }
    @Test
    public void multiTest(){
        Polinom p1=new Polinom("-3x^2-x+8");
        p1.getHash(p1.getStringPoli());
        Polinom p2=new Polinom("2x^2+x-4");
        p2.getHash(p2.getStringPoli());
        Polinom result=new Polinom("-6x^4-5x^3+27x^2+12x-32");
        result.getHash(result.getStringPoli());
        assertEquals(Polinom.getMulti(p1,p2),result.getHashPoli());
    }
    @Test
    public void derTest(){
        Polinom p=new Polinom("2x^5+x^4-3x^2-x+8");
        p.getHash(p.getStringPoli());
        Polinom result=new Polinom("10x^4+4x^3-6x-1");
        result.getHash(result.getStringPoli());
        assertEquals(Polinom.getDer(p),result.getHashPoli());
    }
    @Test
    public void intTest(){
        Polinom p=new Polinom("x^4-3x^2-x+8");
        Polinom result=new Polinom("8x-1/2x^2-3/3x^3 + 1/5x^5 + C");
        p.getHash(p.getStringPoli());
        assertEquals( Polinom.getInt(p),result.getStringPoli());
    }
}
