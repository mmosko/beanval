package com.parc.cpss.beanval;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public interface PersonInterface {
	@NotNull
	public Date birthday();

	@Positive(message = "grow() return must be positive")
	public int grow(@Positive(message = "addedHeight must be positive") int addedHeight);
}
