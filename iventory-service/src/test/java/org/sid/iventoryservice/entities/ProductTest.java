package org.sid.iventoryservice.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void getId() {
        Product product = new Product();
        product.setId("1L");

        assertEquals(1L, product.getId());
    }

    @Test
    void getName() {
        Product product = new Product();
        product.setName("Laptop");

        assertEquals("Laptop", product.getName());
    }

    @Test
    void getPrice() {
        Product product = new Product();
        product.setPrice(1200.0);

        assertEquals(1200.0, product.getPrice());
    }

    @Test
    void getQuantity() {
        Product product = new Product();
        product.setQuantity(10);

        assertEquals(10, product.getQuantity());
    }

    @Test
    void setId() {
        Product product = new Product();
        product.setId("2L");

        assertNotNull(product.getId());
        assertEquals(2L, product.getId());
    }

    @Test
    void setName() {
        Product product = new Product();
        product.setName("Mouse");

        assertNotNull(product.getName());
        assertEquals("Mouse", product.getName());
    }

    @Test
    void setPrice() {
        Product product = new Product();
        product.setPrice(150.5);

        assertEquals(150.5, product.getPrice());
    }

    @Test
    void setQuantity() {
        Product product = new Product();
        product.setQuantity(5);

        assertEquals(5, product.getQuantity());
    }

    @Test
    void testToString() {
        Product product = new Product();
        product.setId("1L");
        product.setName("Keyboard");
        product.setPrice(300.0);
        product.setQuantity(15);

        String result = product.toString();

        assertNotNull(result);
        assertTrue(result.contains("Keyboard"));
        assertTrue(result.contains("300.0"));
    }

    @Test
    void builder() {
        Product product = Product.builder()
                .id("3L")
                .name("Screen")
                .price(2500.0)
                .quantity(7)
                .build();

        assertNotNull(product);
        assertEquals(3L, product.getId());
        assertEquals("Screen", product.getName());
        assertEquals(2500.0, product.getPrice());
        assertEquals(7, product.getQuantity());
    }
}
