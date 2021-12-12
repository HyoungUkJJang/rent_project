# !/bin/bash

REPOSITORY=/home/ec2-user/app/deploy
PROJECT_NAME=rentshop

# echo "> build 파일 복사"

# cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.jar)

echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
	echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
	echo "> kill -15 $CURRENT_PID"
	kill -15 $CURRENT_PID
	sleep 5
fi

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> Jar Name : $JAR_NAME"

nohup java -jar \
  -Dspring.config.location=classpath:/application.yml,/home/ec2-user/app/application-real-db.yml,classpath:/application-real.yml \
  -Dspring.profiles.active=real \
  $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
#  $REPOSITORY/$JAR_NAME 2>&1 &
