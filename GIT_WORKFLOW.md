# ===== FIRST TIME SETUP =====

## Clone the repo
git clone <your_fork_or_repo_url_here>
cd munchies

## Make sure you have main + dev
git checkout main
git pull origin main

git checkout -b dev origin/dev || git checkout dev
git pull origin dev

# ===== STARTING A NEW TASK =====
## Always sync with latest main + dev first

git checkout main
git pull origin main

git checkout dev
git pull origin dev

# Create your feature branch from dev
git checkout -b feature/<name>-<task>
## e.g.
### git checkout -b feature/dren-cli
### git checkout -b feature/ahmed-payment

# ===== WHILE WORKING =====

## Check what changed
git status

## Stage files
git add <file1> <file2>
### or everything:
### git add .

## Commit
git commit -m "Short, clear message"

# ===== PUSHING YOUR WORK =====

## First push (sets upstream)
git push -u origin feature/<name>-<task>

## Next pushes (same branch)
git push

# ===== OPEN PULL REQUEST =====
## (Done on GitHub UI)
### Source branch:  feature/<name>-<task>
### Target branch:  dev

# ===== AFTER YOUR PR IS MERGED INTO dev =====

## Update your local dev and clean up

git checkout dev
git pull origin dev

## Optionally delete old feature branch locally
git branch -d feature/<name>-<task>

## And on remote (if merged)
git push origin --delete feature/<name>-<task>

# ===== MAINTAINER ONLY (MERGING dev → main) =====
## Nokulunga only

git checkout main
git pull origin main

git checkout dev
git pull origin dev

# Create PR on GitHub:
##   base: main
##   compare: dev
## Review, then merge.

# Sync local main after merge
git checkout main
git pull origin main
