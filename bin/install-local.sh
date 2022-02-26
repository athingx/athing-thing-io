#!/bin/bash

projects[i++]="io.github.athingx.athing.thing.io:thing-io"
projects[i++]="io.github.athingx.athing.thing.io:thing-io-raspberry-pi"

mvn clean install \
  -f ../pom.xml \
  -pl "$(printf "%s," "${projects[@]}")" -am \
  '-Dmaven.test.skip=true'
