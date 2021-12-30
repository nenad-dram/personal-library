#!/bin/bash
docker run -d -p 8080:8080 -v "$(pwd)"/database:/perlib/database -v "$(pwd)"/logs:/perlib/logs --name perlib-server perlib-server
