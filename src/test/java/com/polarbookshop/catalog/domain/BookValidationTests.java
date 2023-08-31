package com.polarbookshop.catalog.domain;

import jakarta.validation.Path;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.*;
public class BookValidationTests {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void whenAllFieldsSet_ValidationShouldSucceed() {
        var book = new Book("1","Thinking, Fast and Slow","Daniel Kahneman", 40);

        var validationResult = validator.validate(book);

        assertThat(validationResult).isEmpty();
    }

    @Test
    public void whenTitleIsEmpty_ValidationShouldFail() {
        var book = new Book("1","","Daniel Kahneman",40);

        var validationResult = validator.validate(book);

        assertThat(validationResult).hasSize(1);
        assertThat(validationResult).allMatch(c -> Objects.equals(c.getPropertyPath().toString(), "title"));
    }

}
