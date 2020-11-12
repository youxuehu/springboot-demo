
    # 新建分支
    git checkout -b new_branch

    # 删除本地分支
    git branch -D new_branch
    
    # 删除远程分支
    git push origin --delete new_branch
    
    # 撤销git merge 操作
    git reset --hard HEAD
    
    git log
    
    git reset --hard commitId
    
    
