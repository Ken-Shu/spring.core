package spring.core.session06.templat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.core.session06.entity.Emp;
import spring.core.session06.entity.Job;

//DAO : Data Access Object
@Repository
public class EmpDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
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
	
	// 多筆查詢 :
	public List<Job> queryJobs(){
		String sql = "select j.jid ,j.jname ,j.eid , "
				+ "	e.eid as emp_eid , e.ename as emp_ename , e.age as emp_age, e.createtime as emp_createtime "
				+ "from job j left join emp e on j.eid=e.eid";
		//資料提取器
				ResultSetExtractor<List<Job>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
						.addKeys("jid")  // 對應 key
						.newResultSetExtractor(Job.class); // 要注入到的物件類
		return jdbcTemplate.query(sql, resultSetExtractor);
		
		
	}
		
	// 單筆查詢 : 
	// 取得某單一員工的所有工作
	public List<Job> queryJobsByEmpId(Integer eid){
		String sql = "select jid, jname ,eid from job where eid=?";
		List<Job> jobs = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Job>(Job.class),eid);
		return jobs;
	}
	
	// 單筆查詢 Emp
	public Emp getEmpById(Integer id) {
		String sql = "select eid, ename, age, createtime from emp where eid=?";
		Object[] args = {id};
		//return  jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<Emp>(Emp.class));
		
		// api : queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
		// return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Emp>(Emp.class), id);
		Emp emp = null;
		try {
			emp = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Emp>(Emp.class), id); 
		} catch (DataAccessException e) {
			System.out.println("查無此人 : " + e);
		}
		return emp;
	}

	// 單筆查詢 Emp
	public Emp getEmpById(Integer id , Boolean hasRelative) {
		Emp emp = getEmpById(id);
		// emp 不需要關聯到 job
		if(!hasRelative) {
			return emp;
		}
		// emp 需要關聯到 job * (0~多筆)
		List<Job> jobs = queryJobsByEmpId(id);
		// 將 jobs 注入到 emp 物件
		emp.setJobs(jobs);
		return emp;
	}
	
	// 單筆查詢 Job
	public Job getJobById(Integer id) {
		String sql = "select jid, jname ,eid from job where jid = :job_id";
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("job_id", id);		
		Job job = null;
		try {
			job = namedParameterJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<Job>(Job.class));
		} catch (DataAccessException e) {
			System.out.println("查無此工作 : " + e);
		}
		return job;
	}		
	
	//單筆查詢 Job
	//若 hasRelative = true 則會找出該 job 所對應的 Emp 物件
	public Job getJobById(Integer id , Boolean hasRelative) {
		Job job = getJobById(id);
		// emp 不需要關聯到 job
		if(!hasRelative) {
			return job;
		}
		// emp 需要關聯到 job * (0~多筆)
		// Emp emps = queryEmpByJobId(job.getEid());
		Emp emp = getEmpById(job.getEid());
		// 將 jobs 注入到 emp 物件
		job.setEmp(emp);
		return job;
	}
	// 上方寫法另一方案
	public Emp queryEmpByJobId(Integer jid){
		String sql = "select eid, ename ,age , createtime from emp where eid = ?";
		Emp emps = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Emp>(Emp.class),jid);
		return emps;
	}
	
	
	// 單筆新增 I
	public int addOne1(String ename , Integer age) {
		String sql ="insert into emp(ename,age) values(?,?)";
		int rowcount = jdbcTemplate.update(sql , ename , age);
		return rowcount;
	}
	
	// 單筆新增 II
	public int addOne2(String ename , Integer age) {
		String sql ="insert into emp(ename,age) values(:emp_name , :emp_age)";
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("emp_name", ename)
				.addValue("emp_age", age);
		int rowcount = namedParameterJdbcTemplate.update(sql, params);
		return rowcount;
	}
	
	//批次新增 I
	public int[] batchAdd1(List<Object[]> rows) {
		String sql ="insert into emp(ename , age) values(?,?)";		
		return jdbcTemplate.batchUpdate(sql, rows);
	}
	
	//批次新增 II
	public int[] batchAdd2(List<Emp> emps) {
		String sql ="insert into emp(ename , age) values(?,?)";
		BatchPreparedStatementSetter pss = new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				// i 就是 emps 的 index
				ps.setString(1 , emps.get(i).getEname());
				ps.setInt(2, emps.get(i).getAge());
				
			}
			
			@Override
			public int getBatchSize() {
				
				return emps.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, pss);
	}

	//修改 I
	public int updateEnameAndAgeById(Integer eid , String ename , Integer age) {
		String sql = "update emp set ename=? , age=? where eid=?";
		//按照問號的順序放入參數
		return jdbcTemplate.update(sql,ename,age,eid);
	}
	//修改 II
	public int updateEmp(Emp emp) {
		//String sql = "update emp set ename=? , age=? where eid=?";
		//return jdbcTemplate.update(sql,emp.getEname(),emp.getAge(),emp.getEid());
		return updateEnameAndAgeById(emp.getEid(),emp.getEname(), emp.getAge() );
	}
	
	// 刪除
	public int deleteEmpById(Integer eid) {
		String sql ="delete from emp where eid =?";
		// 刪除一樣也是使用 update
		return jdbcTemplate.update(sql,eid);
	}
	
}
