#!/bin/bash

tar czf /tmp/PensioClientJAVAAPI.tgz $(find . -type f | grep -v .svn | grep -v .idea | grep -v .settings | grep -v "./external/" | grep -v "./test/" | grep -v .buildpath | grep -v .classpath | grep -v .project | grep -v build.xml | grep -v createPensioJAVAAPIClientTGZ.sh | grep -v generate.sh  | grep -v Generate.launch | grep -v devlib | grep -v ant-contrib-1.0b3.jar | grep -v build-common.xml | sed 's/\.\///')
