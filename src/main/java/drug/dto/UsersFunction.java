package drug.dto;

import java.util.List;

public class UsersFunction {

	private String username;

	private List<String> functions;

	public String getUsername() {
		return username!=null?username:"";
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getFunctions() {
		return functions;
	}

	public void setFunctions(List<String> functions) {
		this.functions = functions;
	}

	@Override
	public String toString() {
		return "UsersFunction [username=" + username + ", functions="
				+ functions + "]";
	}

	
	
}
