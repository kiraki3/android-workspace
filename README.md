- 안드로이드 공부 시작
- workspace init

### 깃 명령어 정리
- `git init`
깃 초기화 (.git 폴더 생성)

`git remote add origin {repo url}`
로컬 폴더를 해당 url 의 레포지토리와 연결

- `git status`
깃 상태 확인

- `git add .`
현재 폴더 내의 변경 사항 모두 스테이지로 추가

- `git commit -m "commit message"`
현재 스테이지에 올라온 변경사항 커밋(`-m` 옵션은 커밋 메시지 옵션)

- `git push`
현재 커밋을 리모트로 푸쉬

- `git checkout {branch명}`
특정 브랜치로 브랜치 변경

- `git checkout -b {branch명}`
특정 신규 브랜치를 만들고, 만든 신규 브랜치로 브랜치 연결

- `git pull`
리모트로부터 새로운 커밋(변경사항) 받아오기
