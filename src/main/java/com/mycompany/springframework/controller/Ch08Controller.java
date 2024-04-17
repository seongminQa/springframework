package com.mycompany.springframework.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mycompany.springframework.dto.Ch08CartItem;
import com.mycompany.springframework.dto.Ch08Product;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch08")
public class Ch08Controller {
	@RequestMapping("/productList")
	public String productList(Model model) {
		// 상품 데이터 생성
		List<Ch08Product> productList = new ArrayList<>();
		for(int i=1; i<=5; i++) {
			productList.add(new Ch08Product(i, "상품"+i, i*10000));
		}
		
		// request 범위에 저장
		model.addAttribute("productList", productList);
		model.addAttribute("chNum" , "ch08");
		
		return "ch08/productList";
	}
	
	@RequestMapping("/detailView")
	public String detailView(int pno, Model model) {
		// 상품 상세 정보 얻기
		Ch08Product product = new Ch08Product(pno, "상품"+pno, pno*10000);
		
		// request 범위에 저장
		model.addAttribute("product", product);
		model.addAttribute("chNum", "ch08");
		
		return "ch08/detailView";
	}
	
	@RequestMapping("/cartView")
	public String cartView(HttpSession session, Model model) {
		model.addAttribute("chNum", "ch08");
		// 장바구니 보기 부터 실행할 수 있기 때문에 여기에도 넣는다.
		// 장바구니를 세션에서 가져오기
		List<Ch08CartItem> cart = (List<Ch08CartItem>) session.getAttribute("cart");
		// 가져온 장바구니가 없을 경우 새로 장바구니를 생성해서 session 범위에 저장
		if(cart == null) { // 가져왔는데 만약 없다면? 장바구니를 세션에 만들어야 함!
			cart = new ArrayList<Ch08CartItem>();
			session.setAttribute("cart", cart);
		}
		
		return "ch08/cartView";
	}
	
	@RequestMapping("/addCartItem")	// 우리는 장바구니에 넣자마자 장바구니를 보여주는 사이트를 만들자.
	public String addCartItem(int pno, int amount, HttpSession session) {
		// 자바스크립트 작성 후, 값이 넘어가는지 확인하기.
		/*log.info("pno: " + pno);
		log.info("amount: " + amount);*/
		
		/*// 상품 상세 정보 얻기
		Ch08Product product = new Ch08Product(pno, "상품"+pno, pno*10000);
		// 장바구니 아이템 생성
		Ch08CartItem cartItem = new Ch08CartItem();
		cartItem.setProduct(product);
		cartItem.setAmount(amount);
		
		
		// 장바구니를 세션에서 가져오기
		List<Ch08CartItem> cart = (List<Ch08CartItem>) session.getAttribute("cart");
		// 가져온 장바구니가 없을 경우 새로 장바구니를 생성해서 session 범위에 저장
		if(cart == null) { // 가져왔는데 만약 없다면? 장바구니를 세션에 만들어야 함!
			cart = new ArrayList<Ch08CartItem>();
			session.setAttribute("cart", cart);
		}
		// 장바구니에 장바구니 아이템을 추가
		cart.add(cartItem);*/
		
		
		// 장바구니를 세션에서 가져오기
		List<Ch08CartItem> cart = (List<Ch08CartItem>) session.getAttribute("cart");
		
		// 가져온 장바구니가 없을 경우 새로 장바구니를 생성해서 session 범위에 저장
		if(cart == null) { // 가져왔는데 만약 없다면? 장바구니를 세션에 만들어야 함!
			cart = new ArrayList<Ch08CartItem>();
			session.setAttribute("cart", cart);
		}
		
		// pno가 같은 아이템이 있으면 장바구니의 수량을 수정함
		boolean isAmountUpdated = false;
		for(Ch08CartItem cartItem : cart) {
			if(cartItem.getProduct().getPno() == pno) {
				cartItem.setAmount(cartItem.getAmount() + amount);
				isAmountUpdated = true;
			}
		}
		
		if(isAmountUpdated == false) {
			// 상품 상세 정보 얻기
			Ch08Product product = new Ch08Product(pno, "상품"+pno, pno*10000);
			// 장바구니 아이템 생성
			Ch08CartItem cartItem = new Ch08CartItem();
			cartItem.setProduct(product);
			cartItem.setAmount(amount);
			// 장바구니에 장바구니 아이템을 추가
			cart.add(cartItem);
		}
		
		
		
		
		return "redirect:/ch08/cartView";
	}
	
	// 업데이트 방법 1
	/*@RequestMapping(value="/updateCartItem", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String updateCartItem(int pno, int amount, HttpSession session) {
		// 카트안의 pno를 찾고 그 pno의 수량(amount)를 찾는다? 적용시킨다?
		
		// 장바구니를 session 범위에서 가져오기
		List<Ch08CartItem> cart = (List<Ch08CartItem>) session.getAttribute("cart");
		// pno와 같은 CartItem 찾기
		for(Ch08CartItem item : cart) {
			if(item.getProduct().getPno() == pno) {
				// CartItem의 amount를 수정
				item.setAmount(amount);
			}
		}
		
		// 수정 결과를 응답 생성
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		
		return jsonObject.toString();
	}*/
	
	// 업데이트 방법 2  // get을 사용한 경우에는 이렇게 가능하다. set을 사용한 경우는 못함!
	@RequestMapping(value="/updateCartItem", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String updateCartItem(int pno, int amount, @SessionAttribute("cart") List<Ch08CartItem> cart) {
		// 카트안의 pno를 찾고 그 pno의 수량(amount)를 찾는다? 적용시킨다?
		
		// pno와 같은 CartItem 찾기
		for(Ch08CartItem item : cart) {
			if(item.getProduct().getPno() == pno) {
				// CartItem의 amount를 수정
				item.setAmount(amount); // 세션의 정보를 set하는게 아니라 cart의 아이템을 set하는거라 쓸 수 있음
			}
		}
		
		// 수정 결과를 응답 생성
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		
		return jsonObject.toString();
	}
	
	// 제거 방법 1
	/*@RequestMapping("/removeCartItem")
	public String removeCartItem(int pno, HttpSession session) {
		// 장바구니를 세션에서 가져오기
		List<Ch08CartItem> cart = (List<Ch08CartItem>) session.getAttribute("cart");
		
		// pno와 같은 CartItem 찾기
		for(Ch08CartItem item : cart) {
			if(item.getProduct().getPno() == pno) {
				// CartItem 제거
				?????
			}
		}
		// 위의 방식처럼 for문을 돌리면 지웠는데, 길이는 남아있어서 에러가 난다.
		// 따라서 이터레이터를 사용해야한다.
		Iterator<Ch08CartItem> iterator = cart.iterator();
		while(iterator.hasNext()) {
			Ch08CartItem cartItem = iterator.next();
			if(cartItem.getProduct().getPno() == pno) {
				log.info(pno + "삭제완료");
				iterator.remove();
			}
		}
		return "redirect:/ch08/cartView";
	}*/
	
	// 제거 방법 2 // get을 사용한 경우에는 이렇게 가능하다. set을 사용한 경우는 못함!
	@RequestMapping("/removeCartItem")
	public String removeCartItem(int pno, @SessionAttribute("cart") List<Ch08CartItem> cart) {

		// 위의 방식처럼 for문을 돌리면 지웠는데, 길이는 남아있어서 에러가 난다.
		// 따라서 이터레이터를 사용해야한다.
		Iterator<Ch08CartItem> iterator = cart.iterator();
		while(iterator.hasNext()) {
			Ch08CartItem cartItem = iterator.next();
			if(cartItem.getProduct().getPno() == pno) {
				log.info(pno + "삭제완료");
				iterator.remove();
			}
		}
		return "redirect:/ch08/cartView";
	}
}
