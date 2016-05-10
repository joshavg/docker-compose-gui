![travis](https://travis-ci.org/laniger/docker-compose-gui.svg?branch=master)
# docker-compose-gui

`docker-compose-gui` is a minimalistic gui which shall make the developer's life easier.

## Functions

`docker-compose-gui` does not write compose files for you. That's still your task. It simply collects your compose files and wraps functions around them:

- Starting and stopping the containers attached and detached
- Launch bash sessions inside the containers
- Start your favourite file editor and edit your compose files

That's it.

## How to get it

As of now, there are no binaries ready to distribute, you have to build them yourself. Because the project is built with maven though, building is easy as pie:

    mvn install

Go to your `target` folder, find the jar, have fun.

## If you find something wrong

Don't hesitate to open issues or contact me in any other way.
