package com.columbia.mingyangzheng.recommenderproject;
//import org.apache.mahout.text.SequenceFilesFromDirectory;



import java.io.File;

import java.util.List;

import org.apache.mahout.cf.taste.impl.model.GenericBooleanPrefDataModel;
//import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
//import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
//import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
//import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
//import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
//import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.ItemBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
//import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
//import org.apache.mahout.cf.taste.similarity.UserSimilarity;


public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	DataModel model1 = new GenericBooleanPrefDataModel(GenericBooleanPrefDataModel.toDataMap(new FileDataModel(new File("data/Answers.csv"))));
    	DataModel model2 = new FileDataModel(new File("data/Tags3.csv"));
    	ItemSimilarity similarity =
    			new LogLikelihoodSimilarity(model2);
    	
    	ItemBasedRecommender recommender = 
    			new GenericBooleanPrefItemBasedRecommender(model1, similarity);
    	List<RecommendedItem> recommendations = recommender.recommend(234, 6);
    	for (RecommendedItem recommendation : recommendations)
    	{
    	  System.out.println(recommendation);
    	}
    	System.out.println("over");
    }
}
