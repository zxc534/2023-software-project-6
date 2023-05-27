
@SuppressWarnings("serial")
public class InvalidValueException extends RuntimeException {
	String errorType;
	String errorPart;
	InvalidValueException(String type, String line){
		this.errorType = type;
		this.errorPart = line;
	}
	
	void print(String line) {
		System.err.println("Invalid " + this.errorType + " value : " + this.errorPart);
		System.err.println("Invalid line : " + line);
	}
}