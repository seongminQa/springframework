package com.mycompany.springframework.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Pager;

@Mapper
public interface Ch13BoardDao {
	// board.xml파일에서의 id가 메소드 이름이 되어야 한다.
//	public void insert(Ch13Board board);
	public int insert(Ch13Board board);
	public int count();
	public List<Ch13Board> selectByPage(Ch13Pager pager);
	public Ch13Board selectByBno(int bno);
	public Ch13Board selectAttachData(int bno);
	public int update(Ch13Board board);
	public int deleteByBno(int bno); // 가급적이면 int타입으로 한다.
}
