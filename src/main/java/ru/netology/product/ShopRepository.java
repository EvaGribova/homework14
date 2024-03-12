package ru.netology.product;
public class ShopRepository {
    private Product[] products = new Product[0];

    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }
    public void add(Product product) {
        if (findById(product.id) != null) {
            throw new AlreadyExistsException(
              "Товар с ID " + product.id + " уже существует."
            );
        }
        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    public Product findById(int id) {
        for ( Product product : products ) {
            if (product.id == id)
                return product;
        }
        return null;
    }

    public void remove(int id) {
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        if (findById(id) == null) {
            throw new NotFoundException(
                    "Товар с ID " + id + " не найден."
            );
        }
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }
}
