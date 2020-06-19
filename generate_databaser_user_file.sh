#!/bin/bash

file="db.createUser(
    {
        user: \"${DB_USERNAME}\",
        pwd: \"${DB_PASSWORD}\",
        roles : [
            {
                role: \"readWrite\",
                db: \"${DB_NAME}\"
            }
        ]
    }
)"

file_name="./database_user.js"

echo ${file} > ${file_name}
