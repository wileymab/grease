#!/bin/bash

mvn install:install-file -Dfile=build/outputs/aar/app-release.aar -DgroupId=com.wileymab -DartifactId=grease -Dversion=1.0.0-SNAPSHOT -Dpackaging=aar