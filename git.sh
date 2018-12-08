#!/bin/bash

cd ./nbproject
rm -rf private

cd ../

find . -name ".DS_Store" -print0 | xargs -0 rm -rf

git add --all
git commit -m "Push from local directory"
git push -u origin master
