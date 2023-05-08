DB=mysql
JAR_FILE=linkerly-0.0.1-SNAPSHOT.jar
CURRENT_PID=`pgrep -f $(JAR_FILE)`

.PHONY: start_db
start_db:
	brew services start ${DB}

.PHONY: stop_db
stop_db:
	brew services stop ${DB}

.PHONY: restart_db
restart_db:
	brew services restart ${DB}

.PHONY: build
build:
	./gradlew build

.PHONY: prebuild
prebuild:
	rm -rf ./build

.PHONY: rebuild
rebuild:
	@make prebuild
	@make build

.PHONY: start_api
start_api:
	java -jar ./build/libs/${JAR_FILE} 2> /dev/null &

.PHONY: stop_api
stop_api:
	kill -9 $(CURRENT_PID)

.PHONY: restart_api
restart_api:
	@make stop_api
	@make start_api
