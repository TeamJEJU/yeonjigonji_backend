# yeonjigonji_backend

환경 설정
- 운영체제 : Window 10
- 통합개발환경(IDE) : 인텔리제이(IntelliJ)
- JDK 버전 : JDK 11
- 웹 프레임워크 : Spring 2.5.5
- 데이터베이스 : MySQL
- 테스트용 데이터베이스 : H2
- 빌드 툴 : 메이븐

주의 사항
-localhost/api/excel/prdItem에 접속 후, localhost/api/excel/prdDetailItem에 접속해야 데이터베이스가 정상적으로 저장됩니다.
(prdItem의 prd_detail_id가 prdDetailItem의 id를 참조하기 때문에)


### Packaging
```text
🎈com.JEJU.yeonjigonji_backend
 ┣ 📂config
 ┣ 📂constant       
 ┣ 📂controller
 ┃ ┣ 📃ExcelController
 ┃ ┣ 📃ItemController
 ┃ ┣ 📃MainController
 ┃ ┗ 📃MemberController
 ┣ 📂dto
 ┣ 📂entity       
 ┃ ┣ 📃BaseTimeEntity
 ┃ ┣ 📃LikeItem
 ┃ ┣ 📃Member
 ┃ ┣ 📃Message
 ┃ ┣ 📃PrdDetailItem
 ┃ ┗ 📃PrdItem
 ┣ 📂repository
 ┣ 📂service          
 ┃ ┣ 📃ExcelService
 ┃ ┣ 📃ItemService
 ┃ ┗ 📃MemberService
 ┗📂util  
```
