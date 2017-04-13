package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.dao.ProductItemDao;
import com.yunrer.entity.ProductItem;

/**
 * 子商品Service
 * @ClassName ProductItemService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("ProductItemService")
public class ProductItemService {
	
	@Resource
	private ProductItemDao itemDao;
	
	/**
	 * 添加子商品
	 * @Title: addProductItem 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int addProductItem(HttpServletRequest request){
		ProductItem productItem = setProductItem(request);
		return itemDao.addProductItem(productItem);
	}
	
	/**
	 * 更新子商品
	 * @Title: updateProductItem 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int updateProductItem(HttpServletRequest request){
		ProductItem productItem = setProductItem(request);
		return itemDao.updateProductItem(productItem);
	}
	
	/**
	 * 获取子商品列表
	 * @Title: getProductItemList 
	 * @Description:
	 * @param product_id
	 * @return List<ProductItem>         
	 * @throws
	 */
	public List<ProductItem> getProductItemList(int product_id){
		List<ProductItem> list  = itemDao.queryProductItemListById(product_id);
		return list;
	}
	
	/**
	 * 通过子商品部分信息获取子商品全部信息
	 * @Title: getProductItemByItem 
	 * @Description:
	 * @param req
	 * @return ProductItem         
	 * @throws
	 */
	public ProductItem getProductItemByItem(HttpServletRequest req){
		ProductItem item = setProductItem(req);
		return itemDao.queryProductItemByItem(item);
	}
	
	/**
	 * 通过id获取子商品
	 * @Title: getProductItem 
	 * @Description:
	 * @param id
	 * @return ProductItem         
	 * @throws
	 */
	public ProductItem getProductItem(int id){
		return itemDao.queryProductItemById(id);
		
	}
	/**
	 * 删除子商品
	 * @Title: deleteItem 
	 * @Description:
	 * @param itemId
	 * @return int         
	 * @throws
	 */
	public int deleteItem(int itemId){
		return itemDao.delProductItem(itemId);
	}
	
	/**
	 * 装配子商品
	 * @Title: setProductItem 
	 * @Description:
	 * @param request
	 * @return ProductItem         
	 * @throws
	 */
	public ProductItem setProductItem(HttpServletRequest request){
		ProductItem productItem = new ProductItem();
		if(request.getParameterMap()!=null){
			if(request.getParameter("product_id")!=null)
				productItem.setProductId(Integer.parseInt(request.getParameter("product_id")));
			if(request.getParameter("product_items_id")!=null)
				productItem.setProductItemsId(Long.parseLong(request.getParameter("product_items_id")));
			if(request.getParameter("product_version")!=null)
			productItem.setProductVersions(Integer.parseInt(request.getParameter("product_version")));
			if(request.getParameter("product_size")!=null)
			productItem.setProductSize(Integer.parseInt(request.getParameter("product_size")));
			if(request.getParameter("product_color")!=null)
			productItem.setProductColor(Integer.parseInt(request.getParameter("product_color")));
			if(request.getParameter("product_memory")!=null)
			productItem.setProductMemories(Integer.parseInt(request.getParameter("product_memory")));
			if(request.getParameter("price_cost")!=null)
			productItem.setProductCostPrice(Double.parseDouble(request.getParameter("price_cost")));
			if(request.getParameter("price_market")!=null)
			productItem.setProductMarketPrice(Double.parseDouble(request.getParameter("price_market")));
			if(request.getParameter("price_sale")!=null)
			productItem.setProductSalePrice(Double.parseDouble(request.getParameter("price_sale")));
			if(request.getParameter("stocks")!=null)
			productItem.setStocks(Integer.parseInt(request.getParameter("stocks")));
		}
		return productItem;
	}
	
}
