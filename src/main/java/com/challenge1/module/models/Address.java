package com.challenge1.module.models;


public class Address {

    private final String partialStreet;
    private final String street;
    private final String city;
    private final String state;
    private final String postalCode;

    // Constructor, getters y setters
    private Address(Builder builder) {
        this.partialStreet = builder.partialStreet;
        this.street = builder.street;
        this.city = builder.city;
        this.state = builder.state;
        this.postalCode = builder.postalCode;
    }

    // Getters
    public String getPartialStreet() {
        return partialStreet;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public static class Builder {
        // Parámetros obligatorios
        private String partialStreet;
    
        // Parámetros opcionales
        private String street;
        private String city;
        private String state;
        private String postalCode;
    
        public Builder() {
        }

        public Builder partialStreet(String partialStreet) {
            this.partialStreet = partialStreet;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }
    
        public Builder state(String state) {
            this.state = state;
            return this;
        }
    
        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }
    
        public Address build() {
            return new Address(this);
        }
    }

}
