#!/bin/bash
echo "Building Project";
#sudo apt update;
#sudo apt install -y docker.io;
sudo docker build -t linkstore .;
sudo docker run --rm -it linkstore:latest;
