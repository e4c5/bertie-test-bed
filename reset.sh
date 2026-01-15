#!/bin/bash

# Script to reset git repository and delete untracked files
# WARNING: This will permanently delete untracked files without confirmation!

git reset --hard
git clean -fd
git status

