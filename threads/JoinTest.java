package nachos.threads;

import nachos.machine.*;

public class JoinTest{
  // Place Join test code in the KThread class and invoke test methods
    // from KThread.selfTest().

    // Simple test for the situation where the child (finish)es before
    // the parent calls join on it.

    private static void joinTest1 () {
				KThread child1 = new KThread( new Runnable () {
					public void run() {
					    System.out.println("I (heart) Nachos!");
					}
				    });
				child1.setName("child1").fork();

				// We want the child to finish before we call join.  Although
				// our solutions to the problems cannot busy wait, our test
				// programs can!

				for (int i = 0; i < 5; i++) {
				    System.out.println ("busy...");
				    KThread.currentThread().yield();
				}

				child1.join();
				System.out.println("After joining, child1 should be finished.");
				System.out.println("is it? " + (child1.status == statusFinished));
				Lib.assertTrue((child1.status == statusFinished), " Expected child1 to be finished.");
    }

		private static void joinTest2(){
			KThread child2 = new KThread( new Runnable () {
				public void run() {
						System.out.println("test 2");
				}
					});
			child2.setName("child2").fork();

			child2.join();
			System.out.println("After joining, child2 should be finished.");
			System.out.println("is it? " + (child2.status == statusFinished));
			Lib.assertTrue((child2.status == statusFinished), " Expected child1 to be finished.");
		}

		//parent tries to call join on itself
		private static void joinTest3(){
			currentThread.join();
		}

		//multiple threads try to call join
		private static void joinTest4(){
			KThread child1 = new KThread( new Runnable () {
				public void run() {
						System.out.println("test 4");
				}
					});
			child1.setName("child1").fork();

			KThread child2 = new KThread( new Runnable () {
				public void run() {
						System.out.println("also test 4!");
				}
					});
			child2.setName("child2").fork();

			child1.join();
			child2.join();

			System.out.println("After joining, child1 should be finished. and child2 should not be");
			System.out.println("is it? " + (child1.status == statusFinished));
			System.out.println("is it? " + (child1.status == statusReady));
		}

		//verify if a parent calls join on a
		//child and the child is still executing, the parent waits
		private static void joinTest5(){
			System.out.println("Join Test 5");
			KThread child1 = new KThread( new Runnable () {
				public void run() {
					for(int i = 0; i < 5; i++){
						System.out.println("I'm the child times 5");
					}
				}
					});

			KThread child2 = new KThread( new Runnable () {
				public void run() {
						System.out.println("also test 4!");
						for(int i = 0; i < 5; i++){
							System.out.println("I'm the parent times 5");
						}
						child1.setName("child1").fork();
						child1.join();
						for(int i = 0; i < 5; i++){
							System.out.println("I'm the parent times 5");
						}
				}
					});
			child2.setName("child2").fork();
		}



		//if a parent calls join on a child and the child has finished executing,
		//the parent does not block
		private static void joinTest6(){
			System.out.println("Join Test 6");
			KThread child1 = new KThread( new Runnable () {
				public void run() {
					for(int i = 0; i < 5; i++){
						System.out.println("I'm the child times 5");
					}
				}
					});

			KThread child2 = new KThread( new Runnable () {
				public void run() {
						System.out.println("also test 4!");
						for(int i = 0; i < 5; i++){
							System.out.println("I'm the parent times 5");
						}
						child1.setName("child1").fork();
						child1.join();
						for(int i = 0; i < 5; i++){
							System.out.println("I'm the parent times 5");
						}
				}
					});
			child2.setName("child2").fork();
		}
}
