#!/bin/sh

########################################################################
# title:          Build Complete Project
# author:         Gary A. Stafford (https://programmaticponderings.com)
# url:            https://github.com/garystafford/sprint-music-docker
# description:    Clone and build complete Spring Music Docker project
# usage:          sh ./build_project.sh
########################################################################

set -ex


# mount a named volume on host to store mongo and elk data
# ** assumes your project folder is 'todo' **
docker volume create --name todo_data
docker volume create --name todo_elk

# create bridge network for project
# ** assumes your project folder is 'todo' **
docker network create -d bridge todo_net

# build images and orchestrate start-up of containers (in this order)
docker-compose -p todo up -d app 

