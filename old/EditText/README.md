 # 홍드로이드 안드로이드 강의 2
  - 출처 : https://www.youtube.com/watch?v=EKCQ6sxMWNo&list=PLC51MBz7PMyyyR2l4gGBMFMMUfYmBkZxm

## activity_main.xml
  ### EditText Id, Button 만들기
  - `android:id="@+id/et_id"` 아이디 값을 부여해주는 방법

  - `android:hint="Enter your username"` 아이디를 입력하는 부분에 큰따옴표안의 text 가 써짐

  - `android:id="@+id/btn_text"` 버튼 값을 부여해주는 방법

  - `android:text="Button"` Button이라는 버튼이름


## MainActivity.java File
  ### Id, Button 변수 선언 
  - `EditText et_id; Button btn_text;` 

  ### activity_main 파일의 id 값을 연결을 하여 왼쪽으로 대입
  - `et_id = findViewById(R.id.et_id);` 

  ### activity_main 파일의 button을 연결하여 왼쪽으로 대입
  - `btn_text = findViewById(R.id.btn_text);`

  ### 버튼 클릭하면 클릭이벤트 발생
  - `btn_text.setOnClickListener`
  - `et_id` 



  ## public class MainActivity extends Activity {} 
 -  `Activity extends 클래스를 선언하는 이유`
 Activity는 애플리케이션의 화면을 구축하기 위한 기본 클래스이며 Android 프레임워크에서 예상하는 모든 수명 주기 콜백을 포함

    
