package br.com.learn.java.impl.semaphore;

import java.util.concurrent.Semaphore;

public class LoginQueueSemaphore {
	private Semaphore semaphore;

	public LoginQueueSemaphore(int slotLimit) {
		semaphore = new Semaphore(slotLimit);
	}

	//modify visible for default
	public boolean tryLogin() {
		return semaphore.tryAcquire();
	}

	//modify visible for default
	public void logout() {
		semaphore.release();
	}

	//modify visible for default
	public int availableSlots() {
		return semaphore.availablePermits();
	}
}
