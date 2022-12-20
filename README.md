# online-blood-bank-v3

A Capstone project: An Idea to create an Online Blood Banking System

### Some Git tips for the team 🧐

- To Clone this repo:
  `git clone https://github.com/Firwanaa/online-blood-bank-v3.git`

- To create local brach:
  `git checkout -b newBranchName`
- Check status
  `git status`
- To add your changes
  `git add -A`
- To commit changes
  `git commit -m 'message'`
- To push your branch to our Github repo
  `git push -u origin myBranchName`

- Good habit to make sure you are up-to-date
  `git pull origin main`

- Back To `main` branch
  `git checkout main`
- To update
  `git pull`
- To delete branch
  `git branch -D branchName`

### Notes:

- ##### BE: Backend "Spring Boot API"
- ##### FE: Frontend "Angular Frontend"

- ##### To build on Microsoft Windows, go first to `package.json` and remove the single quotes `dist/**`
  > before: `"deploy": "copyfiles -f 'dist/**' ../resources/static",`
  > after: `"deploy": "copyfiles -f dist/** ../resources/static",`
- ##### To build Angular frontend `npm run build`
