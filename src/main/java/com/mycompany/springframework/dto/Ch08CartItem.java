package com.mycompany.springframework.dto;

import lombok.Data;

@Data
public class Ch08CartItem {
	private Ch08Product product;  // 이미 있는 데이터는 또 건드리지말고 객체 불러오기
	private int amount;
	
}
