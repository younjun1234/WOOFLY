package com.kh.woofly.shop.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cart {

	private int cartId;
	private String mbId;
	private int quantity;
	private int productId;
	private String selected;
	private String pSize;
	private String color;
}
