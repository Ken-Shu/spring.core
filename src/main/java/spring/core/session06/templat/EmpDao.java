package spring.core.session06.templat;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.core.session06.entity.Emp;
import spring.core.session06.entity.Job;

//DAO : Data Access Object
@Repository
public class EmpDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//@Autowired
	//private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	// 多筆查詢 : 全部查詢 I
	public List<Map<String, Object>> queryAll(){
		String sql = "select eid, ename, age, createtime from emp";
		List<Map<String, Object>> employees = jdbcTemplate.queryForList(sql);
		return employees;
	}
	
	// 多筆查詢 : 全部查詢 II
	public List<Emp> queryAll2(){
		String sql = "select eid, ename, age, createtime from emp";
		// RowMapper 逐行資料對應 (ResultSet rs, int rowNum)
		RowMapper<Emp> rowMapper = (ResultSet rs, int rowNum) -> {
			Emp emp = new Emp();
			emp.setEid(rs.getInt("eid"));
			emp.setEname(rs.getString("ename"));
			emp.setAge(rs.getInt("age"));
			emp.setCreatetime(rs.getTimestamp("createtime"));
			return emp;
		};
		List<Emp> emps = jdbcTemplate.query(sql,rowMapper);
		return emps;
	}
	
	// 多筆查詢 : 全部查詢 III
	public List<Emp> queryAll3(){
		String sql = "select eid, ename, age, createtime from emp";
		
		List<Emp> emps = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Emp>(Emp.class));
		return emps;
	}
	
	// 多筆查詢 : 全部查詢 IV ()
	public List<Emp> queryAll4(){
		String sql = "select eid, ename, age, createtime from emp";
		// RowMapper 逐行資料對應 (ResultSet rs, int rowNum)
		RowMapper<Emp> rowMapper = (ResultSet rs, int rowNum) -> {
			Emp emp = new Emp();
			emp.setEid(rs.getInt("eid"));
			emp.setEname(rs.getString("ename"));
			emp.setAge(rs.getInt("age"));
			emp.setCreatetime(rs.getTimestamp("createtime"));
			// 尋找 每一個 emp 有那些 jobs
			String sql2 = "select jid , jname , eid from job where eid="+emp.getEid();
			List<Job> jobs = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<Job>(Job.class));
			// 將 job 設定到 emp中
			emp.setJobs(jobs);
			return emp;
		};
		List<Emp> emps = jdbcTemplate.query(sql,rowMapper);
		return emps;
	}
	
	// 多筆查詢 : 全部查詢 V ()
	public List<Emp> queryAll5(){
		String sql = "select e.eid, e.ename, e.age, e.createtime, " +  // emp 主要table
			     "j.jid as job_jid, j.jname as job_jname, j.eid as job_eid " + // job 要加上 job_ [格式:(資料表名_欄位名)] 的欄位名 次要 table
			     "from emp e left join job j on j.eid = e.eid";
		//資料提取器
		ResultSetExtractor<List<Emp>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys("eid")  // 對應 key
				.newResultSetExtractor(Emp.class); // 要注入到的物件類
		
		return jdbcTemplate.query(sql, resultSetExtractor);
	}

}
