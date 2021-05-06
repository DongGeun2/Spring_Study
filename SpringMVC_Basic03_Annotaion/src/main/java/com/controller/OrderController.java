package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.OrderCommand;

/*
 하나의 요청 주소로 2개 업무 처리
 /order/order.do
 
 화면 : GET
 처리 : POST
 
 */
@Controller
@RequestMapping("/order/order.do")
public class OrderController {
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String form() {
		return "order/OrderForm"; // view 주소를 리턴
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(OrderCommand ordercommand) {
		
		/*
		 OrderCommand ordercommand = new OrderCommand();
		 
		 
		 List<OrderItem> itemlist = new ArrayList<>();
		 list.add(new OrderItem(1,1,"A");
		 list.add(new OrderItem(2,2,"B");
		 list.add(new OrderItem(3,3,"C");
		 
		 ordercommand.setOrderItem(itemlist);
		 
		 */
		return "order/OrderCommited";
	}
	
	
	
	
	
}
