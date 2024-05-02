package com.mycompany.springframework.dao;

import org.springframework.stereotype.Repository;

@Repository("ch12Dao7") // 이걸 붙여주어야 Ch12Service8.java 소스에서 선언한 필드가 자동적으로 객체를 만들어 준다.
public class Ch12Dao7 implements Ch12DaoInterface {
	
}
