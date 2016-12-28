#!/usr/bin/bash

# extract question id
awk 'BEGIN{FS=","} match($3, /^2[0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9].+Z$/) {print $1}' csv/Questions.csv > temp-ids
echo "Complete: extract question ID"

# extract question tags
./bin/tags-one-line.py csv/Tags.csv > temp-tags-one-line
paste -d "," temp-ids temp-tags-one-line > temp-id-tags
echo "Complete: join question ID with tags"

# call seqcsv to generate sequency file from the large csv file
cp temp-id-tags /tmp/seqcsv-in
mahout seqcsv
mkdir qu-seq
cp /tmp/seqcsv-out qu-seq/part-m-00000
echo "Complete: generate sequency file"

# create TF vectors
mahout seq2sparse -i qu-seq/ -o qu-vectors -nv -wt tf
echo "Complete: vectorize data"

echo "Start K-means algorithm"
# kmeans
mahout kmeans -i qu-vectors/tf-vectors/ -c qu-kmeans-clusters -o qu-kmeans -k 20 -dm org.apache.mahout.common.distance.CosineDistanceMeasure -x 50 -ow -cl; 

mahout clusterdump -i `ls -ld qu-kmeans/clusters-*-final | awk '{print $9}'` -o qu-kmeans/clusterdump -d qu-vectors/dictionary.file-0 -dt sequencefile -n 5 --evaluate -dm org.apache.mahout.common.distance.EuclideanDistanceMeasure -sp 0 --pointsDir qu-kmeans/clusteredPoints/

# clean up
rm temp-*
