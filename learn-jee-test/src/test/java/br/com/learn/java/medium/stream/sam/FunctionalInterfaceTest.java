package br.com.learn.java.medium.stream.sam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import br.com.learn.java.about.SAM;


/**
 *  Single Abstract Method (SAM) with Strategy
 *
 */
public class FunctionalInterfaceTest implements SAM{
 
	interface Desconto {
		BigDecimal aplicar(BigDecimal quantia);
	}
	class DescontoPascoa implements Desconto {
		@Override
		public BigDecimal aplicar(final BigDecimal quantia) {
			return quantia.multiply(BigDecimal.valueOf(0.5));
		}
	}

	@Test
	public void testA() {
		Desconto descontoPascoa = new DescontoPascoa();
		BigDecimal valorDescontado = descontoPascoa.aplicar(BigDecimal.valueOf(100));
		assertTrue(BigDecimal.valueOf(50).doubleValue() == valorDescontado.doubleValue());
	}
	
	@Test
	public void testB() {
		Desconto descontoPascoa = new Desconto() {
		    @Override
		    public BigDecimal aplicar(final BigDecimal quantia) {
		        return quantia.multiply(BigDecimal.valueOf(0.5));
		    }
		};
		assertTrue(BigDecimal.valueOf(50).doubleValue() 
				== descontoPascoa.aplicar(BigDecimal.valueOf(100)).doubleValue());
	}
	
	@Test
	public void testC() {
		List<Desconto> list = Arrays.asList(
				quantia -> quantia.multiply(BigDecimal.valueOf(0.5))
		);
		assertTrue(BigDecimal.valueOf(50).doubleValue() 
				== list.stream().findFirst().get().aplicar(BigDecimal.valueOf(100)).doubleValue());
	}
	
	interface Descontos {
		BigDecimal aplicar(BigDecimal quantia);
		
	    static Desconto pascoa() {
	        return quantia -> quantia.multiply(BigDecimal.valueOf(0.5));
	    }
	}
	
	@Test
	public void testD() {
		assertTrue(BigDecimal.valueOf(50).doubleValue()
				== Descontos.pascoa().aplicar(BigDecimal.valueOf(100)).doubleValue());
	}
	
	public interface DescontoUnary extends UnaryOperator<BigDecimal> {
	    default DescontoUnary combine(DescontoUnary after) {
	        return value -> after.apply(this.apply(value));
	    }
	}
	
	@Test
	public void testE() {
		List<DescontoUnary> descontoList = Arrays.asList(
				quantia -> quantia.multiply(BigDecimal.valueOf(0.5))
		);
		DescontoUnary combinaDescontos = descontoList
				.stream()
				.reduce(v -> v, DescontoUnary::combine);
		
		assertTrue(BigDecimal.valueOf(50).doubleValue()
				== combinaDescontos.apply(BigDecimal.valueOf(100)).doubleValue());
		
	}
	
	@Test
	public void testF() {
		long init = System.currentTimeMillis();
		
		List<Integer> dados = Collections.synchronizedList(new ArrayList<>());
		
		int total = (int) Math.pow(2, 16);
		
		IntStream.range(0, total).parallel().map(i -> {
			dados.add(i);
			return i;
		}).forEachOrdered(i -> System.out.print(i + " "));
    
		for (Integer i : dados) { 
			System.out.print(i + " "); 
		}
		
		
		List<DescontoUnary> descontoList = Arrays.asList(
				quantia -> quantia.multiply(BigDecimal.valueOf(0.5))
		);
		DescontoUnary combinaDescontos = descontoList
				.stream()
				.reduce(v -> v, DescontoUnary::combine);
		
		assertTrue(BigDecimal.valueOf(50).doubleValue()
				== combinaDescontos.apply(BigDecimal.valueOf(100)).doubleValue());
		
		System.out.printf("tempo total: %s", System.currentTimeMillis() - init);
		
	}
	
	@Test
	public void testG() {
		System.out.println(
				IntStream.range(0, (int) Math.pow(2, 16))
				 .findAny().getAsInt());  

		System.out.println(
				IntStream.range(0, (int) Math.pow(2, 16)).parallel()
				 .findAny().getAsInt());
	}
	
	@Test
	public void testH() {
		System.out.println(
				Arrays.asList("J", "A", "V", "A")
				 .parallelStream()
				 .reduce("", (s1, s2) -> s1 + s2, (s3, s4) -> s3 + s4));
	}
	
}




