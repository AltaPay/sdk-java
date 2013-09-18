#!/bin/bash

wget "https://testgateway.pensio.com/APIResponse.xsd" -O APIResponse.xsd

xjc -d generated/ -p com.pensio.api.generated APIResponse.xsd
