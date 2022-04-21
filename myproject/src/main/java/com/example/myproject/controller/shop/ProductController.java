package com.example.myproject.controller.shop;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.myproject.model.shop.ProductDAO;
import com.example.myproject.model.shop.ProductDTO;

@Controller
@RequestMapping("/shop/product/*")	//공통적인 url pattern
public class ProductController {
	@Inject
	ProductDAO productDao;
	
	@RequestMapping("write.do")	//세부적인 url pattern
	public String write() {
		return "shop/product_write";
	}
	
	@RequestMapping("insert.do")
	public String insert(ProductDTO dto, HttpServletRequest request) {
		String filename="-";
		if(!dto.getFile1().isEmpty()) { 
			filename=dto.getFile1().getOriginalFilename();	//첨부파일의 이름
			try {
				//application 객체(서버 전체, 모든 사용자가 공유하는 영역)
				ServletContext application=request.getSession().getServletContext();
				//배포 경로
				String path=application.getRealPath("/WEB-INF/views/images/");
				new File(path).mkdir(); //디렉토리 생성
				dto.getFile1().transferTo(new File(path+filename)); //임시 디렉토리에서 지정한 디렉토리로 복사
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setFilename(filename);
		productDao.insert(dto);
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("/shop/product_list");
		mav.addObject("list", productDao.list());
		return mav;
	}
	
	@RequestMapping("edit/{product_code}")	// PathVariable : url에 포함된 변수 
	public ModelAndView edit(@PathVariable("product_code") int product_code, ModelAndView mav) {
		mav.setViewName("/shop/product_edit");
		mav.addObject("dto", productDao.detail(product_code));
		return mav;
	}
	
	@RequestMapping("update.do")
	public String update(ProductDTO dto, HttpServletRequest request) {
		String filename="-";
		if(!dto.getFile1().isEmpty()) { //첨부파일이 있으면 
			filename=dto.getFile1().getOriginalFilename();
			try {
				ServletContext application=request.getSession().getServletContext();
				String path=application.getRealPath("/WEB-INF/views/images/");
				new File(path).mkdir();
				dto.getFile1().transferTo(new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setFilename(filename);
		}else { //첨부파일이 없으면 
			ProductDTO dto2=productDao.detail(dto.getProduct_code());
			dto.setFilename(dto2.getFilename());
		}
		productDao.update(dto);
		return "redirect:/shop/product/list.do";
	}
	@RequestMapping("delete.do")
	public String delete(int product_code, HttpServletRequest request) {
		String filename=productDao.file_info(product_code); //첨부파일의 이름
		if(filename!=null && !filename.equals("-")) { //첨부파일이 있으면 
			ServletContext application=request.getSession().getServletContext();
			String path=application.getRealPath("/WEB-INF/views/images/");
			File f=new File(path+filename);
			if(f.exists()) f.delete(); //파일이 존재하면 삭제 처리 
		}
		productDao.delete(product_code); //레코드 삭제 
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("detail/{product_code}")
	public ModelAndView detail(@PathVariable int product_code, ModelAndView mav) {
		mav.setViewName("/shop/product_detail");
		mav.addObject("dto", productDao.detail(product_code));
		return mav;
	}
}
