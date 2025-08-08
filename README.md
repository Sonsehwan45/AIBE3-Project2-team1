# CLI 텍스트 게시판

Team1 GIT READY | 손세환 고영서 김찬종 이승원 안지협 임창기

Java 콘솔 기반 고기능 텍스트 게시판 애플리케이션입니다.

## 프로젝트 개요
- **언어**: Java
- **구조**: MVC 패턴, Static Context 의존성 주입
- **기능**: CRUD + 검색, 정렬, 조회수, 파일 저장/불러오기
- **개발환경**: IntelliJ IDEA, Gradle

## 실행 방법

### 1. 프로젝트 클론
```bash
git clone [repository-url]
cd wtl_cli_project
```

### 2. 빌드 및 실행
```bash
# Gradle 빌드
./gradlew build

# 실행
./gradlew run
```

### 3. IDE에서 실행
IntelliJ IDEA에서 `src/main/java/com/ll/Main.java` 실행

## 전체 명령어 목록

### 기본 CRUD 명령어
| 명령어 | 기능 | 사용법 |
|--------|------|--------|
| `write` | 게시글 작성 | write |
| `list` | 게시글 목록 조회 | list |
| `detail [id]` | 게시글 상세보기 | detail 1 |
| `update [id]` | 게시글 수정 | update 1 |
| `delete [id]` | 게시글 삭제 | delete 1 |

### 고급 기능 명령어
| 명령어 | 기능 | 사용법 |
|--------|------|--------|
| `search [keyword]` | 게시글 검색 | search Java |
| `list --[sort]` | 정렬된 목록 | list --view |
| `save` | 파일 저장 | save |
| `load` | 파일 불러오기 | load |
| `help` | 정렬 도움말 | help |
| `exit` | 프로그램 종료 | exit |

### 정렬 옵션
| 옵션 | 정렬 방식 | 사용법 |
|------|-----------|--------|
| (기본) | 최신순 (번호 내림차순) | list |
| `--id` | 번호 오름차순 | list --id |
| `--id-desc` | 번호 내림차순 | list --id-desc |
| `--date` | 날짜 오름차순 | list --date |
| `--date-desc` | 날짜 내림차순 | list --date-desc |
| `--view` | 조회수 높은순 | list --view |
| `--view-asc` | 조회수 낮은순 | list --view-asc |
| `--title` | 제목 가나다순 | list --title |
| `--title-desc` | 제목 역순 | list --title-desc |

## 실행 예시

### 게시글 작성 및 조회
```
=== 텍스트 게시판 ===
명령어: write
제목: Java 공부
내용: Java 기초 문법을 공부했습니다.
1번 게시글이 작성되었습니다.

명령어: write  
제목: Spring 학습
내용: Spring Framework를 배워보겠습니다.
2번 게시글이 작성되었습니다.

명령어: list
게시글 목록 (기본 정렬 (최신순))
번호 | 제목       | 등록일     | 조회수
---------------------------------------
2    | Spring 학습 | 2025-08-08 | 0
1    | Java 공부   | 2025-08-08 | 0
```

### 상세보기 및 조회수 증가
```
명령어: detail 1
번호: 1
제목: Java 공부
내용: Java 기초 문법을 공부했습니다.
등록일: 2025-08-08
조회수: 1

명령어: detail 1
번호: 1
제목: Java 공부
내용: Java 기초 문법을 공부했습니다.
등록일: 2025-08-08
조회수: 2
```

### 검색 기능
```
명령어: search Java
"Java" 검색 결과: 1개
번호 | 제목       | 등록일     | 조회수
---------------------------------------
1    | Java 공부   | 2025-08-08 | 2

명령어: search 없는내용
"없는내용"에 대한 검색 결과가 없습니다.
```

### 정렬 기능
```
명령어: list --view
게시글 목록 (조회수 높은순)
번호 | 제목       | 등록일     | 조회수
---------------------------------------
1    | Java 공부   | 2025-08-08 | 2
2    | Spring 학습 | 2025-08-08 | 0

명령어: help
=== 정렬 옵션 도움말 ===
list          # 기본 정렬 (최신순)
list --id     # 번호 오름차순
list --view   # 조회수 높은순
...
```

### 파일 저장/불러오기
```
명령어: save
게시글 데이터가 저장되었습니다.

명령어: load
게시글 데이터를 불러왔습니다.
```

## 프로젝트 구조

```
src/
├── main/
│   └── java/
│       └── com/ll/
│           ├── Main.java                    # 애플리케이션 진입점
│           ├── App.java                     # 명령어 라우팅
│           ├── AppContext.java              # Static 의존성 주입 컨테이너
│           ├── Rq.java                      # 요청 객체 (명령어 파싱)
│           └── domain/
│               ├── article/
│               │   ├── entity/
│               │   │   └── Article.java            # 게시글 엔티티
│               │   ├── repository/
│               │   │   └── ArticleRepository.java  # 데이터 저장소
│               │   ├── service/
│               │   │   └── ArticleService.java     # 비즈니스 로직
│               │   └── controller/
│               │       └── ArticleController.java  # 사용자 입출력 처리
│               └── system/
│                   └── controller/
│                       └── SystemController.java   # 시스템 명령어 처리
├── test/
│   └── java/
│       └── com/ll/
│           └── domain/article/
│               └── controller/
│                   └── ArticleControllerTest.java  # 테스트 코드
└── data/
    └── articles.txt                         # 게시글 데이터 파일
```

## 기술 스택
- **Java**: JDK 21
- **Build Tool**: Gradle
- **File I/O**: BufferedWriter/BufferedReader
- **Testing**: JUnit 5, AssertJ
- **Architecture**: MVC Pattern, Static Context DI, Command Pattern

## 상세 기능 설명

### 1. 게시글 작성 (write)
- 제목과 내용을 입력받아 새로운 게시글 생성
- 자동으로 ID 할당 및 등록일 기록 (yyyy-MM-dd)
- 조회수 0으로 초기화
- 작성 완료 시 게시글 번호 안내

### 2. 게시글 목록 (list)
- 8가지 정렬 옵션으로 게시글 목록 출력
- 번호, 제목, 등록일, 조회수 표시
- 정렬 방식이 목록 상단에 표시
- 기본 정렬: 번호 내림차순 (최신순)

### 3. 게시글 상세보기 (detail)
- 특정 게시글의 전체 내용 확인
- **조회 시 자동으로 조회수 증가**
- 번호, 제목, 내용, 등록일, 조회수 표시
- 존재하지 않는 게시글 접근 시 오류 메시지

### 4. 게시글 수정 (update)
- 기존 게시글의 제목과 내용 수정
- 기존 내용을 보여주고 새 내용 입력받음
- 조회수 및 등록일은 유지
- 존재하지 않는 게시글 수정 시 오류 처리

### 5. 게시글 삭제 (delete)
- 특정 게시글 삭제
- 존재하지 않는 게시글 삭제 시 오류 처리
- 삭제 성공/실패 메시지 제공

### 6. 게시글 검색 (search)
- **제목과 내용에서 키워드 검색**
- **대소문자 구분 없는 부분 일치 검색**
- 검색 결과 개수 표시
- 검색 결과를 목록 형태로 출력
- 검색 결과가 없을 때 안내 메시지

### 7. 파일 저장/불러오기 (save/load)
- **BufferedWriter/BufferedReader 활용**
- `data/articles.txt` 파일에 데이터 저장
- `|` 구분자로 필드 분리 저장
- **프로그램 시작 시 자동 로드**
- **프로그램 종료 시 자동 저장**
- 수동 save/load 명령어 지원

### 8. 정렬 기능
- **java.util.Comparator 활용**
- 번호, 날짜, 조회수, 제목별 정렬
- 각 기준마다 오름차순/내림차순 지원
- `help` 명령어로 정렬 옵션 확인 가능

## 데이터 파일 구조

`data/articles.txt`:
```
3                           # 마지막 ID
1|Java 공부|내용입니다|2025-08-08|5      # ID|제목|내용|등록일|조회수
2|Spring 학습|내용입니다|2025-08-08|2
3|React 공부|내용입니다|2025-08-08|0
```

## 아키텍처 특징

### 1. Static Context 의존성 주입
- AppContext에서 모든 컴포넌트를 static으로 관리
- 싱글톤 보장 및 전역 접근 가능
- 프로그램 시작/종료 시 자동 초기화/정리

### 2. Command Pattern
- Rq 클래스로 명령어 파싱 및 매개변수 추출
- App 클래스에서 명령어별 라우팅
- 각 Controller에서 비즈니스 로직 처리

### 3. MVC 패턴
- **Entity**: 데이터 구조 정의
- **Repository**: 데이터 저장소 추상화
- **Service**: 비즈니스 로직 처리
- **Controller**: 사용자 입출력 및 검증

## 테스트

```bash
# 테스트 실행
./gradlew test
```

테스트 파일: `ArticleControllerTest.java`
- 게시글 작성, 조회, 수정, 삭제 기능 테스트
- System.out 캡처를 통한 출력 검증

## 성능 특징
- **메모리 기반 저장**: 빠른 CRUD 연산
- **파일 영속성**: 프로그램 재시작 시 데이터 보존
- **Stream API 활용**: 효율적인 검색 및 정렬
- **자동 저장/로드**: 사용자 편의성 향상

## 팀원
- 손세환
- 고영서  
- 김찬종
- 이승원
- 안지협
- 임창기

---
