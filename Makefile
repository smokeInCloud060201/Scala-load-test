
MVN_BIN ?= mvn

setup:
	${MVN_BIN} clean install

clean:
	${MVN_BIN} clean

test:
	${MVN_BIN} gatling:test

