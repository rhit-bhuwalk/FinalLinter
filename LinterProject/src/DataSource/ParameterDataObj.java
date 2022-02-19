package DataSource;

public class ParameterDataObj {

	private int code;
	private String paramName;

	public ParameterDataObj(int accessCode, String paramName) {
		this.code = accessCode;
		this.paramName = paramName;
	}

	public int getCode() {
		return code;
	}

	public String getParamName() {
		return paramName;
	}

}
