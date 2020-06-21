# SimpleHomes
A simple homes plugin for Minecraft

### Permissions
The following permissions can be applied to players:
* homes.skipwait - Skip the countdown when teleporting to a home
* homes.bypasscap - Bypass the maximum home count
* homes.admin - Able to view, visit, set or delete homes of other players

Config file:
```
# SimpleHomes v1.0
# Author: nijabutter

# Commands:
# /homes : Displays your current homes
# /sethome <home-name> : Sets a new home at your current location
# /home <home-name> : Teleports you to the specified home
# /delhome <home-name> : Deletes given home

# Admin commands:
# /homes <player> : Displays specified player's homes
# /sethome <player> <home-name> : Sets a new home for specified player (bypasses max home count)
# /home <player> <home-name> : Teleports you to the specified home of the player
# /delhome <player> <home-name> : Deletes the specified home from the player

# Permissions
# homes.skipwait : Able to skip the wait
# homes.bypasscap : Able to bypass max home count
# homes.admin : Able to view, visit, set or remove homes of other players

# Enable / disable the teleport countdown
do-teleport-wait: true

# The countdown before teleportation (in seconds)
teleport-wait: 5

# Should those with homes.admin teleport instantly
admin-bypass-wait: true

# Maximum amount of homes
max-home-count: 5

# Should setting a new home with the same name as an existing home overwrite the old home?
# If set to false - you will have to delete the old home before setting the new home
overwrite-homes: false

# Should the countdown be cancelled if you move
cancel-on-move: true

# The chat colour code to use when sending messages
# This will be prepended before each message!
chat-color: "&6"

# The chat colour code to use after successfully teleporting
# This will be prepended before each message!
teleport-color: "&a"

# The message to send after successfully teleporting
teleport-message: "Teleported!"

# Message to show when at maximum home count
max-homecount-message: "&cYou have reached the maximum amount of homes"

# Message to show when trying to access admin commands without homes.admin
invalid-perm-message: "&cInvalid permissions"
```
