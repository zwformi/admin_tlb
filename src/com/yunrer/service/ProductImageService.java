package com.yunrer.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.dao.ProductDao;
import com.yunrer.dao.ProductImageDao;
import com.yunrer.dao.ProductItemDao;
import com.yunrer.entity.Product;
import com.yunrer.entity.ProductImage;
/**
 * 商品图片service
 * @ClassName ProductImageService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("ProductImageService")
public class ProductImageService {
	
	@Resource
	private ProductImageDao productImageDao;
	
	@Resource
	private ProductDao productDao;
	
	@Resource
	private ProductItemDao productItemDao;
	
	
	private ServletContext servletContext; 
	
	/**
	 * 新增产品图片
	 */
	public int addProductImage(HttpServletRequest request) {
		int id = 0;
		try{
			ProductImage productImage = new ProductImage();
			String url = request.getAttribute("img_url").toString();
			productImage.setProduct_id(Integer.parseInt(request.getParameter("product_id")));    
			productImage.setImg_url(url); 
			String item = request.getParameter("product_items_id");
			productImage.setProduct_items_id(Integer.parseInt(request.getParameter("product_items_id")==""?"0":request.getParameter("product_items_id")));
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			Product product = productDao.queryProduct(product_id);

			id = productImageDao.addProductImage(productImage);
			String main_url = product.getImg_url();
			if(main_url==null||"".equals(main_url)||"/images/no_pic.png".equals(main_url)){
				productDao.updateProduct(product_id,"img_url",url);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return id;
	}
	
	/**
	 * 查询产品图片列表
	 */
	public List<ProductImage> queryProductImageList(HttpServletRequest request) {
		List<ProductImage> list = null;
		try{
			int product_id = Integer.parseInt(request.getParameter("product_id")==null?"0":request.getParameter("product_id"));
			int product_items_id = Integer.parseInt(request.getParameter("product_items_id")==null?"0":request.getParameter("product_items_id"));
			list = productImageDao.queryProductImageList(product_id,product_items_id);
			if(list==null){
				if(product_items_id==0){
					//无子商品的情况
					String url = productDao.queryProduct(product_id).getImg_url();
					ProductImage image = new ProductImage();
					image.setProduct_id(product_id);
					image.setProduct_items_id(0);
					image.setcoverImage(1);
					image.setId(0);
					image.setImg_url(url);
					list.add(image);
				}else{
					//无子商品的情况
					String url = productItemDao.queryProductItemById(product_items_id).getImg_url();
					ProductImage image = new ProductImage();
					image.setProduct_id(product_id);
					image.setProduct_items_id(product_items_id);
					image.setcoverImage(1);
					image.setId(0);
					image.setImg_url(url);
					list.add(image);
					
				}
			}} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 设置默认图片
	 */
	public int setDefaultImage(HttpServletRequest request) {
		int count = 0;
		try{
			int product_id= Integer.parseInt(request.getParameter("product_id"));
			int id= Integer.parseInt(request.getParameter("id"));
			String cover_old = request.getParameter("cover_old");
			int product_items_id = Integer.parseInt(request.getParameter("product_items_id")==""?"0":request.getParameter("product_items_id"));
			if(cover_old!=null){
				count = productImageDao.setAsDefaultImg(product_items_id,Integer.parseInt(cover_old),0);
			}
			count = productImageDao.setAsDefaultImg(product_items_id,id,1);
			ProductImage image = productImageDao.queryImage(id).get(0);
			//更新产品内默认图片路径
			if(product_items_id==0){ //主产品更新product表
				count = productDao.updateProduct(product_id, "img_url", image.getImg_url());	
			}else{ //子商品更新item表
				
				count = productItemDao.updateProductItemImg(product_items_id, image.getImg_url());
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 删除产品图片
	 */
	public int delProductImage(HttpServletRequest request) {
		int count = 0;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			int i = delImageFile(request);
			if(i!=0){
			count = productImageDao.delProductImage(product_id,id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	  public void setServletContext(ServletContext servletContext) {
	        this.servletContext = servletContext;
	    }
	/**
	 * 删除服务器中对应的图片
	 */
	public int delImageFile(HttpServletRequest request) {
		int count = 0;
		String path = request.getSession().getServletContext().getRealPath("/").trim();
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			List<ProductImage> list = productImageDao.queryImage(id);
			
			String url = path + list.get(0).getImg_url();
			File file = new File(url);
			 if (!file.exists()) {  // 无论如何都返回1
				 System.out.println(url+"文件不存在!");
			        return 1;
			        
			    } else {  
			        // 判断是否为文件  
			        if (file.isFile()) {  // 为文件时调用删除文件方法  
			            file.delete(); 
			            return 1;
			        }else{
			        	 System.out.println(url+"不是文件!");
			        	return 1;
			        }
			    }  
			 
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
}