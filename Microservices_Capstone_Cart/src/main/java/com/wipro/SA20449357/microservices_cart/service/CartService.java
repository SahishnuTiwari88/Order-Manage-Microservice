package com.wipro.SA20449357.microservices_cart.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.shopingservice.entities.CustomerCartMapping;
import com.wipro.SA20449357.microservices_cart.entities.Cart;
import com.wipro.SA20449357.microservices_cart.entities.LineItem;
import com.wipro.SA20449357.microservices_cart.repository.CartRepository;
import com.wipro.SA20449357.microservices_cart.repository.LineItemRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private LineItemRepository lineItemRepo;
	
	//add cart
	public Cart addCart(Cart cart) {
		return cartRepo.save(cart);
	}
	
	public Cart addCartId(int cartId) {
		Cart cart = new Cart();
		cart.setCartId(cartId);
		return cartRepo.save(cart);
	}
	
//	public LineItem addItemId(int itemId) {
//		LineItem lineItem = new LineItem();
//		lineItem.setItemId(itemId);
//		return lineItemRepo.save(lineItem);
//	}
	
	//delete cart working
	@Transactional 
	public void emptyCart(int id) {
		Cart cart = cartRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Cart id not found "+id));
		System.out.println("Cart data "+cart.getCartId());
		if(cart!=null) {
			List<LineItem> lineItems = cart.getLineItem();
			for(LineItem lineItem:lineItems) {
				lineItem.setCart(null);
				
				lineItemRepo.deleteById(lineItem.getItemId());
			}
			lineItems.clear();
			cartRepo.save(cart);
			cartRepo.deleteById(id);
		}
		
		
		}
		
	
	
	//update Cart working
	public Cart updateCart(Cart cart,int cartId) {
		Cart updateCart = cartRepo.findById(cartId).orElseThrow(()->new IllegalArgumentException("Cart id not available :"+cartId));
		updateCart.setLineItem(cart.getLineItem());
		return cartRepo.save(updateCart);
		
	}
	//search cart working
	public Cart searchCart(int cartId) {
		Cart cart1 = cartRepo.findById(cartId).orElseThrow(()->new IllegalArgumentException("Cart id not exist for search :"+cartId));
		return cart1;
	}
	
	//Now for LineItems
	
	//add items working
	
	public Cart addLineItem(LineItem item,int cartId) {
		Cart cart = cartRepo.findById(cartId).orElseThrow(()->new IllegalArgumentException("Provided cart id is not in line item "+cartId));
		//setting cart for line item
		item.setCart(cart);
		lineItemRepo.save(item);
		//adding lineitems item to the cart
		cart.getLineItem().add(item);
		//save and return updated cart
		return cartRepo.save(cart);
		
	}
	
	//delete lineitem working
	@Transactional
	public void deleteLineItem(int cartId,int itemId) {
		Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + cartId));

        if (cart != null) {
            List<LineItem> lineItems = cart.getLineItem();
            lineItems.removeIf(lineItem -> lineItem.getItemId() == itemId);
           lineItemRepo.deleteById(itemId);
           
            cartRepo.deleteById(cartId);
           

            System.out.println("Removed item " + itemId + " from cart " + cartId);
        } else {
            System.out.println("Cart not found for id: " + cartId);
        }
    }
	    
	    
	//update line item working
	public Cart updateLineItem(int cartId,int itemId,LineItem listItem) {
		Cart cart = cartRepo.findById(cartId).orElseThrow(()->new IllegalArgumentException("not valid cartId"));
		if(cart!=null) {
			//getting list of line items from cart
			List<LineItem> lineItems = cart.getLineItem();
			for(LineItem item : lineItems) {
				if(item.getItemId()==itemId) {
					LineItem existingItem = lineItemRepo.findById(itemId).orElseThrow(()->new IllegalArgumentException("Item id not exist "+itemId));
					
					existingItem.setProductId(listItem.getProductId());
					existingItem.setProductName(listItem.getProductName());
					existingItem.setQuantity(listItem.getQuantity());
					existingItem.setPrice(listItem.getPrice());
					lineItemRepo.save(existingItem);
					break;
				}
			}
			cart.setLineItem(lineItems);
			cartRepo.save(cart);
			
//			 {
//		            "itemId": 21,
//		            "productId": 4,
//		            "productName": "Iphone LED",
//		            "quantity": 1,   //send data in this form with cart id iand item id,cart id in postman
//		            "price": 160000.0
//		        }

	}
		return cart;
}
	
	
	
	
	//search line item working
	
	public Cart searchLineItem(int cartId,int itemId) {
		Cart cart = cartRepo.findById(cartId).orElseThrow(()->new IllegalArgumentException("Cart id not found :"+cartId));
		if(cart!=null) {
			 List<LineItem> lineItems = cart.getLineItem();
			for(LineItem lineitem : lineItems) {
				if(lineitem.getItemId()==itemId) {
					return cart;
				}
				
			}
			
		}
		return null;
	}
	
	
	//cart-shopping mapping 
	public void saveCustomerCartMaping(CustomerCartMapping mapping) {
		int customerId = mapping.getCustomerId();
		int cartId = mapping.getCartId();
		//to save customerId and cartId in cart microservice database
		Cart cart = cartRepo.findById(cartId).orElseThrow(()->new IllegalArgumentException("Cart id not found"+cartId));
		cart.setCustomerId(customerId);
		cartRepo.save(cart);
		
	}
	
	public Cart getCartIdForCustomer(int customerId) {
        return cartRepo.findCartIdByCustomerId(customerId);
       
    }
	
	
	//add line item to cart
	public Cart addLineItemToCart(int cartId, LineItem lineItem) {
        Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + cartId));

        if (cart != null) {
            lineItem.setCart(cart);
            lineItemRepo.save(lineItem);
            cart.addLineItem(lineItem);
            return cartRepo.save(cart);
        } else {
            throw new IllegalArgumentException("Cart not found with id: " + cartId);
        }
    }
}
