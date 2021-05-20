package dao;

import java.sql.SQLException;

import vo.Member;

public interface MemberDao {
	public Member getMember(String uid) throws ClassNotFoundException, SQLException;
	public int insertMember(Member member) throws ClassNotFoundException, SQLException;
	public int idCheck(String userid) throws ClassNotFoundException, SQLException;
	public int loginCheck(int userid, int userpwd) throws ClassNotFoundException, SQLException;
	public int updateMember(Member member) throws ClassNotFoundException, SQLException;
	
	
	
}
