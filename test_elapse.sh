input_file=$1
output_file=$2
for i in {1..50}
do
	mvn test -B
	echo Test $i done 
done
echo Run TestConclusion...
java -jar tools/Test_Conclusion.jar $input_file $output_file
echo Done!
