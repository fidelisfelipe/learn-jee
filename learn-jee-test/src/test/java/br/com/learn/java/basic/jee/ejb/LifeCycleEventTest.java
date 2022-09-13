package br.com.learn.java.basic.jee.ejb;

import br.com.learn.java.about.EJB;

/**
 * Eventos do Ciclo de Vida
 * 
 * Métodos interceptadores do ciclo de vida podem ser definidos tanto para <b>beans de sessão</b> quanto para <b>beans de mensagens</b>.
 * 
 * As anotações @AroundConstruct, @PostConstruct, @PreDestroy, @PostActivate, e @PrePassivate 
 * são utilizados para definir métodos interceptadores para eventos do ciclo de vida dos beans.
 * 
 * A anotações @AroundConstruct pode ser definida apenas numa classe interceptadora, 
 * enquanto que todas as outras anotações podem ser definidos em uma classe interceptadora e/ou diretamente numa classe bean.
 * 
 */
public class LifeCycleEventTest implements EJB{

	
	
}
