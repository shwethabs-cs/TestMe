/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.order.strategies.calculation.pdt.criteria;

import de.hybris.platform.europe1.enums.ProductDiscountGroup;
import de.hybris.platform.europe1.enums.UserDiscountGroup;
import de.hybris.platform.order.exceptions.CalculationException;

import java.util.List;


public interface DiscountValueInfoCriteria extends PDTCriteria
{
	ProductDiscountGroup getProductGroup();

	UserDiscountGroup getUserGroup();

	long getQuantity();

	String getPageId();

	String getPriceList();

	List<Long> getHierchyPKList();

	String getDiscountCode();

	boolean isBestPriceDiscount();

	@Override
	default Boolean isNet()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	default void validate()
	{
		try
		{
			if (getUser() == null && getUserGroup() == null)
			{
				throw new CalculationException("cannot match discounts without user and user group - at least one must be present");
			}
			if (getCurrency() == null)
			{
				throw new CalculationException("cannot match price without currency");
			}
			if (getDate() == null)
			{
				throw new CalculationException("cannot match price without date");
			}
		}
		catch (final CalculationException e)
		{
			throw new RuntimeException(e);
		}
	}
}
