package spring.core.session07.tx.exception;


// 餘額數量不足例外
public class InsufficientAmount extends Exception{
	
	public InsufficientAmount (String errorMessage) {
		super(errorMessage);
	}

}
