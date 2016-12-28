#!/usr/bin/bash

# extract question id
awk 'BEGIN{FS=","} match($3, /^2[0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9].+Z$/) {print $5","$1}' csv/Questions.csv > temp-score-id
echo "Complete: extract question score and ID"

# extract question line index
awk 'BEGIN{FS=","} match($3, /^2[0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9].+Z$/) {print NR}' csv/Questions.csv > temp-indices
echo "Complete: extract line index"

# extract question title and body
./bin/csv-one-line.py temp-indices csv/Questions.csv > /tmp/puretext-in
java puretext
cp /tmp/puretext-out temp-title-body
echo "Complete: extract title and body"

# assign question class by score
./bin/assign-class-by-score.py temp-score-id temp-title-body > temp-id-text
echo "Complete: assign classes to questions"

# call seqcsv to generate sequency file from the large csv file
cp temp-id-text /tmp/seqcsv-in
mahout seqcsv
mkdir qu-seq
cp /tmp/seqcsv-out qu-seq/part-m-00000
echo "Complete: generate sequency file"

# create TF-IDF bigram vectors
mahout seq2sparse -i qu-seq/ -o qu-vectors -nv -wt tfidf -ng 2
echo "Complete: vectorize data"

mahout split -i qu-vectors/tf-vectors/ --trainingOutput qu-train-vectors --testOutput qu-test-vectors --randomSelectionPct 40 -ow --sequenceFiles -xm sequential
echo "Complete: split data into training and testing sets"

mahout trainnb -i qu-train-vectors/ -o model -li labelindex -ow
echo "Complete: train model on training data"

mahout testnb -i qu-train-vectors/ -o qu-testing -m model/ -l labelindex -ow
echo "Complete: test model on training data"

mahout testnb -i qu-train-vectors/ -o qu-testing -m model/ -l labelindex -ow
echo "Complete: test model on testing data"

# clean up
rm temp-*
