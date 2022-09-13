package br.com.learn.java.medium.threads.semaphore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import br.com.learn.java.impl.semaphore.LoginQueueSemaphore;


public class LoginQueueSemaphoreTest {
	
	@Test
	public void givenLoginQueue_whenReachLimit_thenBlocked() {
		int slots = 10;
		ExecutorService executorService = Executors.newFixedThreadPool(slots);
		LoginQueueSemaphore loginQueue = new LoginQueueSemaphore(slots);
		IntStream.range(0, slots)
			.forEach(user -> executorService.execute(loginQueue::tryLogin));
		executorService.shutdown();

		assertEquals(0, loginQueue.availableSlots());
		assertFalse(loginQueue.tryLogin());
	}
	@Test
	public void givenLoginQueue_whenLogout_thenSlotsAvailable() {
	    int slots = 10;
	    ExecutorService executorService = Executors.newFixedThreadPool(slots);
	    LoginQueueSemaphore loginQueue = new LoginQueueSemaphore(slots);
	    IntStream.range(0, slots)
	      .forEach(user -> executorService.execute(loginQueue::tryLogin));
	    executorService.shutdown();
	    assertEquals(0, loginQueue.availableSlots());
	    loginQueue.logout();

	    assertTrue(loginQueue.availableSlots() > 0);
	    assertTrue(loginQueue.tryLogin());
	}
}
