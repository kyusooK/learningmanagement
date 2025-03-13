
// 강사 등록
```
http :8088/users name=김강사 phoneNumber=010-xxxx-xxxx email=edu1@gmail.com isTutor=true
http PUT :8088/users/1/checktutor tutorApprove=true

http :8088/users name=나학생 phoneNumber=010-xxxx-xxxx email=stu1@gmail.com interest=수학
```

// 강의 업로드
```
http :8088/lectures userId:='{"id":1}' title="수학의정석" category="수학" assignment="집합의 정의에 대해 작성하시오" specifics="이 강의는 수학의 기본 개념을 다룹니다."
http :8088/lectures userId:='{"id":1}' title="고등학교 수학 I" category="수학" assignment="이차방정식의 해를 구하는 방법을 설명하시오" specifics="이 강의는 이차방정식의 기본 개념과 해법을 다룹니다."
http :8088/lectures userId:='{"id":1}' title="고등학교 수학 II" category="수학" assignment="삼각함수의 그래프를 그리는 과정을 설명하시오" specifics="이 강의는 삼각함수의 정의와 그래프 해석을 포함합니다."
```

// 수강신청
```
http :8088/studies lectureId:='{"id":1}' userId:='{"id":2}'
http PUT :8088/studies/1/submit assignment="집합은 수학에서 특정한 조건을 만족하는 객체들의 모임을 의미합니다. 집합은 보통 중괄호 {}를 사용하여 표현되며, 각 객체는 원소라고 불립니다. 예를 들어, 자연수 집합은 {1, 2, 3, ...}와 같이 표현할 수 있습니다. 집합은 원소의 순서나 중복을 고려하지 않으며, 두 집합이  동일한 원소를 포함하고 있으면 같은 집합으로 간주됩니다."
```