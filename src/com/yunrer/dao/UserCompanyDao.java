package com.yunrer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;
import com.yunrer.entity.OrderInfo;
import com.yunrer.entity.UserCompany;
import com.yunrer.entity.UserCompanyImg;
/**
 * 公司DAO
 * @ClassName UserCompanyDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("UserCompanyDao")
public class UserCompanyDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询公司信息
	 * @Title: queryCompany 
	 * @Description:
	 * @param user_id
	 * @return UserCompany         
	 * @throws
	 */
	public UserCompany queryCompany(int user_id) {
		StringBuffer sql = new StringBuffer("select a.* from tbl_user_company a left join tbl_user_info b on b.owning_company = a.id where b.user_id = ?");
		Object[] params = new Object[] { user_id };
		List<UserCompany> list = jdbcTemplate.query(sql.toString(), params,
				new BeanPropertyRowMapper<UserCompany>(UserCompany.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 查询公司资质列表
	 * @Title: queryCompanyImgList 
	 * @Description:
	 * @param user_id
	 * @return List<UserCompanyImg>         
	 * @throws
	 */
	public List<UserCompanyImg> queryCompanyImgList(int user_id) {
		StringBuffer sql = new StringBuffer("select a.* from tbl_user_company_img a left join tbl_user_info b on b.owning_company = a.id where b.user_id = ?");
		Object[] params = new Object[] { user_id };
		List<UserCompanyImg> list = jdbcTemplate.query(sql.toString(), params,
				new BeanPropertyRowMapper<UserCompanyImg>(UserCompanyImg.class));
		return list;
	}
	
	/**
	 * 根据条件查询公司
	 * @Title: queryCriteriaCompany 
	 * @Description:
	 * @param queryStr
	 * @return List<UserCompany>         
	 * @throws
	 */
	public List<UserCompany> queryCriteriaCompany(String queryStr){
		StringBuffer sql = new StringBuffer("select * from tbl_user_company where 1=1 and "+queryStr);
		List<UserCompany> list = jdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper<UserCompany>(UserCompany.class));
		return list;
	}
	

	/**
	 * 添加公司信息
	 * @Title: addCompanyInfo 
	 * @Description:
	 * @param c
	 * @return int         
	 * @throws
	 */
	public int addCompanyInfo(UserCompany c){
		StringBuffer sql = new StringBuffer("insert into tbl_user_company (user_id,gsmc,zczj,yyzzzch,fddbr,jyms,ygrs,bgdz) values (?,?,?,?,?,?,?,?)");
		Object[] params = new Object[]{c.getUser_id(),c.getGsmc(),c.getZczj(),c.getYyzzzch(),c.getFddbr(),c.getJyms(),c.getYgrs(),c.getBgdz()};
		return 	jdbcTemplate.update(sql.toString(), params);
		
		
	}
	/**
	 * 修改公司管理员（常用联系人）
	 * @Title: updateCompanyInfo 
	 * @Description:
	 * @param c
	 * @return int         
	 * @throws
	 */
	public int updateCompanyInfo(UserCompany c){
		StringBuffer sql = new StringBuffer("update tbl_user_company set user_id = ? where id = ?");
		Object[] params = new Object[]{c.getUser_id(),c.getId()};
		return 	jdbcTemplate.update(sql.toString(), params);
	}
	
	/**
	 * 按条件检索公司列表
	 * @Title: queryUserCompanyList 
	 * @Description:
	 * @param gsmc
	 * @param fddbr
	 * @param bgdz
	 * @param pageIndex
	 * @param pageSize
	 * @return List<UserCompany>         
	 * @throws
	 */
	public List<UserCompany> queryUserCompanyList(String gsmc,String fddbr,String bgdz,int pageIndex,int pageSize) {
		List<Object> paramlist = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select * from tbl_user_company where 1=1 ");
		int start = pageIndex * pageSize;
		if(gsmc!=null&&!"".equals(gsmc)){
			sql.append(" and gsmc like ?");
			gsmc = "%"+gsmc+"%";
			paramlist.add(gsmc);
		};
		if(fddbr!=null&&!"".equals(fddbr)){
			sql.append(" and  fddbr like ?");
			fddbr = "%"+fddbr+"%";
			paramlist.add(fddbr);
		}
		if(bgdz!=null&&!"".equals(bgdz)){
			sql.append(" and  bgdz like ?");
			bgdz = "%"+bgdz+"%";
			paramlist.add(bgdz);
		}
		if(pageIndex!=-1){
		sql.append(" order by id limit ?,?");
		paramlist.add(start);
		paramlist.add(pageSize);
		}
		Object[] params = paramlist.toArray();
		
		List<UserCompany> list = jdbcTemplate.query(sql.toString(),params,
				new BeanPropertyRowMapper<UserCompany>(UserCompany.class));
		return list;
	}
	
	/**
	 * 按条件检索公司数量
	 * @Title: queryUserCompanycount 
	 * @Description:
	 * @param gsmc
	 * @param fddbr
	 * @param bgdz
	 * @return int         
	 * @throws
	 */
	public int queryUserCompanycount(String gsmc,String fddbr,String bgdz) {
		List<Object> paramlist = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select count(1) from tbl_user_company where 1=1 ");
		
		if(gsmc!=null&&!"".equals(gsmc)){
			sql.append(" and gsmc like ?");
			gsmc = "%"+gsmc+"%";
			paramlist.add(gsmc);
		};
		if(fddbr!=null&&!"".equals(fddbr)){
			sql.append(" and  fddbr like ?");
			fddbr = "%"+fddbr+"%";
			paramlist.add(fddbr);
		}
		if(bgdz!=null&&!"".equals(bgdz)){
			sql.append(" and  bgdz like ?");
			bgdz = "%"+bgdz+"%";
			paramlist.add(bgdz);
		}
		Object[] params = paramlist.toArray();
		int count = jdbcTemplate.queryForObject(sql.toString(),Integer.class,params);
				
		return count;
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
		StringBuffer sql = new StringBuffer("select * from tbl_user_company where special_code = 1 and gsmc like ? limit 10");
		keyword = "%"+keyword +"%";
		Object[] params = new Object[] { keyword };
		System.out.println(keyword);
		
		List<UserCompany> list = jdbcTemplate.query(sql.toString(),params,
				new BeanPropertyRowMapper<UserCompany>(UserCompany.class));
		return list;
	}
	
	/**
	 * 新增公司信息
	 * @Title: addCompany 
	 * @Description:
	 * @param userCompany
	 * @return int         
	 * @throws
	 */
	public int addCompany(final UserCompany userCompany) {
		final StringBuffer sql = new StringBuffer("insert into tbl_user_company(user_id,gsmc,zczj,yyzzzch,fddbr,jyms,ygrs,bgdz,staffs_number,gsjs) values(?,?,?,?,?,?,?,?,?,?)");
		
		  KeyHolder keyHolder = new GeneratedKeyHolder();  
		    jdbcTemplate.update(new PreparedStatementCreator() {  
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
		              
		               PreparedStatement ps = connection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);  
		               ps.setInt(1, userCompany.getUser_id()==null?0:userCompany.getUser_id());  
		               ps.setString(2, userCompany.getGsmc());  
		               ps.setString(3, userCompany.getZczj());  
		               ps.setString(4, userCompany.getYyzzzch());   
		               ps.setString(5, userCompany.getFddbr());  
		               ps.setString(6, userCompany.getJyms());  
		               ps.setString(7, userCompany.getYgrs()); 
		               ps.setString(8, userCompany.getBgdz());  
		               ps.setInt(9, userCompany.getStaffs_number()==null?0:userCompany.getStaffs_number());  
		               ps.setString(10, userCompany.getGsjs());
		               return ps;  
		        }  
		    }, keyHolder);  
		      
		    int generatedId = keyHolder.getKey().intValue();   
		    return generatedId;  
	}
	
	/**
	 * 修改公司信息
	 * @Title: updateCompany 
	 * @Description:
	 * @param userCompany
	 * @return int         
	 * @throws
	 */
	public int updateCompany(UserCompany userCompany) {
		StringBuffer sql = new StringBuffer("update tbl_user_company set gsmc=?,zczj=?,yyzzzch=?,fddbr=?,jyms=?,ygrs=?,bgdz=?,special_code=? where  id = ?");
		Object[] params = new Object[] { userCompany.getGsmc(),userCompany.getZczj(),userCompany.getYyzzzch(),userCompany.getFddbr(),userCompany.getJyms(),userCompany.getYgrs(),userCompany.getBgdz(),userCompany.getSpecial_code(),userCompany.getId() };
		return jdbcTemplate.update(sql.toString(),params);
	}
	

	
	
}