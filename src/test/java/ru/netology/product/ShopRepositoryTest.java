package ru.netology.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    Product products1 = new Product(67, "Крылья тарантула", 30);
    Product products2 = new Product(15, "Ноги русалки", 56);
    Product products3 = new Product(34, "Печень дракона", 40);
    Product products4 = new Product(55, "Флешка для мозга с полным курсом по java", 10_000_000);
    ShopRepository repo = new ShopRepository();

    @Test
    public void shouldRemoteById() {
        repo.add(products1);
        repo.add(products4);
        repo.add(products2);
        repo.add(products3);
        repo.remove(products2.id);

        Product[] expected = {products1, products4, products3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGetExceptionNotFound() {
        repo.add(products1);
        repo.add(products4);
        repo.add(products2);
        repo.add(products3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(70);
        });
    }

    @Test
    public void shouldAddNewProduct() {
        repo.add(products1);
        repo.add(products4);
        repo.add(products2);
        repo.add(products3);

        Product[] expected = {products1, products4, products2, products3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotAddProductWithSameId() {
        repo.add(products1);
        repo.add(products4);
        repo.add(products2);
        repo.add(products3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
           repo.add(products2);
        });
    }
}
