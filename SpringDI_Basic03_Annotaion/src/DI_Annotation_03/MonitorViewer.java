package DI_Annotation_03;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MonitorViewer {
	private Recorder recorder;

	public Recorder getRecorder() {
		return recorder;
	}
	
	
	// @Autowired (같은 Type)   (by type)
	@Resource(name="xx") // (by name) 같은 타입의 객체가 여러개 있더라도 name 값으로 찾는다
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
	
}
