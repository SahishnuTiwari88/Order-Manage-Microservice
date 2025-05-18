package com.wipro.SA20449357.microservices_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wipro.SA20449357.microservices_cart.entities.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	@Modifying
	@Query("DELETE From LineItem li where li.cart.cartId =:cartId AND li.itemId =:itemId")
	void deleteCartLineItem(@Param("cartId") int cartId,@Param("itemId") int itemId);
	
	Cart findByCustomerId(int customerId);
	
	Cart findCartIdByCustomerId(int customerId);

}
