package com.example.trung_thien_technology.repository;

import com.example.trung_thien_technology.model.ShoppingCart;
import com.example.trung_thien_technology.projection.IShoppingCartProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    @Query(value = "SELECT p.id AS id, p.name AS name, p.price AS price, sc.quantity AS quantity,p.quantity AS totalQuantity,i.url AS image \n" +
            "            FROM shopping_cart AS sc" +
            " INNER JOIN product AS p ON p.id = sc.product_id" +
            "        INNER JOIN images AS i on p.id = i.product_id\n" +
            " WHERE sc.is_delete=false AND sc.customer_id=:customer_id AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id)",
            countQuery =
                    " SELECT COUNT(*)" +
                            "            FROM shopping_cart AS sc" +
                            " INNER JOIN product AS p ON p.id = sc.product_id" +
                            "                    INNER JOIN images AS i on p.id = i.product_id" +
                            "            WHERE sc.is_delete=false AND sc.customer_id=:customer_id AND i.id IN (SELECT MIN(i.id) FROM images AS i GROUP BY i.product_id)",
            nativeQuery = true)
    List<IShoppingCartProjection> findShoppingCartByCustomer(@Param("customer_id") Integer customerId);

    @Query(value = "SELECT * " +
            "            FROM shopping_cart AS sc" +
            " WHERE sc.is_delete=false AND sc.customer_id=:customer_id",
            countQuery =
                    " SELECT COUNT(*)" +
                            "            FROM shopping_cart AS sc" +
                            "            WHERE sc.is_delete=false AND sc.customer_id=:customer_id ",
            nativeQuery = true)
    List<ShoppingCart> findShoppingCartByCustomers(@Param("customer_id") Integer customerId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE shopping_cart AS sc SET sc.quantity= :quantity WHERE sc.is_delete=false AND sc.customer_id=:customerID AND sc.product_id =:productId", nativeQuery = true)
    void updateShoppingCart(@Param("productId") Integer productId, @Param("quantity") Integer quantity, @Param("customerID") Integer customerID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM shopping_cart AS sc WHERE sc.is_delete=false AND sc.customer_id=:customer_id ", nativeQuery = true)
    void clearShoppingCartByCustomer(@Param("customer_id") Integer customerId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM shopping_cart AS sc WHERE sc.is_delete=false AND sc.customer_id=:customer_id AND sc.product_id =:product_id", nativeQuery = true)
    void deleteShoppingCartByProduct(@Param("customer_id") Integer customerId, @Param("product_id") Integer productId);
}
