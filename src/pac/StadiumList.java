package pac;
import pac.Stadium;

public class StadiumList {
	
	public Stadium list[];
	public int listLen;
	
	private int getListLen() {
		//获得场馆数量
		return 0;//仅为了消灭没有返回值的错误提示
	}
	
	public StadiumList(/*这里不能有任何参数，因为设计用于Javabean*/) {
		//构造函数
	}
	
	public void init(String sportType) {
		//获取某种特定的场馆信息
		this.listLen = this.getListLen();
		this.list = new Stadium[this.listLen];
		for (int i = 0; i < this.listLen; i++) {
			this.list[i] = new Stadium(/*参数自定*/);
			//TODO 初始化每一个Stadium的粗略信息
		}
	}

	public static void main(String[] args) {
		//测试代码
	}

}
