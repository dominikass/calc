package pl.dominikas.calculatorApp;
//import pl.dominikas.calculatorApp.MainFrame;

public class Operation {
	
	 private String name;
	 private String code;
     
     public Operation(String n, String c) {
    	 this.name=n;
    	 this.code=c;
     }
     
     public String getCode() {
 		return code;
 	}

	public String getName() {
		return name;
	}

	public void setCode(String name) {
		this.code = name;
	}

	@Override
	public String toString() {
		return "" + name + "";
	}
	
	
     
}
