package com.herokuapp.trademateapi.demo.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {
    private final List<Violation> violations = new ArrayList<>();

    public ValidationErrorResponse() {}

    public List<Violation> getViolations() {
        return violations;
    }
}
