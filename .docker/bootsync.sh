#!/bin/sh
sed -i '/if \[ -n \"\$dns\" \]/a     dns=\"$dns 8.8.8.8\"' /usr/share/udhcpc/default.script
#sed -i '/if \[ -n \"\$domain\" \]/a     domain=\"$domain dev\"' /usr/share/udhcpc/default.script