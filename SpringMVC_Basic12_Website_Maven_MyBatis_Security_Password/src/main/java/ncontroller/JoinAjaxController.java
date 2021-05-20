package ncontroller;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dao.MemberDao;

@RestController
public class JoinAjaxController {

	private SqlSession sqlsession;

	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	//중복확인
	@RequestMapping(value="idcheck.htm")
	public String idCheck(@RequestParam(value="userid") String userId) throws ClassNotFoundException, SQLException {
		String ischeck = null;
		
		MemberDao memberdao = sqlsession.getMapper(MemberDao.class);
		
		int result = memberdao.idCheck(userId);
		if (result > 0) {
			System.out.println("아이디 중복");
			ischeck = "false";
		} else {
			System.out.println("중복 아님");
			ischeck = "true";
		}
	
		return ischeck;
	}
	
	
	
}
