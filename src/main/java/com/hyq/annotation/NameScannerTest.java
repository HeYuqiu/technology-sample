package com.hyq.annotation;

/**
 * @author Yuqiu.He
 * @date 2020-04-17
 */
@NameScanner
public class NameScannerTest {

    @NameScanner
    private String name;

    @NameScanner
    private int age;

    @NameScanner
    public String getName(){
        return this.name;
    }

    @NameScanner
    public void setName(String name){
        this.name = name;
    }

    public static void main(String[] args){
        System.out.println("--finished--");
    }
}