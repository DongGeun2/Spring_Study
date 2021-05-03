package DI_Annotation_01;

import org.springframework.beans.factory.annotation.Autowired;

public class MonitorViewer {
	private Recorder recorder;

	public Recorder getRecorder() {
		return recorder;
	}
	
	// setter 통해서 Recorder 객체의 주소를 자동으로 주입 
	// By Type 이 컨테이너 안에 있으면 ..
	
	// @Autowired(required = true) defalut >> 무조건 injection 하겠다.
	// @Autowired(required = false) >> 컨테이너안에 원하는 타입이 없으면 안한다.
	@Autowired(required = true)
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
	
	
	
}
