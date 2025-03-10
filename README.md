# 

## Model
www.msaez.io/#/123912988/storming/learningmanagement

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- lecture
- user
- study
- lecturesupport


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- lecture
```
 http :8088/lectures id="id"UserId := '{"id": 0}'title="title"specifics="specifics"category="category"assignment="assignment"
```
- user
```
 http :8088/users id="id"name="name"phoneNumber="phoneNumber"email="email"isTutor="isTutor"tutorApprove="tutorApprove"interest="interest"suggestionLecture=" suggestionLecture"
```
- study
```
 http :8088/studies id="id"LectureId := '{"id": 0}'UserId := '{"id": 0}'assignment="assignment"isSubmit="isSubmit"submitScore="submitScore"feedback="feedback"
```
- lecturesupport
```
 http :8088/assignments id="id"StudyId := '{"id": 0}'assignment="assignment"submitContent="submitContent"submitScore="submitScore"feedback="feedback"
 http :8088/lectureSuggestions id="id"UserId := '{"id": 0}'LectureId := '{"id": 0}'suggestionContent="SuggestionContent"
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```
