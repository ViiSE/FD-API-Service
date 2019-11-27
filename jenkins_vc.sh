#!/bin/bash

main_sub_vs() {
	local main_version="${1%%.*}"
	local sub_version=$(awk -F[..] '{print $2}' <<< $1)
	echo $main_version $sub_version
}

cr_version() {
	echo create new folder $pom_version in /releases dir...
	mkdir /home/viise/fd-api-service/releases/"$1"
	echo move release into releases/"$1"...
	mv target/FD_API_Service.jar /home/viise/fd-api-service/releases/"$1"
	echo rewrite VERSION...
	echo "$1" > /home/viise/fd-api-service/VERSION
	echo done!
}

echo $(ls)
echo $(pwd)
pom=$(cat pom.xml)
pom_version=$(grep -oPm1 "(?<=<version>)[^<]+" <<< "$pom")
echo pom version: $pom_version
pom_versions=($(main_sub_vs $pom_version))

current_version=$(cat home/viise/fd-api-service/VERSION)
echo current version: $current_version
current_versions=($(main_sub_vs $current_version))

if [[ ${pom_versions[0]} -gt ${current_versions[0]} ]]; then
	echo new MAIN version is available!
	cr_version $pom_version

elif [[ ${pom_versions[1]} -gt ${current_versions[1]} ]]; then
	echo new SUB version is available!
	cr_version $pom_version
else
	echo nothing to deploy.
fi
