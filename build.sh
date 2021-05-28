#!/bin/bash

# add ppa repository
sudo add-apt-repository ppa:linuxuprising/java
# refresh package cache
sudo apt update
# install java16
sudo apt install oracle-java16-installer
# check that java16 is installed
java --version

# install maven
sudo apt install maven
# check that maven is installed
mvn -version

