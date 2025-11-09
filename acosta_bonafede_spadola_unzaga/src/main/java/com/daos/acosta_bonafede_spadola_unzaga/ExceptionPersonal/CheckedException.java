package com.daos.acosta_bonafede_spadola_unzaga.ExceptionPersonal;

public class CheckedException extends Exception {
  
	private static final long serialVersionUID = 1L;
	private String atributo;

    public CheckedException() {
        super();
    }
    public CheckedException(String message) {
        super(message);
    }
    public CheckedException(String message, String atributo) {
        super(message);
        this.atributo = atributo;   
    }
    public CheckedException(String message, String atributo, Throwable cause) {
        super(message, cause);
        this.atributo = atributo;
    }

    public String getAtributo() {
        return atributo;
    }
    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }
}
