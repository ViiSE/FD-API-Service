for i in {1..10}
do
	mvn test -B
	echo Test $i done 
done
