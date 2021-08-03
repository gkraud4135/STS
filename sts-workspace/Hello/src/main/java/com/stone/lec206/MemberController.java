package com.stone.lec206;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.SqlSession;
import java.util.*;
import com.stone.lec206.dto.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.naming.NamingException;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class MemberController {

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("main.do")
	public String mm() {
		return "main"; //뷰리턴 views/main.jsp
	}
	
	//회원가입폼
	@RequestMapping("insertForm.do")
	public String insertForm() {
		return "/member/insertForm"; // views/member/insertForm.jsp
	}
	
	//아이디 중복체크
	@RequestMapping(value="confirmID", method=RequestMethod.POST )
	public String idCheck(String id, Model model) {
		int check = -1;
		MemberDTO dto = sqlSession.selectOne("member.selectOne",id);  //member.selectOne >> member.xml에서의 id로 사용
		if(dto==null) {
			check=1;
			
		}else {
			check=-1;
		}
		
		model.addAttribute("check",check);
		return "member/confirmID";    //뷰 confirmID.jsp
	}
	
	
	
}
