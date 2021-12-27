package com.endyary.perlibserver;

import org.assertj.core.api.AbstractAssert;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Asserts a result related to the SortOrder tests
 *
 * @author Nenad Dramicanin
 */
public class SortOrderAssert extends AbstractAssert<SortOrderAssert, List<Sort.Order>> {

    protected SortOrderAssert(List<Sort.Order> orders) {
        super(orders, SortOrderAssert.class);
    }

    protected static SortOrderAssert assertThat(List<Sort.Order> actual) {
        return new SortOrderAssert(actual);
    }

    protected SortOrderAssert equals(List<Sort.Order> expected) {
        isNotNull();
        if (actual.size() == expected.size()) {
            Sort.Order actualFirstOrder = actual.get(0);
            Sort.Order expectedFirstOrder = expected.get(0);
            // Two Sort.Order elements are equal only if their properties and directions are equal
            if (!(actualFirstOrder.getProperty().equals(expectedFirstOrder.getProperty()) &&
                    actualFirstOrder.getDirection().equals(expectedFirstOrder.getDirection()))) {
                failWithMessage("Actual order list not the same as the expected one!");
            }
        } else {
            failWithMessage("Actual order list size is incorrect, the size is %s but the expected is %s!",
                    actual.size(), expected.size());
        }

        return this;
    }
}
