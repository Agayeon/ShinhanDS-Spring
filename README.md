# ShinhanDS-Spring

# 🌱 Spring MVC Parameter Handling Practice

Spring 프레임워크에서 다양한 방식으로 클라이언트 파라미터를 처리하는 방법을 실습한 예제입니다.  
폼 입력 처리, DTO 자동 바인딩, Map 처리, 유효성 검사 등을 연습했습니다.

---

## 📁 프로젝트 구조

📦src
┣ 📂main
┃ ┗ 📂java
┃ ┗ 📂com.shinhan.spring.controller
┃ ┗ 📄SampleController2.java
┣ 📂webapp
┃ ┣ 📂WEB-INF/views
┃ ┃ ┗ 📂dept
┃ ┃ ┗ 📄deptinsert.jsp
┃ ┗ 📂resources/img
┃ ┗ 📄d.jpg

yaml
복사
편집

---

## 📌 주요 실습 내용

### ✅ 다양한 방식의 파라미터 처리

| 방식 | 설명 | 예시 메서드 |
|------|------|-------------|
| `@RequestParam Map` | 모든 파라미터를 Map으로 받기 | `f6()` |
| DTO 바인딩 | 자바빈(DeptDTO)에 자동 매핑 | `f5()` |
| 개별 파라미터 | 각각 파라미터를 직접 받기 | `f4()` |

---

## 🧾 컨트롤러 예시 코드

```java
@PostMapping("/deptinsert3.do")
@ResponseBody
public String f5(@RequestParam("job") String job, DeptDTO dept) {
    System.out.println("job: " + job);
    System.out.println("JAVA Beans: " + dept);
    deptService.deptInsert(dept);

    DeptDTO deptInsert = deptService.selectById(dept.getDepartment_id());
    System.out.println(deptInsert);

    return "parameter OK";
}
🖥️ JSP 입력 폼 (deptinsert.jsp)
✍️ 입력 항목
부서코드 (department_id)

부서이름 (department_name)

메니저번호 (manager_id)

지역코드 (location_id)

📑 폼 예시
html
복사
편집
<form id="myfrm" action="deptinsert.do" method="post">
    <input type="hidden" name="job" value="insert">
    <label>부서코드 :</label>
    <input type="number" name="department_id" required><br>
    <label>부서이름 :</label>
    <input name="department_name" required><br>
    <label>메니저번호 :</label>
    <input type="number" name="manager_id"><br>
    <label>지역코드 :</label>
    <input type="number" name="location_id"><br>
    <input type="submit" value="입력">
    <input type="reset" value="다시입력">
</form>
🚨 클라이언트 유효성 검사 (JavaScript)
javascript
복사
편집
window.onload = function(event) {
    var frmObj = document.querySelector("#myfrm");

    frmObj.onsubmit = function(event) {
        var deptname = document.querySelector('input[name="department_name"]').value;
        if (deptname.length < 5) {
            alert("부서이름은 5자리 이상입니다.");
            event.preventDefault();
            return false;
        }
        alert("서버에 전송됩니다.");
    };
};
🧠 학습 포인트
@RequestParam, DTO, Map 기반 파라미터 처리 방식 이해

HTML 폼 데이터를 컨트롤러로 전송하는 흐름 익히기

JavaScript를 활용한 클라이언트 유효성 검사

JSP 뷰와 Spring 컨트롤러 간 연동

🔗 참고 자료
Spring 공식 문서 - Web MVC

@RequestParam vs @ModelAttribute (Baeldung)

🧰 개발 환경
Java 8+

Spring Framework 5.x

JSP / JSTL

Maven / Tomcat

IDE: Eclipse or IntelliJ

📌 실행 방법
프로젝트를 Eclipse 또는 IntelliJ에 import

Tomcat 서버 설정 후 실행

브라우저에서 http://localhost:8080/section02/deptinsert.do 접속
