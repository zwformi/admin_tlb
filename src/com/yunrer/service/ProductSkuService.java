package com.yunrer.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.support.json.JSONUtils;
import com.yunrer.dao.ProductSkuDao;
import com.yunrer.entity.ProductSku;

/**
 * 子商品Service
 * @ClassName ProductSkuService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("ProductSkuService")
public class ProductSkuService {
	
	@Resource
	private ProductSkuDao skuDao;
	
	/**
	 * 添加子商品
	 * @Title: addProductSku 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	@Transactional
	public int addProductSku(HttpServletRequest request){
		try{
		String skus = request.getParameter("skus");
		int product_id = Integer.parseInt((String) (request.getParameter("product_id")==null?0:request.getParameter("product_id")));
				JSONArray arr = new JSONArray(skus);
				for (Object object : arr) {
					JSONObject j = new JSONObject(object.toString());
					ProductSku sku = this.setProductSkuByAttr(j.put("product_id",product_id));
					skuDao.addProductSku(sku);
				}
		return 1;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	@Transactional
	public int updateProductSku2(HttpServletRequest request){
		try{
			

			int product_id = Integer.parseInt((request.getParameter("product_id")==null?
					"0":request.getParameter("product_id")));
			String skus = request.getParameter("skus");
			//删除现有的所有sku
			skuDao.delProductSkuByProduct(product_id);
			return  addProductSku(request);
			
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			return 0;

	}
	

	/**
	 * 更新子商品
	 * @Title: updateProductSku 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	@Transactional
	public int updateProductSku(HttpServletRequest request){
		try{
			String skus = request.getParameter("skus");
			int product_id = Integer.parseInt((request.getParameter("product_id")==null?
					"0":request.getParameter("product_id")));
					JSONArray arr = new JSONArray(skus);
					for (Object object : arr) {
						JSONObject j = new JSONObject(object.toString());
						ProductSku sku = this.setProductSkuByAttr(j.put("product_id",product_id));
						skuDao.updateProductSku(sku);
					}
			return 1;
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			return 0;

	}
	
	/**
	 * 获取子商品列表
	 * @Title: getProductSkuList 
	 * @Description:
	 * @param product_id
	 * @return List<ProductSku>         
	 * @throws
	 */
	public List<ProductSku> getProductSkuList(int product_id){
		List<ProductSku> list  = skuDao.queryProductSkuListById(product_id);
		return list;
	}
	

	
	/**
	 * 通过id获取子商品
	 * @Title: getProductSku 
	 * @Description:
	 * @param id
	 * @return ProductSku         
	 * @throws
	 */
	public ProductSku getProductSku(int id){
		return skuDao.queryProductSkuById(id);
		
	}
	/**
	 * 删除子商品
	 * @Title: deleteSku 
	 * @Description:
	 * @param skuId
	 * @return int         
	 * @throws
	 */
	public int deleteSku(int skuId){
		return skuDao.delProductSkuById(skuId);
	}
	
	/**
	 * 装配子商品
	 * @Title: setProductSku 
	 * @Description:
	 * @param request
	 * @return ProductSku         
	 * @throws
	 */
	public ProductSku setProductSku(HttpServletRequest request){
		ProductSku productSku = new ProductSku();
		if(request.getParameterMap()!=null){
			if(request.getParameter("product_id")!=null)
				productSku.setProductId(Integer.parseInt(request.getParameter("product_id")));
			if(request.getParameter("product_skus_id")!=null)
				productSku.setId(Integer.parseInt(request.getParameter("product_skus_id")));
			if(request.getParameter("attrName1")!=null)
				productSku.setAttrName1(request.getParameter("attrName1"));
			if(request.getParameter("attrName2")!=null)
				productSku.setAttrName2(request.getParameter("attrName2"));
			if(request.getParameter("attrName3")!=null)
				productSku.setAttrName3(request.getParameter("attrName3"));
			if(request.getParameter("attrName4")!=null)
				productSku.setAttrName4(request.getParameter("attrName4"));
			if(request.getParameter("attrName5")!=null)
				productSku.setAttrName5(request.getParameter("attrName5"));
			if(request.getParameter("attrName6")!=null)
				productSku.setAttrName6(request.getParameter("attrName6"));
			if(request.getParameter("attrName7")!=null)
				productSku.setAttrName7(request.getParameter("attrName7"));
			if(request.getParameter("attrName8")!=null)
				productSku.setAttrName8(request.getParameter("attrName8"));
			if(request.getParameter("attrVal1")!=null)
				productSku.setAttrVal1(request.getParameter("attrVal1"));
			if(request.getParameter("attrVal2")!=null)
				productSku.setAttrVal2(request.getParameter("attrVal2"));
			if(request.getParameter("attrVal3")!=null)
				productSku.setAttrVal3(request.getParameter("attrVal3"));
			if(request.getParameter("attrVal4")!=null)
				productSku.setAttrVal4(request.getParameter("attrVal4"));
			if(request.getParameter("attrVal5")!=null)
				productSku.setAttrVal5(request.getParameter("attrVal5"));
			if(request.getParameter("attrVal6")!=null)
				productSku.setAttrVal6(request.getParameter("attrVal6"));
			if(request.getParameter("attrVal7")!=null)
				productSku.setAttrVal7(request.getParameter("attrVal7"));
			if(request.getParameter("attrVal8")!=null)
				productSku.setAttrVal8(request.getParameter("attrVal8"));
			if(request.getParameter("price")!=null)
				productSku.setPrice(Double.parseDouble(request.getParameter("price")));
			if(request.getParameter("platformPrice")!=null)
				productSku.setPlatformPrice(Double.parseDouble(request.getParameter("platformPrice")));
			if(request.getParameter("originPrice")!=null)
				productSku.setOriginPrice(Double.parseDouble(request.getParameter("originPrice")));
			if(request.getParameter("stocks")!=null)
				productSku.setStocks(Integer.parseInt(request.getParameter("stocks")));
		}
		return productSku;
	}
	
	/**
	 * 装配子商品
	 * @Title: setProductSku 
	 * @Description:
	 * @param request
	 * @return ProductSku         
	 * @throws
	 */
	public ProductSku setProductSkuByAttr(JSONObject j){
			ProductSku productSku = new ProductSku();
//			Iterator<String> i = j.keys();
//			while(i.hasNext()){
				
				//String key = i.next();
				//String value = j.getString(key);
				if(j.has("id"))
				productSku.setId(Integer.parseInt(j.get("id").toString()));
				if(j.has("product_id"))
					productSku.setProductId(Integer.parseInt(j.get("product_id").toString()));
				if(j.has("product_skus_id"))
					productSku.setId(Integer.parseInt(j.get("product_skus_id").toString()));
				if(j.has("attrName1"))
					productSku.setAttrName1(j.getString("attrName1"));
				if(j.has("attrName2"))
					productSku.setAttrName2(j.getString("attrName2"));
				if(j.has("attrName3"))
					productSku.setAttrName3(j.getString("attrName3"));
				if(j.has("attrName4"))
					productSku.setAttrName4(j.getString("attrName4"));
				if(j.has("attrName5"))
					productSku.setAttrName5(j.getString("attrName5"));
				if(j.has("attrName6"))
					productSku.setAttrName6(j.getString("attrName6"));
				if(j.has("attrName7"))
					productSku.setAttrName7(j.getString("attrName7"));
				if(j.has("attrName8"))
					productSku.setAttrName8(j.getString("attrName8"));
				if(j.has("attrVal1"))
					productSku.setAttrVal1(j.getString("attrVal1"));
				if(j.has("attrVal2"))
					productSku.setAttrVal2(j.getString("attrVal2"));
				if(j.has("attrVal3"))
					productSku.setAttrVal3(j.getString("attrVal3"));
				if(j.has("attrVal4"))
					productSku.setAttrVal4(j.getString("attrVal4"));
				if(j.has("attrVal5"))
					productSku.setAttrVal5(j.getString("attrVal5"));
				if(j.has("attrVal6"))
					productSku.setAttrVal6(j.getString("attrVal6"));
				if(j.has("attrVal7"))
					productSku.setAttrVal7(j.getString("attrVal7"));
				if(j.has("attrVal8"))
					productSku.setAttrVal8(j.getString("attrVal8"));
				if(j.has("price"))
					productSku.setPrice(Double.parseDouble(j.getString("price")));
				if(j.has("platformPrice"))
					productSku.setPlatformPrice(Double.parseDouble(j.getString("platformPrice")));
				if(j.has("originPrice"))
					productSku.setOriginPrice(Double.parseDouble(j.getString("originPrice")));
				if(j.has("stocks"))
					productSku.setStocks(Integer.parseInt(j.getString("stocks")));
//				i.next();
//			}
			
		
		return productSku;
	}
}
