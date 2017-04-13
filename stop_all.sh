#!/bin/bash
echo "stopping all services..."
sudo kill -9 `ps aux | grep -v grep |  grep todo-ms/ | awk '{print $2}'` 
# grep -v grep  ->> excluding the grep process from the list
echo "stopped"