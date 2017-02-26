package drug.commons.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DataViolationException extends DataIntegrityViolationException {

	private static final long serialVersionUID = 1L;

	public DataViolationException(String msg) {
		super(msg);
	}

}
