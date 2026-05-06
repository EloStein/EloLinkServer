package de.spring.elolink_spring.test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EntityBuilder {

    private final String firstName;
    private final List<String> middleNames;
    private final String lastName;
    private final String email;
    private final String phone;

    private EntityBuilder(Builder builder)
    {
        this.firstName = builder.firstName;
        this.middleNames = builder.middleNames;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    public static FirstNameSetter builder() {
        return new Builder();

    }

    public String getFirstName() {
        return firstName;
    }

    public List<String> getMiddleNames() {
        return middleNames;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public interface FirstNameSetter {
        LastNameSetter firstName(String firstName);
    }

    public interface LastNameSetter {
        OptionalFieldSetter lastName(String lastName);
    }

    public interface OptionalFieldSetter {
        OptionalFieldSetter middleNames(List<String> middleNames);
        OptionalFieldSetter email(String email);
        OptionalFieldSetter phone(String phone);
        EntityBuilder build();
    }

    private static class Builder implements FirstNameSetter, LastNameSetter, OptionalFieldSetter {

        private String firstName;
        private List<String> middleNames = Collections.emptyList();
        private String lastName;
        private String email;
        private String phone;

        @Override
        public LastNameSetter firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        @Override
        public OptionalFieldSetter lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        @Override
        public OptionalFieldSetter middleNames(List<String> middleNames) {
            this.middleNames = middleNames;
            return this;

        }

        @Override
        public OptionalFieldSetter email(String email) {
            this.email = email;
            return this;
        }

        @Override
        public OptionalFieldSetter phone(String phone) {
            this.phone = phone;
            return this;
        }

        @Override
        public EntityBuilder build() {
            return new EntityBuilder(this);
        }
    }
}
