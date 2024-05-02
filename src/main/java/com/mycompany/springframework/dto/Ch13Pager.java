package com.mycompany.springframework.dto;

import lombok.Data;

@Data
public class Ch13Pager {
   private int totalRows;      // 페이징 대상이 되는 전체 행수
   private int totalPageNo;    // 전체 페이지 수  // 1만개의 리스트가 있고 페이지당 10개의 리스트가 있다면 총 1000페이지를 나타냄
   private int totalGroupNo;   // 전체 그룹 수  // [처음][이전]1 2 3 4 ... [다음][맨끝] // 1~10까지 하나의 그룹. [다음]을 누르면 11~20그룹이 나옴 // 위의 예를 든다면 총 그룹수는 100개
   private int startPageNo;    // 그룹의 시작 페이지 번호 // 1, 11, 21 ...
   private int endPageNo;      // 그룹의 끝 페이지 번호  // 10, 20, 30 ...
   private int pageNo;         // 현재 페이지 번호 
   private int pagesPerGroup;  // 그룹당 페이지 수  // 한 그룹에 페이지의 갯수를 정하자. 위의 예를 들면 10개
   private int groupNo;        // 현재 그룹 번호  // 1~10은 1그룹, 21~30은 3그룹
   private int rowsPerPage;    // 페이지당 행 수   // 한 페이지당 리스트의 개수
   private int startRowNo;     // 페이지의 시작 행 번호(1, ..., n)  // 1, 11, 21 등
   private int startRowIndex;  // 페이지의 시작 행 인덱스(0, ..., n-1) for mysql  // 앞서 배웠던 rownum. 오라클은 1부터시작, mySQL은 0부터 시작 (DBMS마다 다를 수 있음)
   private int endRowNo;       // 페이지의 마지막 행 번호  // 오라클은 1~10이라면 '10', mySQL은 0~9라면 '9'
   private int endRowIndex;    // 페이지의 마지막 행 인덱스 

   public Ch13Pager(int rowsPerPage, int pagesPerGroup, int totalRows, int pageNo) {
      this.rowsPerPage = rowsPerPage;
      this.pagesPerGroup = pagesPerGroup;
      this.totalRows = totalRows;
      this.pageNo = pageNo;

      totalPageNo = totalRows / rowsPerPage;
      if(totalRows % rowsPerPage != 0) totalPageNo++;
      
      totalGroupNo = totalPageNo / pagesPerGroup;
      if(totalPageNo % pagesPerGroup != 0) totalGroupNo++;
      
      groupNo = (pageNo - 1) / pagesPerGroup + 1;
      
      startPageNo = (groupNo-1) * pagesPerGroup + 1;
      
      endPageNo = startPageNo + pagesPerGroup - 1;
      if(groupNo == totalGroupNo) endPageNo = totalPageNo;
      
      startRowNo = (pageNo - 1) * rowsPerPage + 1;
      startRowIndex = startRowNo - 1;
      endRowNo = pageNo * rowsPerPage;
      endRowIndex = endRowNo - 1; 
   }
}
