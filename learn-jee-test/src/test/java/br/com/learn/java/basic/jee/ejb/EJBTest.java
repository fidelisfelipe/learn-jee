package br.com.learn.java.basic.jee.ejb;

import javax.ejb.Remove;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import br.com.learn.java.about.EJB;

/**
 * Performance
 * Segurança
 * Transações
 * Integridade
 *
 */
public class EJBTest implements EJB{

	/**
	 * Um bean com estado de sessão
	 * 
	 * Um bean com estado de sessão(stateful session bean) tem como caracteristicas manter o estado conversacional para um cliente especifico. 
	 * O estado é armazenado nos valores das variaveis de instancia do bean e nos interceptadores associados.
	 * Podemos definir um simples bean com estado de sessãousando a anotação 
	 * 
	 * Todos os metodos publicos do bean exemplificados podem ser invocados por um cliente.
	 * 
	 * Este estilo de declaração de bean é chamado de visualizacao sem interface. 
	 * Esse bean é apenas localmente acessível aos clientes empacotados no mesmo arquivo de deploy (como um war)
	 * 
	 * Se um bean precisa ser remotamente acessível, ele deve definir uma interface de negócio separada anotada com @Remote. 
	 */
	@Stateful
	class StatefulSessionBeans{
		
		/**
		 * Um cliente pode remover um bean com estado de sessãoapenas invocando o metodo "remover". 
		 * Chamando este método temos como resultado uma chamado do container ao metodo com a anotação @PreDestroy. 
		 * Remover um bean com estado de sessãosignifica que o estado da instancia específica para aquele cliente nao existe mais.
		 */
		@Remove
		public void remover() {}
		
	}
	
	/**
	 * Um bean sem estado de sessao
	 * 
	 * Um bean sem estado de sessão nao contem qualquer estado conversacional para um cliente especifico.
	 * Todas as instancias de um bean sem estado de sessão sao equivalentes, 
	 * portanto o container pode escolher delegar um metodo invocado por um cliente para qualquer instancia disponivel no pool do container. 
	 * Visto que os beans sem estado de sessão nao possuem qualquer estado, eles nao precisam ser passivados.
	 *
	 */
	@Stateless
	class StatelessSessionBeans{
		
	}
	
	/**
	 * Um bean de sessãoSingleton
	 *
	 * Um bean de sessãoSingleton é um componente que é instanciado uma única vez por aplicação e fornece um acesso bastante facilitado ao estado compartilhado. 
	 * Se o container for distribuído em múltiplas JVM, cada aplicação terá uma instância do Singleton para cada JVM. 
	 * Um bean de sessãoSingleton é explicitamente projetado para ser compartilhado e suportar concorrência.
	 *
	 * O Container é responsável por quando inicializar uma instância de um bean de sessãoSingleton. 
	 * Contudo, podemos opcionalmente marcar o bean para inicializar antes, através da anotação @Startup
	 * O Container agora inicializará todos esses Singletons em tempo de inicialização, executando o código que estiver anotado com @PostConstruct, 
	 * antes da aplicação tornar-se disponível e antes que qualquer solicitação do cliente seja atendida.
	 * 
	 * Podemos especificar uma inicialização explicita de beans de sessãoSingleton usando @DependsOn("MinhaSessionBeanPrimaria")
	 * O container assegura que o bean MinhaSessionBeanPrimaria é inicializado antes do bean SingletonSesionBean.
	 * Um bean Singleton suporta os métodos de callback do ciclo de vida "PostConstruct" e "PreDestroy". 
	 * Além disso, o Singleton também suporta acesso concorrente.
	 * 
	 * Por padrão, um bean Singleton é marcado para ter concorrência gerenciada pelo container, 
	 * mas opcionalmente pode ser marcado para ter concorrência gerenciada por bean.
	 * 
	 * Concorrência gerenciada por Container é baseada em metadados com bloqueio a nível de método onde cada método é associado com um bloqueio do tipo Read (compartilhado) ou Write (exclusivo).
	 * Um bloqueio de leitura (Read) permite chamadas simultâneas do método. Um bloqueio de leitura (Write) 
	 * aguarda o processamento de uma invocação completar antes de permitir que a próxima invocação prossiga.
	 * As anotações @Lock(LockType.READ) e @Lock(LockType.WRITE) são utilizadas para especificar o tipo de bloqueio. 
	 * Mas, por padrão, um bloqueio Write é associado com cada método do bean.
	 * Essas anotações podem ser especificadas na classe, um método de negócio da classe, ou ambos. 
	 * Um valor especificado no método sobrescreve um valor especificado na classe. 
	 * A concorrência gerenciada por bean requer que o desenvolvedor gerencie a concorrência utilizando primitivas de sincronização da linguagem Java como "synchronized" e "volatile".
	 *
	 */
	@Singleton
	class SingletonSesionBean{
		
	}
	
	
	
}
