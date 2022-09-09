package model;

import static org.apache.commons.lang3.Validate.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Student {


	private Pc pc = null;

	private String userName = null;

	private HelpStatus helpStatus = HelpStatus.None;
	private LocalDateTime handUpTime = null;

	private WaitingManager<Pc> waitingManager = null;


	//--------コンストラクタ--------------
	public Student(Pc pc,WaitingManager<Pc> waitingManager) {
		notNull(pc);
		this.pc = pc;
		notNull(waitingManager);
		this.waitingManager=waitingManager;
	}

	//--------アクセッサ--------------
	public Pc getPc() {
		return pc;
	}

	public HelpStatus getHelpStatus() {
		return helpStatus;
	}

	public LocalDateTime getHandUpTime() {
		return handUpTime;
	}

	public String getUserName() {
		if (userName != null) {
			return userName;
		} else {
			return pc.getHostName().toString();
		}
	}

	//--------login処理--------------
	public void login(String name) {
		userName = name;
	}

	public void logout() {
		userName = null;
	}

	public boolean isLogin() {
		return (userName != null);
	}

	//--------hand処理--------------
	public void handUp() {
		helpStatus = HelpStatus.Troubled;
		handUpTime = LocalDateTime.now();
		waitingManager.regist(pc);
	}

	public void supported() {
		helpStatus = HelpStatus.Supporting;
		handUpTime = null;
		waitingManager.unregist(pc);
	}

	public void handDown() {
		helpStatus = HelpStatus.None;
		handUpTime = null;
		waitingManager.unregist(pc);
	}

	//--------待ち行列処理--------------
	public int getPriority() {
		return waitingManager.getPriority(pc);
	}

	public Duration getWaitingTime(LocalDateTime currentTime) {
		if (handUpTime == null) {
			return null;
		} else {
			return Duration.between(currentTime, handUpTime);
		}
	}

}
