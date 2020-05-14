package com.exhibition.model.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Exhibition {

    private Long id;

    private String name;

    private String showroom;

    private String description;

    private User author;

    private BigDecimal price;

    private Date date;

    public static Exhibition.Builder builder() {
        return new Exhibition().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Exhibition.Builder id(Long id) {
            Exhibition.this.id = id;
            return this;
        }

        public Exhibition.Builder name(String name) {
            Exhibition.this.name = name;
            return this;
        }

        public Exhibition.Builder showroom(String showroom) {
            Exhibition.this.showroom = showroom;
            return this;
        }

        public Exhibition.Builder description(String description) {
            Exhibition.this.description = description;
            return this;
        }

        public Exhibition.Builder author(User author) {
            Exhibition.this.author = author;
            return this;
        }

        public Exhibition.Builder price(BigDecimal price) {
            Exhibition.this.price = price;
            return this;
        }

        public Exhibition.Builder date(Date date) {
            Exhibition.this.date = date;
            return this;
        }

        public Exhibition build() {
            return Exhibition.this;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowroom() {
        return showroom;
    }

    public void setShowroom(String showroom) {
        this.showroom = showroom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exhibition)) return false;
        Exhibition that = (Exhibition) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
