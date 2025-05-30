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




🖥️ JSP 입력 폼 (deptinsert.jsp)
✍️ 입력 항목
부서코드 (department_id)

부서이름 (department_name)

메니저번호 (manager_id)

지역코드 (location_id)

📑 폼 예시





🚨 클라이언트 유효성 검사 (JavaScript)
javascript





🧠 학습 포인트
@RequestParam, DTO, Map 기반 파라미터 처리 방식 이해

HTML 폼 데이터를 컨트롤러로 전송하는 흐름 익히기

JavaScript를 활용한 클라이언트 유효성로 접속
