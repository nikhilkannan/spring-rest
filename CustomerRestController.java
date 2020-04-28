package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	//autowire the customwr servie
	
	@Autowired
	private CustomerService customerService;
	
	
	//add mapping for  get customer 
	@GetMapping("/customers")
	private List<Customer> getCustomers()
	{
		return customerService.getCustomers();
	}
	
	//add a mapping for get customer/customerid
	
	@GetMapping("/customers/{customerId}")
	private Customer getCustomer(@PathVariable int customerId)
	{
		Customer theCustomer=customerService.getCustomer(customerId);
		
		if(theCustomer==null)
		{
			throw new CustomerNotFoundException("customer Id not found "+customerId);
		}
		
		return theCustomer;
	}
	
	//add mapping for POST/customers adda new customer
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer)
	{
		//also just in case pass an id in jason....set id to 0
				//this id force a save of new item instead of update
				
		
		theCustomer.setId(0);
		
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	//add mapping for put/customers to update an existing customer
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer)
	{
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	//add mapping for delete/customers to delete an existing customer
	
		@DeleteMapping("/customers/{customerId}")
		public String deleteCustomer(@PathVariable int customerId)
		{
			Customer tempCustomer=customerService.getCustomer(customerId);
			if(tempCustomer==null)
			{
				throw new CustomerNotFoundException( "customer with id"+customerId +"doesnt exist");
			}
			
			customerService.deleteCustomer(customerId);
			
			
			return "Customer with id="+customerId+"deleted successfully";
		}
	
	
	
	
	
	

}
