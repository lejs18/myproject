package com.example.myproject.model.shop;

import java.util.List;

public interface ProductDAO {
	List<ProductDTO> list();

	ProductDTO detail(int product_code);

	void update(ProductDTO dto);

	void delete(int product_code);

	void insert(ProductDTO dto);

	String file_info(int product_code);
}
