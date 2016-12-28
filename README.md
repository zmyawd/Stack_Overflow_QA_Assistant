# Stack_Overflow_QA_Assistant
Team 201612-63 of Sappharine Big Data Analytics

The whole package contains 3 folders.

The folder named “recommender” contains a Maven project for recommendation. The datasets we use are put in the data folder. FileTransfer.java is used for data pre-process. It read file “Tags2.csv” and transfer strings in the file to integers using hash code and stores the transferred file into “Tags3.csv”. “Tags4.csv” is generated to record the relation between strings and hash code. App.java is the main function code of the project, which realized generic Boolean preference item-based recommendation. “pom.xml” record the dependency of the Maven project. This project is based on Mahout 0.12.2. In order to run it, you should import the whole project into an exited Eclipse workspace 

The folder named “system-g” contains the node file and edge file to form the graph. In order to generate the graph, you should install systemg-tools-1.4.0 in AWS first. After running startuiserver.sh and runggsserver.sh, you can generate the graph using the files in this folder and do graph query using g-shell or Gremlin.

The Stack Overflow (S.O.) data is downloaded from website https://www.kaggle.com/stackoverflow/stacksample by David Robinson. The data set consists of 10% of S.O. questions and answers from year 2008 to 2016. Three files are included in the data set:
- Questions contains the title, body, creation date, closed date (if applicable), score, and owner ID for all non-deleted Stack Overflow questions whose Id is a multiple of 10. Size: 1.9GB.
- Answers contains the body, creation date, score, and owner ID for each of the answers to these questions. The ParentId column links back to the Questions table. Size: 1.6GB.
- Tags contains the tags on each of these questions. Size: 65MB.

The Stack Overflow question clustering procedure is specified in qu-cluster.sh under cluste-class directory. Similarly the classification is in qu-class.sh. The following prerequisites are needed for the two shell scripts to run:
- Text-editing Python scripts are under bin/ directory.
- Original CSV files are under csv/ directory.
- Java programs under cluster-class directory are compiled using Apache Mahout classes.

Generally the procedures are:
- Select and extract data from original CSV files.
- Process data to generate the “ID, Value” CSV file.
- Call mahout seqcsv to convert the generated CSV file into binary sequence file.
- Vectorize data in sequence file to form feature data.
- Apply clustering/classification algorithms on feature data using Apache Mahout libraries.

The seqcsv is created to replace the Mahout seqdirectory lib function which generates sequence files from all files under input directory. The directory-based approach is efficient when data size is relatively small. However when there are over one million items to handle, using the directory-based approach leads to tens of Gigabytes of space and estimated hours of time cost. Although it is more suitable for distributed calculations, the directory-based function does not handle a large number of small documents very well due to the large cost of I/O operations. The new approach seqcsv takes one single CSV file as input and its output is same as the default function, but with much less cost in both time and space.
