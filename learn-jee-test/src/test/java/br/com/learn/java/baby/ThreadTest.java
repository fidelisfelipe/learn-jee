package br.com.learn.java.baby;

import org.junit.jupiter.api.Test;

//StringBuffer
//StringBuilder
public class ThreadTest {
	@Test
	public void test() {
		System.out.println("Thread");
		new Thread(() -> System.out.println("Condição")).start();
	    System.out.println("Corrida");
	}
}
