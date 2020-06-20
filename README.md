# SimpleHomes
A simple homes plugin for Minecraft

###### Permissions
The following permissions can be applied to players:
* homes.skipwait - Skip the countdown when teleporting to a home
* homes.bypasscap - Bypass the maximum home count
* homes.admin - Able to view, visit, set or delete homes of other players

Config file:
```
# do-teleport-wait : If enabled, there will be a countdown before telporting the player
# teleport-wait : The time to wait before teleporting the player
# admin-bypass-wait : If enabled, those with homes.admin will not have to wait
# max-home-count : Maximum number of homes each player may have
# overwrite-homes : When enabled, setting a new home with the same name of an already existing home will overwrite the home's location
#      disabling this will display a message informing that the home already exists.
# cancel-on-move : If enabled, the telport will be cancelled if the player moves while waiting
# chat-color: Color to use when sending messages to player (default: &6)
# teleport-color: Color to use when sending "Teleported!" to player (default &a)
# max-homecount-message : Message to display when user tries to set a new home but they have reached the maximum
# invalid-perm-message : Message to send when the player does not have the correct permissions
# 
# Commands:
# /homes : Displays your current homes
# /sethome <home-name> : Sets a new home at your current location
# /home <home-name> : Teleports you to the specified home
# /delhome <home-name> : Deletes given home
# 
# Admin commands:
# /homes <player> : Displays specified player's homes
# /sethome <player> <home-name> : Sets a new home for specified player (bypasses max home count)
# /home <player> <home-name> : Teleports you to the specified home of the player
# /delhome <player> <home-name> : Deletes the specified home from the player
# 
# Permissions
# homes.skipwait : Able to skip the wait
# homes.bypasscap : Able to bypass max home count
# homes.admin : Able to see, set, visit, remove or add new homes to other players

do-teleport-wait: true
teleport-wait: 5
admin-bypass-wait: true
max-home-count: 5
overwrite-homes: false
cancel-on-move: true
chat-color: '&6'
teleport-color: '&a'
max-homecount-message: '&cYou have reached the maximum amount of homes'
invalid-perm-message: '&cInvalid permissions'
```
