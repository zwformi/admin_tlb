package com.yunrer.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.OperateLog;
import com.yunrer.entity.Product;
import com.yunrer.entity.ProductAttrTemplate;
import com.yunrer.entity.ProductBrand;
import com.yunrer.entity.ProductParam;
import com.yunrer.entity.ProductType;
import com.yunrer.entity.UserCompany;

/**
 * 产品DAO
 * @ClassName ProductDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("ProductDao")
public class ProductDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	private static Logger log = Logger.getLogger(ProductDao.class);  
	/**
	 * 新增商品
	 * @Title: addProduct 
	 * @Description:
	 * @param product
	 * @return int[]         
	 * @throws
	 */
	public int[] addProduct(final Product product) {

		
		int id =0;
		int result =0;
		int[] list  = new int[2];

		try {
			final  String sql = "INSERT INTO `tbl_product` (`type_ids`, `brand_id`, `xh`, `pz`, `name`, `sub_title`, "
					+ "`count`, `price_old`, `price_new`, `content`, `sortid`, `is_yj`, `is_cx`, `is_ms`, `is_yun`,"
					+ " `is_wx`, `is_pc`, `is_red`, `delete_flag`, `ms_kl`, `ms_begintime`, `needInstall`, `limit`, "
					+ "`countryId`, `provinceId`, `cityId`, `firm`, `service`, `regionId`, `is_zcy`, `is_zcysj`, "
					+ "`special_code`,`parentId`,`sub_parentId`,`category_id`,`platform_price_area`,`zcy_price_area`)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			KeyHolder keyHolder = new GeneratedKeyHolder();   
			result = jdbcTemplate.update(new PreparedStatementCreator(){   
	            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {   
	                PreparedStatement ps =  conn.prepareStatement(sql, new String[]{"id"});//返回主键id   
	                ps.setString(1, product.getType_ids());
					ps.setInt(2, product.getBrand_id());
					ps.setString(3, product.getXh());
					ps.setString(4, product.getPz());
					ps.setString(5, product.getName());
					ps.setString(6,product.getSub_title());
//					ps.setString(7,product.getImg_url());
					ps.setInt(7, product.getCount());
					ps.setDouble(8, product.getPrice_old());
					ps.setDouble(9, product.getPrice_new());
					ps.setString(10, product.getContent());
					ps.setInt(11, product.getSortid());
					ps.setInt(12, product.getIs_yj());
					ps.setInt(13, product.getIs_cx());
					ps.setInt(14, product.getIs_ms());
					ps.setInt(15,product.getIs_yun());
					ps.setInt(16, product.getIs_wx());
					ps.setInt(17,product.getIs_pc());
					ps.setInt(18, product.getIs_red());
					ps.setInt(19,product.getDelete_flag());
					ps.setString(20,product.getMs_kl());
					ps.setDate(21, (Date) product.getMs_begintime());
					ps.setString(22, product.getNeedInstall());
					ps.setString(23, product.getLimit());
					ps.setString(24, product.getCountryId());
					ps.setString(25, product.getProvinceId());
					ps.setString(26, product.getCityId());
					ps.setString(27, product.getFirm());
					ps.setString(28, product.getService());
					ps.setString(29, product.getRegionId());
					ps.setInt(30, product.getIs_zcy()==null?0:product.getIs_zcy());
					ps.setInt(31, product.getIs_zcysj()==null?0:product.getIs_zcysj());
					ps.setInt(32, product.getSpecial_code()==null?0:product.getSpecial_code());
					ps.setInt(33, product.getParentId()==null?0:product.getParentId());
					ps.setInt(34, product.getSub_parentId()==null?0:product.getSub_parentId());
					ps.setInt(35, product.getCategory_id()==null?0:product.getCategory_id());
					ps.setString(36, product.getPlatform_price_area());
					ps.setString(37, product.getZcy_price_area());
	                return ps;   
	            }
	        }, keyHolder);   
			if (result > 0) {
				list[0] = result;
				list[1]  = keyHolder.getKey().intValue();
			}else{
				list[0] = 0;
				list[1]  = 0;
			}
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}

		return list;
	}
	

	public int addSpecial(final int product_id ,final Double product_price,final int owning_company) {
		int id =0;
		try {
			final String sql = "insert into tbl_special_products(product_id,product_price,owning_company) values(?,?,?)";

			KeyHolder keyHolder = new GeneratedKeyHolder();   
	        int result = jdbcTemplate.update(new PreparedStatementCreator(){   
	            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {   
	                PreparedStatement ps =  conn.prepareStatement(sql, new String[]{"id"});//返回主键id   
	                ps.setInt(1, product_id);
	                ps.setDouble(2, product_price);
	                ps.setInt(3, owning_company);
	                return ps;   
	            }
	        }, keyHolder);   
			if (result > 0) {
				id = keyHolder.getKey().intValue();
			}
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return id;
	}
	
	/**
	 * 查询内购公司
	 * @Title: querySpecialCompany 
	 * @Description:
	 * @param keyword
	 * @return List         
	 * @throws
	 */
	public List<UserCompany> querySpecialCompany(String keyword){
		StringBuffer sql = new StringBuffer("select * from tbl_user_company where special_code = 1 and gsmc = ?");
		Object[] params = new Object[] { keyword };
		System.out.println(keyword);
		
		List<UserCompany> list = jdbcTemplate.query(sql.toString(),params,
				new BeanPropertyRowMapper<UserCompany>(UserCompany.class));
		return list;
	}
	
	
	/**
	 * 查询商品数量
	 * @Title: queryProductcount 
	 * @Description:
	 * @param keyword
	 * @param type
	 * @return int         
	 * @throws
	 */
	public int queryProductcount(String keyword,String type,Integer sortid1,Integer sortid2,Integer product_id1,Integer product_id2,Integer is_zcy,Integer zcy_id1,Integer zcy_id2) {
		String sql = "select count(1) from tbl_product a where 1=1 ";
		List<Object> paramlist = new ArrayList<Object>();
		if(null!=keyword&&!"".equals(keyword)){
			keyword = "%" + keyword + "%";
			sql += " and ( a.name like ? or a.sub_title like ?)";
			paramlist.add(keyword);
			paramlist.add(keyword);
		}
		if(null!=type&&!"".equals(type)&&!"0".equals(type)){
			type = "%," + type + ",%";
			sql += " and a.type_ids like ? ";
			paramlist.add(type); 
		}
		if(null!=is_zcy&&!"".equals(is_zcy)){
			sql += " and a.is_zcy = ? ";
			paramlist.add(is_zcy); 
		}
		if(null!=sortid1&&!"".equals(sortid1)&&null!=sortid2&&!"".equals(sortid2)){
			sql += " and a.sortid between ? and ? ";
			paramlist.add(sortid1);
			paramlist.add(sortid2);
		}
		if(null!=product_id1&&!"".equals(product_id1)&&null!=product_id2&&!"".equals(product_id2)){
			sql += " and a.id between ? and ? ";
			paramlist.add(product_id1);
			paramlist.add(product_id2);
		}
		if(null!=zcy_id1&&!"".equals(zcy_id1)&&null!=zcy_id2&&!"".equals(zcy_id2)){
			sql += " and a.zcy_id between ? and ? ";
			paramlist.add(zcy_id1);
			paramlist.add(zcy_id2);
		}
		sql += " order by a.sortid desc,a.id desc";
		int total_count = jdbcTemplate.queryForObject(sql, Integer.class,paramlist.toArray());
		return total_count;
	}
	
	/**
	 * 查询商品列表
	 * @Title: queryProductList 
	 * @Description:
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @param type
	 * @return List<Product>         
	 * @throws
	 */
	public List<Product> queryProductList(String keyword,int pageIndex,int pageSize,String type,Integer sortid1,Integer sortid2,Integer product_id1,Integer product_id2,Integer is_zcy,Integer zcy_id1,Integer zcy_id2) {
		String sql = "select a.*,(select sum(c.count) out_count from tbl_product_count c where c.product_id = a.id and c.state = 0) as out_count " +
				"from tbl_product a where 1=1 ";
		int start = pageIndex * pageSize;
		List<Object> paramlist = new ArrayList<Object>();
		if(null!=keyword&&!"".equals(keyword)){
			keyword = "%" + keyword + "%";
			sql += " and (a.name like ? or a.sub_title like ?) ";
			paramlist.add(keyword);
			paramlist.add(keyword);
		}
		if(null!=type&&!"".equals(type)&&!"0".equals(type)){
			type = "%," + type + ",%";
			sql += " and a.type_ids like ? ";
			paramlist.add(type); 
		}
		if(null!=is_zcy&&!"".equals(is_zcy)){
			sql += " and a.is_zcy = ? ";
			paramlist.add(is_zcy); 
		}
		if(null!=sortid1&&!"".equals(sortid1)&&null!=sortid2&&!"".equals(sortid2)){
			sql += " and a.sortid between ? and ? ";
			paramlist.add(sortid1);
			paramlist.add(sortid2);
		}
		if(null!=product_id1&&!"".equals(product_id1)&&null!=product_id2&&!"".equals(product_id2)){
			sql += " and a.id between ? and ? ";
			paramlist.add(product_id1);
			paramlist.add(product_id2);
		}
		if(null!=zcy_id1&&!"".equals(zcy_id1)&&null!=zcy_id2&&!"".equals(zcy_id2)){
			sql += " and a.zcy_id between ? and ? ";
			paramlist.add(zcy_id1);
			paramlist.add(zcy_id2);
		}
		sql += " order by a.sortid desc,a.id desc limit ?,?";
		paramlist.add(start);
		paramlist.add(pageSize);
		List<Product> list = jdbcTemplate.query(sql,paramlist.toArray(),
		new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}
	
	
	/**
	 * 查询商品实体
	 * @Title: queryProduct 
	 * @Description:
	 * @param id
	 * @return Product         
	 * @throws
	 */
	public Product queryProduct(int id) {
		StringBuffer sql = new StringBuffer("select * from tbl_product where id = ?");
		Object[] params = new Object[] { id };
		List<Product> list = jdbcTemplate.query(sql.toString(), params,
				new BeanPropertyRowMapper<Product>(Product.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 查询商品实体
	 * @Title: queryProduct 
	 * @Description:
	 * @param id
	 * @return Product         
	 * @throws
	 */
	public Product getRequiredAttrs(int id) {
		StringBuffer sql = new StringBuffer("select * from tbl_product where id = ?");
		Object[] params = new Object[] { id };
		List<Product> list = jdbcTemplate.query(sql.toString(), params,
				new BeanPropertyRowMapper<Product>(Product.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 查询属性
	 * @Title: queryProductAttrs 
	 * @Description:
	 * @param id
	 * @return List         
	 * @throws
	 */
	public List queryProductAttrs(int id) {
		StringBuffer sql = new StringBuffer("( select a.id,t.attrName `attrKey`,a.product_id,a.attr_id,a.attrVal from  tbl_product_attr_template t left JOIN tbl_product_attrs a on  a.product_id = ? and a.attrKey = t.attrName  where t.delete_flag = 0 and t.category_id = (select category_id from tbl_product where  id = ? LIMIT 1)) ");
		Object[] params = new Object[] { id, id};
		List list = jdbcTemplate.queryForList(sql.toString(),params);
		if(null!=list&&!list.isEmpty()){
			return list;
		}else{
			return null;
		}
	}
	
	/**
	 * 通过id查询内购商品公司
	 * @Title: querySpecailProduct 
	 * @Description:
	 * @param id
	 * @return Product         
	 * @throws
	 */
	public List querySpecialProduct(int id,String name) {
		StringBuffer sql = new StringBuffer("select a.*,b.gsmc from tbl_special_products a  left join tbl_user_company b on a.owning_company= b.id where a.product_id = ? and b.gsmc like ?");
		name = "%"+name+"%";
		Object[] params = new Object[] { id ,name };
		List list = jdbcTemplate.queryForList(sql.toString(), params);
		return list;
		
	}
	
	/**
	 * 删除商品
	 * @Title: delProduct 
	 * @Description:
	 * @param ids
	 * @return int         
	 * @throws
	 */
	public int delProduct(Object[] ids) {
		int count =0;
			StringBuffer sql= new StringBuffer("delete from tbl_product where id in ("); 
			for (int i = 0; i < ids.length; i++) {
				if (i != 0)
					sql.append(",");
				sql.append("?");
			}
			sql.append(")");
			count = jdbcTemplate.update(sql.toString(),ids);
		return count;
	}
	
	/**
	 * 商品单字段更新
	 * @Title: updateProduct 
	 * @Description:
	 * @param id
	 * @param name
	 * @param value
	 * @return int         
	 * @throws
	 */
	public int updateProduct(int id,String name,Object value) {
		int count =0;
		try {
			Object[] params = new Object[] {value,id};
			String sql = "update tbl_product set " + name + " = ? where id = ? ";
			count = jdbcTemplate.update(sql,params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	/**
	 * 删除
	 * @Title: delSpecialProduct 
	 * @Description:
	 * @param id
	 * @return int         
	 * @throws
	 */
	public int delSpecialProduct(int id) {
		int count =0;
		try {
			Object[] params = new Object[] {id};
			String sql = "delete from tbl_special_products where id = ?";
			count = jdbcTemplate.update(sql,params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	/**
	 * 修改内购商品
	 * @Title: editSpecialProduct 
	 * @Description:
	 * @param product
	 * @return int         
	 * @throws
	 */
	public int editSpecialProduct(double product_price,int product_id,int id) {
		int count =0;
		try {
			Object[] params = new Object[] {product_price,product_id,id};
			String sql = "update tbl_special_products set product_price = ? where product_id = ? and id = ?";
			count = jdbcTemplate.update(sql,params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	public int editAttrs(int id,String attrKey,String attrVal) {
		int count =0;
		try {
			Object[] params = new Object[] {attrVal,id,attrKey};
			String sql = "update tbl_product_attrs set attrVal = ? where product_id = ? and attrKey = ?";
			count = jdbcTemplate.update(sql,params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	public int[] addAttrs(final int product_id,final int attr_id,final String attrKey,final String attrVal) {
			int result =0;
			int[] list  = new int[2];

			try {
				final  String sql = "insert into tbl_product_attrs(product_id,attr_id,attrKey,attrVal) values(?,?,?,?)";
				KeyHolder keyHolder = new GeneratedKeyHolder();   
				result = jdbcTemplate.update(new PreparedStatementCreator(){   
		            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {   
		                PreparedStatement ps =  conn.prepareStatement(sql, new String[]{"id"});//返回主键id   
		                ps.setInt(1, product_id);
						ps.setInt(2, attr_id);
						ps.setString(3, attrKey);
						ps.setString(4, attrVal);
		                return ps;   
		            }
		        }, keyHolder);   
				if (result > 0) {
					list[0] = result;
					list[1]  = keyHolder.getKey().intValue();
				}
			} catch (Exception ex) {
				System.err.println("---------------------------------------------");
				System.err.println(ex.getMessage());
				System.err.println("---------------------------------------------");
			}

			return list;

	}
	
	/**
	 * 修改商品
	 * @Title: modifyProduct 
	 * @Description:
	 * @param product
	 * @return int         
	 * @throws
	 */
	public int modifyProduct(Product product) {
		int count =0;
		try {
			Object[] params = new Object[] {
					product.getType_ids(),
					product.getBrand_id(),
					product.getXh(),
					product.getPz(),
					product.getName(),
					product.getSub_title(),
//					product.getImg_url(),
					product.getCount(),
					product.getPrice_old(),
					product.getPrice_new(),
					product.getContent(),
					product.getSortid(),
					product.getIs_yj(),
					product.getIs_cx(),
					product.getIs_ms(),
					product.getIs_yun(),
					product.getIs_wx(),
					product.getIs_pc(),
					product.getIs_red(),
					product.getDelete_flag(),
					product.getMs_kl(),
					product.getMs_begintime(),
					product.getNeedInstall(),
					product.getLimit(),
					product.getCountryId(),
					product.getProvinceId(),
					product.getCityId(),
					product.getFirm(),
					product.getService(),
					product.getRegionId(),
					product.getIs_zcy(),
					product.getIs_zcysj(),
					product.getSpecial_code(),
					product.getIs_zcy(),
					product.getId()
					
				};
			String sql = "UPDATE `tbl_product` SET  `type_ids`=?, `brand_id`=?, `xh`=?, `pz`=?, `name`=?, `sub_title`=?,  `count`=?, `price_old`=?, `price_new`=?, `content`=?, `sortid`=?, `is_yj`=?, `is_cx`=?, `is_ms`=?, `is_yun`=?, `is_wx`=?, `is_pc`=?, `is_red`=?, `delete_flag`=?, `ms_kl`=?, `ms_begintime`=?, `needInstall`=?, `limit`=?, `countryId`=?, `provinceId`=?, `cityId`=?, `firm`=?, `service`=?, `regionId`=?, `is_zcy`=?, `is_zcysj`=?, `special_code`=?, `is_zcy`= ? WHERE (`id`=?);";
			count = jdbcTemplate.update(sql,params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	/**
	 * 查询品牌列表
	 * @Title: queryProductBrandList 
	 * @Description:
	 * @return List<ProductBrand>         
	 * @throws
	 */
	public List<ProductBrand> queryProductBrandList() {
		String sql = "select * from tbl_product_brand where delete_flag = 0 order by sortid desc,id desc";
		List<ProductBrand> list = jdbcTemplate.query(sql,
		new BeanPropertyRowMapper<ProductBrand>(ProductBrand.class));
		return list;
	}
	
	/**
	 * 根据大类查询类别列表
	 * @Title: queryProductTypeList 
	 * @Description:
	 * @param parentid
	 * @return List<ProductType>         
	 * @throws
	 */
	public List<ProductType> queryProductTypeList(int parentid) {
		String sql = "select * from tbl_product_type where parentId = ?  and delete_flag = 0 order by sortid desc,id desc";
		Object[] params = new Object[] { parentid};
		List<ProductType> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<ProductType>(ProductType.class));
		return list;
	}

	/**
	 * 查询所有分类信息
	 * @Title: queryProductTypeList 
	 * @Description:
	 * @return List<ProductType>         
	 * @throws
	 */
	public List<ProductType> queryProductTypeList() {
		String sql = "select * from tbl_product_type where delete_flag = 0 order by sortid desc,id desc";
		List<ProductType> list = jdbcTemplate.query(sql,
		new BeanPropertyRowMapper<ProductType>(ProductType.class));
		return list;
	}
	
	/**
	* 根据本地三级类目id获取三级类目的属性信息
	 * @param categoryId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public  List  queryProductAttributeForLocal(Integer categoryId){
		String sql ="select `id`, `category_id`, `category_name`, `sub_name`, `root_name`, `zcy_attr_id`, `attrName`, `group`, `isRequired`, `isImportant`, `isSukCandidate`, `isUserDefined`, `valueType`, `attrVals`, `local_type_id` "
				+" from tbl_product_attr_template where local_type_id = ? ORDER BY CONVERT( `group` USING gbk ) COLLATE gbk_chinese_ci asc  , isRequired desc , id asc";
		return jdbcTemplate.queryForList(sql, new Object[]{categoryId});
	}
	
	/**
	* 根据政采云三级类目id获取三级类目的属性信息
	 * @param categoryId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public  List  queryProductAttribute(Integer categoryId){
		String sql ="select `id`, `category_id`, `category_name`, `sub_name`, `root_name`, `zcy_attr_id`, `attrName`, `group`, `isRequired`, `isImportant`, `isSukCandidate`, `isUserDefined`, `valueType`, `attrVals`, `local_type_id` "
				+" from tbl_product_attr_template where category_id = ? and delete_flag=0 ORDER BY CONVERT( `group` USING gbk ) COLLATE gbk_chinese_ci asc  , isRequired desc , id asc";
		return jdbcTemplate.queryForList(sql, new Object[]{categoryId});
	}

	
	/**
	 * 删除商品
	 * @Title: delProduct 
	 * @Description:
	 * @param ids
	 * @return int         
	 * @throws
	 */
	public int delProductAttr(Object[] ids) {
		int count =0;
			StringBuffer sql= new StringBuffer("delete from tbl_product_attrs where product_id in ("); 
			for (int i = 0; i < ids.length; i++) {
				if (i != 0)
					sql.append(",");
				sql.append("?");
			}
			sql.append(")");
			count = jdbcTemplate.update(sql.toString(),ids);
		return count;
	}
	

	@SuppressWarnings("rawtypes")
	public  List  getAreaList(int parentCode,int level){
		String sql ="select id,code,name,level from dd_district where parentid = (select id from dd_district where code = ?) and level = ?";
		return jdbcTemplate.queryForList(sql, new Object[]{parentCode,level});
	}
	
	@SuppressWarnings("rawtypes")
	public  List  getCountryList(){
		String sql ="select * from tbl_country where delete_code = 0; ";
		return jdbcTemplate.queryForList(sql);
	}
	
	/**
	 * 根据政采云状态获取product
	 * @Title: getProductByStatus 
	 * @Description:
	 * @param status
	 * @return List         
	 * @throws
	 */
	@SuppressWarnings("null")
	public  List<Product>  getProductByStatus(int[] status){
		String sql ="select * from tbl_product where is_zcy in (";
		List<Object> paramlist = new ArrayList<Object>();
		for(int i=0;i<status.length;i++){
			sql += "?,";
			paramlist.add(status[i]);
		}
		if(status.length!=0){
			sql = sql.substring(0,sql.length()-1) +")";
		}
		List<Product> list = jdbcTemplate.query(sql,paramlist.toArray(),
				new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}
	

	public void addOperateLog(OperateLog log){
		String sql = "INSERT INTO `tulingbuy`.`tbl_operate_log` ( `user_id`, `product_id`, `use_name`, `content`, `oprate_time`, `behavior`) VALUES ( ?,?,?,?,now(),? );";
		jdbcTemplate.update(sql, new Object[]{log.getUserId(),log.getProductId(),log.getUserName(),log.getContent(),log.getBehavior()});
		
	}
	
}