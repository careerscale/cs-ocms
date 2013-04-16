package in.careerscale.apps.ocms.bpm;

import org.springframework.beans.factory.annotation.Autowired;

public class ValidateOrderTask {
	@Autowired
	private BookOrder bookOrder;

	public void validate(String isbn, int amount) {
		bookOrder.setIsbn(isbn);
		bookOrder.setAmount(amount);
		if (bookOrder.getAmount() > 10) {
			bookOrder.setApproved(false);
		} else {
			bookOrder.setApproved(true);
		}
	}
}