#
# Local .bashrc for Natural Cobwebs
# author: cerrealic

alias run='cd server && start start.bat && cd ..'
alias debug='cd server && start startDebug.bat && cd ..'
alias bl='../spigot-dev-scripts/build.sh natural-cobwebs'
alias bll='bl && run'
alias bp='bump'

source ../spigot-dev-scripts/bump.sh
