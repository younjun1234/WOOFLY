package com.kh.woofly.shop.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.woofly.board.model.vo.PageInfo;
import com.kh.woofly.common.Pagination;
import com.kh.woofly.shop.model.exception.ShopException;
import com.kh.woofly.shop.model.service.ShopService;
import com.kh.woofly.shop.model.vo.Product;
import com.kh.woofly.shop.model.vo.ProductAttm;
import com.kh.woofly.shop.model.vo.ProductCategory;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ShopController {
	
	@Autowired
	private ShopService sService;

	@GetMapping("/shopMain")
	public String movoToShopMain(Model model, @RequestParam(value = "page", defaultValue="1")int page,
								HttpServletRequest request) {
		// 쇼핑 상품리스트 전체 가져와서 뿌리기
		// 상품리스트, 상품에 따른 썸네일 가져오고, 상품의 카테고리 가져오고 // 페이징처리(무한스크롤)
		// 가져올 페이지, 한 페이지에 표현될 상품 개수			// 나중에 무한스크롤 완성시키자...
		int listCount = sService.getProductCount("P");
		PageInfo pi = Pagination.getPageInfo(page, listCount, 12);
		
		
		ArrayList<Product> pList = sService.selectProducts(pi, null);
		
		// topCategory용 - 선택된 카테고리! 자체를 선별 null 보내면 전체 카테고리 1개씩
		ArrayList<ProductCategory> sList = sService.selectedCategory(null);
		
		ArrayList<ProductCategory> cList = sService.selectCategory(9999);
		// 대분류 카테고리 리스트
		ArrayList<ProductCategory> bList = sService.selectCategory(null);
		// 썸네일의 첫번째만 가져오기....
		ArrayList<ProductAttm> aList = sService.selectProductAttm("thumb");
		
		model.addAttribute("pi", pi);
		model.addAttribute("pList", pList);
		model.addAttribute("cList", cList);
		model.addAttribute("aList", aList);
		model.addAttribute("bList", bList);
		model.addAttribute("sList", sList);
		model.addAttribute("loc", request.getRequestURI());
		
		
		return "shopMain";
	}
	
	@GetMapping("/shop/shopWrite")
	public String shopWrite(Model model) {
		// 상세카테고리의 전체 리스트
		ArrayList<ProductCategory> cList = sService.selectCategory(9999);
		
		// 대분류 카테고리 리스트
		ArrayList<ProductCategory> bList = sService.selectCategory(null);
		model.addAttribute("cList", cList);
		model.addAttribute("bList", bList);
		
		return "shopWrite";
	}
	
	@PostMapping("/shop/insertProduct")
	public String insertProduct(@ModelAttribute Product p,
								@RequestParam("thumbnailFile") ArrayList<MultipartFile> thumbFiles,
								@RequestParam("contentFile") ArrayList<MultipartFile> contentFiles) {
		if(p.getColorPicker() == null) {
			p.setColorPicker("N");
		}
		
		int result1 = sService.insertProduct(p);
		
		// 썸네일 리네임 과정
		ArrayList<ProductAttm> list = new ArrayList<ProductAttm>();
		for(int i = 0; i < thumbFiles.size(); i++) {
			MultipartFile upload = thumbFiles.get(i);
			if(!upload.getOriginalFilename().equals("")) {
				String[] returnArr = saveFile(upload);
				if(returnArr[1] != null) {
					ProductAttm t = new ProductAttm();
					t.setOriginalName(upload.getOriginalFilename());
					t.setRenameName(returnArr[1]);
					t.setAttmPath(returnArr[0]);
					t.setAttmRefNo(p.getProductId());
					t.setAttmLevel(1);
					
					list.add(t);
				}
			}
		}
		
		// 내용 이미지 리네임 과정
		for(int i = 0; i < contentFiles.size(); i++) {
			MultipartFile upload = contentFiles.get(i);
			if(!upload.getOriginalFilename().equals("")) {
				String[] returnArr = saveFile(upload);
				if(returnArr[1] != null) {
					ProductAttm c = new ProductAttm();
					c.setOriginalName(upload.getOriginalFilename());
					c.setRenameName(returnArr[1]);
					c.setAttmPath(returnArr[0]);
					c.setAttmRefNo(p.getProductId());
					
					// 썸네일(제목이미지)는 레벨이 1
					
					c.setAttmLevel(2);
					
					list.add(c);
				}
			}
		}
		
		int result2 = sService.insertAttm(list);
		
		if(result1 + result2 == list.size() + 2) {
			return "shopMain";
		} else {
			for(ProductAttm a : list) {
				deleteFile(a.getRenameName());
			}
			throw new ShopException("상품 등록을 실패하였습니다.");
		}
	}
	
	private String[] saveFile(MultipartFile upload) {
		
		String root = "C:\\woofly\\";
		String savePath = root + "\\shopFiles";
		
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		Date time = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int ranNum = (int)(Math.random()*100000);
		
		String originFileName = upload.getOriginalFilename();
		String renameFileName = sdf.format(time) + ranNum + originFileName.substring(originFileName.lastIndexOf("."));
		
		String renamePath = folder + "\\" + renameFileName;
		
		try {
			upload.transferTo(new File(renamePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] returnArr = new String[2];
		returnArr[0] = savePath;
		returnArr[1] = renameFileName;
		
		return returnArr;
	}
	
	
	private void deleteFile(String renameName) {
		String root = "C:\\woofly\\";
		String savePath = root + "\\shopFiles";
		
		File f = new File(savePath + "\\" + renameName);
		if(f.exists()) {
			f.delete();
		}
	}
	
	@GetMapping("/shop/selectDetail")
	public String selectDetailCategory(@RequestParam("productDetailNo") int cNo,
										@RequestParam(value="page", defaultValue="1") int page,
										HttpServletRequest request,
										Model model) {
		
		// 받아 온 카테고리에 해당하는 상품으로 교체
		
		int listCount = sService.getDetailCount(cNo);
		PageInfo pi = Pagination.getPageInfo(page, listCount, 12);
		ArrayList<Product> pList = sService.selectProducts(pi, cNo);
		
		// topCategory용 - 선택된 카테고리! 자체를 선별 null 보내면 전체 카테고리 1개씩
		ArrayList<ProductCategory> sList = sService.selectedCategory(cNo);
		
		ArrayList<ProductCategory> cList = sService.selectCategory(9999);
		// 대분류 카테고리 리스트 - 근데 이건 나중에 nav바 따로 빠지면 그쪽으로 보내놔도 될듯?
		ArrayList<ProductCategory> bList = sService.selectCategory(null);
		// 썸네일의 첫번째만 가져오기....
		ArrayList<ProductAttm> aList = sService.selectProductAttm("thumb");
		
		if(pList != null) {
			model.addAttribute("pi", pi);
			model.addAttribute("pList", pList);
			model.addAttribute("cList", cList);
			model.addAttribute("aList", aList);
			model.addAttribute("bList", bList);
			model.addAttribute("sList", sList);
			model.addAttribute("loc", request.getRequestURI());
			
			return "shopMain";
		} else {
			throw new ShopException("카테고리 선택에 실패하였습니다.");
		}
	}
	
}
