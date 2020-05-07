package practice;

public class WaitNotifyDemoFailure {

	public static void main(String a[]) {
		Account acct = new Account(1000, "anbu", "CTI`001");
		new Thread(new AccountRunable(acct, 5000, true),"Withdraw_Thread").start(); // withdraw thread

		new Thread(new AccountRunable(acct, 10000, false),"Deposit_Thread").start(); // deposit thread
	}

}

class AccountRunable implements Runnable {
	private Account acct;
	private double amount;
	private boolean isWithdrawal;

	AccountRunable(Account acct, double amount, boolean isWithdrawal) {
		this.acct = acct;
		this.amount = amount;
		this.isWithdrawal = isWithdrawal;
	}

	@Override
	public void run() {

		if (isWithdrawal) {
			boolean isWithdrawn = acct.withdraw(amount);
			System.out.println("isWithdrawn:" + isWithdrawn);
			if (!isWithdrawn) {
				synchronized (acct) {
					try {
						System.out.println(Thread.currentThread().getName()+" ** "+"Thread entered in waiting state due to not sufficient balance");
						acct.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+" ** "+"Waiting thread awaken...");

				}
			}
		} else {
			acct.balance += amount;
			System.out.println(Thread.currentThread().getName()+" ** "+"Amount " + amount + " credited to the account " + acct.acctNumber);
			acct.showBalance();
			synchronized (acct) {
				System.out.println(Thread.currentThread().getName()+" ** "+"Notifying the waiting thread to continue withdrawal process");
				acct.notify();
			}
		}

	}
}

class Account {
	public double balance;
	public String name;
	public String acctNumber;

	public Account(double balance, String name, String acctNumber) {
		super();
		this.balance = balance;
		this.name = name;
		this.acctNumber = acctNumber;
	}

	public boolean withdraw(double amount) {
		if (amount > balance) {
			System.out.println(Thread.currentThread().getName()+" ** "+"There is no sufficient amount to withdraw " + amount);
			showBalance();
			return false;
		}
		System.out.println(Thread.currentThread().getName()+" ** "+"Amount " + amount + " successfully withdrawn");
		balance = balance - amount;
		showBalance();
		return true;
	}

	public void showBalance() {
		System.out.println(Thread.currentThread().getName()+" ** "+"Balance Available:" + balance);
	}
}