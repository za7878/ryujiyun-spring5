select bno from (
select bno from tbl_board order by bno desc
) where rownum = 1
;
--게시물등록시 발생된 bno를 구한 결과값 1개만 구해서 첨부파일의 bno입력값으로 사용.