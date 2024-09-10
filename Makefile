
MVN_BIN ?= mvn

setup:
	${MVN_BIN} clean install

clean:
	${MVN_BIN} clean

local:
	${MVN_BIN} gatling:test -Denv=local

qa:
	${MVN_BIN} gatling:test -Denv=qa

