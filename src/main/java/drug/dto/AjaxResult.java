package drug.dto;

/**
 * ajax请求返回结果
 */
public class AjaxResult {
	
	private boolean state;   // 请求是否成功
	private Object value;        // 返回对象
	
	public boolean isState() {
		return state;
	}


	public void setState(boolean state) {
		this.state = state;
	}


	public Object getValue() {
		return value;
	}


	public void setValue(Object value) {
		this.value = value;
	}

	public AjaxResult(boolean state, Object result) {
		super();
		this.state = state;
		this.value = result;
	}
	
	@Override
	public String toString() {
		return "AjaxResult [state=" + state + ", value=" + value + "]";
	}
}
