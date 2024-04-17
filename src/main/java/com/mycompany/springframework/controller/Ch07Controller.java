package com.mycompany.springframework.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.springframework.dto.Ch07Board;
import com.mycompany.springframework.dto.Ch07JoinForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch07")
public class Ch07Controller {
   @GetMapping("/request1")
   public ModelAndView request1(Model model) {
      // 데이터 생성
      List<Ch07Board> boardList = new ArrayList<>();
      for (int i = 1; i <= 10; i++) {
         boardList.add(new Ch07Board(i, "제목" + i, "봄날이여~" + i, "글쓴이" + i, 0, new Date()));
      }
      ModelAndView modelAndView = new ModelAndView();
      // request 범위 저장에 boardList 저장
      modelAndView.addObject("boardList", boardList);
      modelAndView.addObject("chNum", "ch07");
      // ViewName을 저장
      modelAndView.setViewName("ch07/requestData");

      return modelAndView;
   }

   /*
    * @GetMapping("/request2") public String request2(Model model, Ch07Board board)
    * {
    * 
    * model.addAttribute("chNum", "ch07"); return "ch07/writeBoardForm"; }
    */

   // Ch07JoinForm의 필드의 값 세팅
   @ModelAttribute("jobList")
   public List<String> getJobList() {
      List<String> jobList = new ArrayList<>();
      jobList.add("개발자");
      jobList.add("공무원");
      jobList.add("은행원");
      return jobList;
   }

   // Ch07JoinForm의 필드의 값 세팅   
   @ModelAttribute("cityList")
   public List<String> getCityList() {
      List<String> cityList = new ArrayList<>();
      cityList.add("서울");
      cityList.add("대전");
      cityList.add("제주");
      return cityList;
   }
   
   // Ch07JoinForm 객체를 생성하여 세팅하고 JSP에서 ${joinForm}으로 사용할 수 있게끔 모델에 연결
   @GetMapping("/request2")
   public String request2(Model model) {
      model.addAttribute("chNum", "ch07");

      Ch07JoinForm joinForm = new Ch07JoinForm();
      joinForm.setMid("fall");
      joinForm.setMname("한가을");
      joinForm.setMjob("공무원");
      joinForm.setMcity("제주");
      model.addAttribute("joinForm", joinForm);

      return "ch07/joinForm";
   }

   @PostMapping("/request2") // Get방식과 다르기 때문에 사용 가능하다. 단, 메소드 이름은 달라야 한다.
   public String postRequest2(Model model, @ModelAttribute("joinForm") Ch07JoinForm joinForm) { 
	  
      model.addAttribute("chNum", "ch07");
      return "ch07/memberInfo";
   }

   // 세션 범위 예제 시작
   @GetMapping("/sessionLoginForm")
   public String sessionLoginForm(Model model) {

      model.addAttribute("chNum", "ch07");
      return "ch07/loginForm";
   }
   // 주소에 요청 파라미터가 유출될 우려가 있기 때문에 로그인시 post방식으로 매핑받는다.
   @PostMapping("/sessionLogin")
   public String sessionLogin(Model model, HttpSession session) {
      session.setAttribute("login", "success");
      return "redirect:/ch07/sessionLoginForm";
   }
   // 로그아웃시에는 세션을 만료시켜야하기 때문에 굳이 post방식으로 쓸 이유가 없다.
   @GetMapping("/sessionLogout")
   public String sessionLogout(Model model, HttpSession session) {
      session.removeAttribute("login");
      return "redirect:/ch07/sessionLoginForm";
   }

   // 게시판의 좋아요 나빠요 구현에 필요할 것 같다.
   // 어플리케이션 범위는 요청을 하여 응답을 받아도 데이터가 기록된다.
   @GetMapping("/application")
   public String application(Model model, HttpServletRequest request) {
	   model.addAttribute("chNum", "ch07");
	   
	   // application 범위에 counter 이름의 값(객체)를 가져오기
	   ServletContext application = request.getServletContext();
	   Integer counter = (Integer) application.getAttribute("counter");
	   
	   // 값(객체) 존재 유무에 따라 처리
	   if(counter == null) {
		   application.setAttribute("counter", 1); // 1이라는 값이 Integer 객체로 boxing이 됨
	   } else {
		   application.setAttribute("counter", 1 + counter); // 계속적으로 1씩 추가가 된다.
	   }
	   
	   return "ch07/applicationData";
   }
}