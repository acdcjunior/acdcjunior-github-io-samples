package io.github.acdcjunior.jsfexample.web;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExampleManagedBeanTest {

    private ExampleManagedBean exampleManagedBean = new ExampleManagedBean();

    @Test
    public void getExampleProperty__should_have_a_default_value() {
        // given
        // initial values
        // when
        String examplePropertyInitialValue = exampleManagedBean.getExampleProperty();
        // then
        assertThat(examplePropertyInitialValue, is("Property Value"));
    }

    @Test
    public void exampleAction__should_change_exampleProperty() {
        // given
        // initial values
        String examplePropertyInitialValue = exampleManagedBean.getExampleProperty();
        // when
        exampleManagedBean.exampleAction();
        // then
        String examplePropertyAfterAction = exampleManagedBean.getExampleProperty();
        assertThat(examplePropertyAfterAction, is(examplePropertyInitialValue+"######0"));
    }

}