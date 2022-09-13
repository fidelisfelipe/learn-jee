package br.com.learn.java.medium.threads;

import br.com.learn.java.about.BillPughSingleton;
import br.com.learn.java.about.EagerInitialization;
import br.com.learn.java.about.LazyInitialization;
import br.com.learn.java.about.MutualExclusion;
import br.com.learn.java.about.RaceCondition;
import br.com.learn.java.about.Semaphore;
import br.com.learn.java.about.SingleThread;
import br.com.learn.java.about.ThreadSafe;

public class SingletonTest implements ThreadSafe, SingleThread, RaceCondition, Semaphore, MutualExclusion,
		EagerInitialization, LazyInitialization, BillPughSingleton {

}
/**
 * Eager Initialization
 * 
 * Este é o método mais simples de criar uma classe singleton. 
 * Neste, o objeto de classe é criado quando é carregado na memória pela JVM. 
 * Isso é feito atribuindo a referência de uma instância diretamente.
 * Ele pode ser usado quando o programa sempre usará instância dessa classe ou 
 * o custo de criação da instância não for muito grande em termos de recursos e tempo.
 * 
 */
class Singleton {
	private static Singleton instance = new Singleton();

	private Singleton() {
	}

	public static Singleton getInstance() {
		return instance;
	}
}

/**
 * Eager Initialization
 * 
 * Muito simples de implementar.
 * 
 * Pode levar ao desperdício de recursos. Porque a instância da classe é sempre criada, seja ela necessária ou não.
 * O tempo de CPU também é desperdiçado na criação da instância se não for necessário.
 * O tratamento de exceção não é possível.
 * 
 * Usando bloco estático: Esta também é uma subparte da inicialização Eager. 
 * A única diferença é que o objeto é criado em um bloco estático para que possamos ter acesso em sua criação, como tratamento de exceção. 
 * Desta forma também, o objeto é criado no momento do carregamento da classe.
 * Ele pode ser usado quando há uma chance de exceções na criação de objetos com inicialização antecipada.
 *
 */
class SingletonA {
	public static SingletonA instance;

	private SingletonA() {
	}

	static {
		instance = new SingletonA();
	}
}

/**
 * 
 * Lazy Inicialization
 * 
 * Prós:
 * Muito simples de implementar.
 * Não há necessidade de implementar o método getInstance(). A instância pode ser acessada diretamente.
 * Exceções podem ser tratadas em bloco estático.
 * Pode levar ao desperdício de recursos. Porque a instância da classe é sempre criada, seja ela necessária ou não.
 * O tempo de CPU também é desperdiçado na criação da instância se não for necessário.
 * 
 * Inicialização lenta: Neste método, o objeto é criado apenas se for necessário. 
 * Isso pode evitar o desperdício de recursos. 
 * É necessária uma implementação do método getInstance() que retorna a instância. 
 * Há uma verificação nula de que, se o objeto não for criado, crie, caso contrário, retorne criado anteriormente. 
 * Para garantir que a classe não possa ser instanciada de outra forma, o construtor é finalizado. 
 * À medida que o objeto é criado com um método, ele garante que o objeto não será criado até e a menos que seja necessário. 
 * A instância é mantida privada para que ninguém possa acessá-la diretamente.
 * Ele pode ser usado em um único ambiente de encadeamento porque vários encadeamentos podem quebrar a propriedade singleton, 
 * pois podem acessar o método de instância get simultaneamente e criar vários objetos.
 *
 */

class SingletonB {
	private static SingletonB instance;

	public static SingletonB getInstance() {
		if (instance == null) {
			instance = new SingletonB();
		}
		return instance;
	}
}

/**
 * 
 * Lazy initialization with Thread Safe
 * 
 * O objeto é criado apenas se for necessário. Pode superar o desperdício de recursos e tempo de CPU.
 * O tratamento de exceção também é possível no método.
 * Toda vez que uma condição de nulo deve ser verificada.
 * instância não pode ser acessada diretamente.
 * Em ambiente multithread, pode quebrar a propriedade singleton.
 * 
 * Thread Safe Singleton: Um singleton thread-safe é criado para que a propriedade singleton seja mantida mesmo em ambiente multithread. 
 * Para tornar um thread de classe singleton seguro, o método getInstance() é sincronizado para que vários threads não possam acessá-lo simultaneamente.
 *
 */
class SingletonC {
	private static SingletonC instance;

	private SingletonC() {
	}

	synchronized public static SingletonC getInstance() {
		if (instance == null) {
			instance = new SingletonC();
		}
		return instance;
	}
}

/**
 * Lazy Initialization with double check locking and Thread Safe
 * 
 * A inicialização lenta é possível.
 * Também é thread-safe.
 * O método getInstance() é sincronizado, portanto, causa um desempenho lento, pois vários threads não podem acessá-lo simultaneamente.
 * Inicialização lenta com bloqueio de verificação dupla: Neste mecanismo, superamos o problema de sobrecarga do código sincronizado. 
 * Neste método, getInstance não é sincronizado, mas o bloco que cria a instância é sincronizado para que o número mínimo de threads tenha que esperar e isso é apenas pela primeira vez.
 *
 */
class SingletonD {
	private static SingletonD instance;

	private SingletonD() {
	}

	public static SingletonD getInstance() {
		if (instance == null) {
			synchronized (SingletonD.class) {
				if (instance == null) {
					instance = new SingletonD();
				}
			}
		}
		return instance;
	}
}
/**
 * Bill Pugh impl
 * 
 * A inicialização lenta é possível.
 * Também é thread-safe.
 * A sobrecarga de desempenho é reduzida devido à palavra-chave sincronizada.
 * Na primeira vez, pode afetar o desempenho.
 * Implementação de Bill Pugh Singleton: Antes do Java5, o modelo de memória tinha muitos problemas e os métodos acima causavam falhas em certos cenários em ambiente multithread. 
 * Então, Bill Pugh sugeriu um conceito de classes estáticas internas para usar em singleton.
 * 
 * Quando a classe singleton é carregada, a classe interna não é carregada e, portanto, não cria objeto ao carregar a classe. 
 * A classe interna é criada somente quando o método getInstance() é chamado. 
 * Portanto, pode parecer uma inicialização Eager, mas é uma inicialização Lazy.
 * Esta é a abordagem mais usada, pois não usa sincronização.
 */
class SingletonE {

	private SingletonE() {
	}

	private static class SingletonBillPugh {
		private static final SingletonE INSTANCE = new SingletonE();
	}

	public static SingletonE getInstance() {
		return SingletonBillPugh.INSTANCE;
	}
}

/**
 * Considerações
 * 
 * A inicialização Eager é fácil de implementar, mas pode causar desperdício de recursos e tempo de CPU. 
 * Use-o apenas se o custo de inicialização de uma classe for menor em termos de recursos ou seu programa sempre precisará da instância da classe.
 * 
 * Ao usar o bloco estático na inicialização do Eager, podemos fornecer tratamento de exceção e também controlar a instância.
 * 
 * Usando sincronizado, podemos criar uma classe singleton em ambiente multi-threading também, 
 * mas isso pode causar um desempenho lento, para que possamos usar o mecanismo de bloqueio de verificação dupla.
 * 
 * A implementação de Bill Pugh é a abordagem mais usada para classes singleton. A maioria dos desenvolvedores o prefere por causa de sua simplicidade e vantagens.
 **/
