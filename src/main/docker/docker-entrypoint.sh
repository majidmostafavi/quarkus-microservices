#!/bin/ash

set -u

if [ "$1" = 'app' ]; then
    (cd /opt/app && java ${java_args} -jar /opt/app/${jar_file})
else
    exec "$@"
fi
